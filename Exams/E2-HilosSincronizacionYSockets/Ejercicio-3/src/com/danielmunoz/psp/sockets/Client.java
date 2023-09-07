package com.danielmunoz.psp.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private final Socket         _socket;
    private final PrintWriter    _socketOut;
    private final BufferedReader _socketIn;
    private final BufferedReader _stdIn;

    public Client(InetAddress inetAddress, int portNumber) throws IOException {
        _socket    = new Socket(inetAddress, portNumber);
        _socketOut = new PrintWriter(_socket.getOutputStream(), true);
        _socketIn  = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _stdIn     = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException, InterruptedException {
        Thread uiThread = new Thread(() -> {
            try {
                String userInput;
                while (!"/exit".equalsIgnoreCase(userInput = _stdIn.readLine()))
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
                System.err.println("Client Closed");
            }
        });
        uiThread.start();
        nwkThread.start();
        uiThread.join();
        _socket.close();
        _stdIn.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        int         portNumber  = 8080;
        new Client(inetAddress, portNumber).start();
    }
}
