package com.raks.psp.example02.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final int MAX_PORT_NUMBER = 65535;
    private static final int MIN_PORT_NUMBER = 1;

    private final ServerSocket   _chatSocket;
    private final List<ChatPeer> _chatPeers;

    public ChatServer(int portNumber) throws IOException {
        _chatSocket = new ServerSocket(portNumber);
        _chatPeers  = new ArrayList<>();
    }

    public void start() {
        System.out.println("Chat Server Starting");
        try {
            while (true) {
                Socket   clientSocket = _chatSocket.accept();
                ChatPeer chatPeer     = new ChatPeer(this, clientSocket);
                synchronized (this) {
                    _chatPeers.add(chatPeer);
                }
                chatPeer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                _chatSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Chat Server Ending");
    }

    public void broadcast(String msg, ChatPeer peer) {
        synchronized (this) {
            for (ChatPeer chatPeer : _chatPeers)
                if (chatPeer != peer) chatPeer.send(msg);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int portNumber = 0;
        try {
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("<Port Number> must be an integer value");
            System.exit(1);
        }

        if (portNumber < MIN_PORT_NUMBER || portNumber > MAX_PORT_NUMBER) {
            System.err.printf("<Port Number> must be an integer value between %d and %d%n", MIN_PORT_NUMBER, MAX_PORT_NUMBER);
            System.exit(1);
        }

        ChatServer chatServer = new ChatServer(portNumber);
        chatServer.start();
    }
}