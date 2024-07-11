package com.raks.psp.example02.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("USAGE: Java EchoServer <Port Number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
                ServerSocket   echoSocket   = new ServerSocket(portNumber);
                Socket         clientSocket = echoSocket.accept();
                PrintWriter    socketOut    = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader socketIn     = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine;
            while ((inputLine = socketIn.readLine()) != null)
                socketOut.println(inputLine);
        }
    }

}