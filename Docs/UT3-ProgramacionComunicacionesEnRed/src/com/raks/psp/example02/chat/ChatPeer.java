package com.raks.psp.example02.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatPeer extends Thread {
    private final ChatServer     _chatServer;
    private final Socket         _clientSocket;
    private final PrintWriter    _socketOut;
    private final BufferedReader _socketIn;

    public ChatPeer(ChatServer chatServer, Socket clientSocket) throws IOException {
        _chatServer   = chatServer;
        _clientSocket = clientSocket;
        _socketOut    = new PrintWriter(clientSocket.getOutputStream(), true);
        _socketIn     = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = _socketIn.readLine()) != null) {
                System.out.printf("clientSocket %s msg: %s%n", _clientSocket, inputLine);
                _chatServer.broadcast(inputLine, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                _clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String msg) {
        _socketOut.println(msg);
    }
}
