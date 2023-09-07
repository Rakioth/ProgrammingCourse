package com.danielmunoz.psp.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Server {
    private final ServerSocket _serverSocket;
    private       int          _connections;

    public Server(int portNumber) throws IOException {
        _serverSocket = new ServerSocket(portNumber);
        _connections  = 0;
    }

    public void start() {
        try {
            while (true) {
                Socket clientSocket = _serverSocket.accept();
                _connections++;
                new Thread(() -> {
                    try (
                            PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
                            BufferedReader socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                    ) {
                        outputType(socketOut, socketIn);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            _connections--;
                            if (_connections == 0) _serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Server Closed");
        }
    }

    private void outputType(PrintWriter socketOut, BufferedReader socketIn) throws IOException {
        socketOut.printf("%sAvailable Options%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[36m", "\u001B[0m");
        String option;
        String inputLine;

        while ((option = socketIn.readLine()) != null)
            switch (option) {
                case "-t1" -> {
                    socketOut.printf("%sType t for Time | Type d for Date%s%n", "\u001B[35m", "\u001B[0m");
                    while ((inputLine = socketIn.readLine()) != null)
                        if (inputLine.startsWith("t"))
                            socketOut.println(LocalTime.now());
                        else if (inputLine.startsWith("d"))
                            socketOut.println(LocalDate.now());
                        else
                            socketOut.printf("%sType t for Time | Type d for Date%s%n", "\u001B[35m", "\u001B[0m");
                }
                case "-t2" -> {
                    socketOut.printf("%sType a Number for Random%s%n", "\u001B[35m", "\u001B[0m");
                    while ((inputLine = socketIn.readLine()) != null)
                        try {
                            int number = Integer.parseInt(inputLine);
                            socketOut.println((int) (Math.random() * number));
                        } catch (NumberFormatException e) {
                            socketOut.printf("%sType a Number for Random%s%n", "\u001B[35m", "\u001B[0m");
                        }
                }
                default    -> socketOut.printf("%sUnknown Option%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[31m", "\u001B[0m");
            }
    }

    public static void main(String[] args) throws IOException {
        int portNumber = 8080;
        new Server(portNumber).start();
    }
}