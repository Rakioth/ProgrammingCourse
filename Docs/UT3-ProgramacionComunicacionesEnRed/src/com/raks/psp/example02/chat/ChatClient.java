package com.raks.psp.example02.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

    private static final int MAX_PORT_NUMBER = 65535;
    private static final int MIN_PORT_NUMBER = 1;

    private final Socket         _socket;
    private final PrintWriter    _socketOut;
    private final BufferedReader _socketIn;
    private final BufferedReader _stdIn;

    public ChatClient(InetAddress inetAddress, int portNumber) throws IOException {
        _socket    = new Socket(inetAddress, portNumber);
        _socketOut = new PrintWriter(_socket.getOutputStream(), true);
        _socketIn  = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _stdIn     = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws InterruptedException {
        Thread uiThread = new Thread(() -> {
            try {
                String userInput;
                while ((userInput = _stdIn.readLine()) != null)
                    _socketOut.println(userInput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread nwkThread = new Thread(() -> {
            try {
                String nwkInput;
                while ((nwkInput = _socketIn.readLine()) != null)
                    System.out.println(nwkInput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        uiThread.start();
        nwkThread.start();

        uiThread.join();
        nwkThread.join();

        try {
            _socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 2) {
            System.err.println("USAGE: ChatClient <IP Address> <Port Number>");
            System.exit(1);
        }

        InetAddress address = null;

        try {
            address = InetAddress.getByName(args[0]);
        } catch (UnknownHostException ex) {
            System.err.printf("USAGE: <IP Address> %s invalid%n", args[0]);
            System.exit(1);
        }

        if (!address.isReachable(10)) {
            System.err.printf("USAGE: Can't reach <IP Address> %s%n", args[0]);
            System.exit(1);
        }

        int portNumber = 0;

        try {
            portNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("<Port Number> must be an integer value");
            System.exit(1);
        }

        if (portNumber < MIN_PORT_NUMBER || portNumber > MAX_PORT_NUMBER) {
            System.err.printf("<Port Number> must be an integer value between %d and %d%n", MIN_PORT_NUMBER, MAX_PORT_NUMBER);
            System.exit(1);
        }

        ChatClient chatClient = new ChatClient(address, portNumber);
        chatClient.start();
    }

}