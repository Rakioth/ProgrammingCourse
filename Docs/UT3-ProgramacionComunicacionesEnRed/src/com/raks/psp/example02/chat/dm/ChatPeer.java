package com.raks.psp.example02.chat.dm;

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
    private final String         _nickname;

    public ChatPeer(ChatServer chatServer, Socket clientSocket) throws IOException {
        _chatServer   = chatServer;
        _clientSocket = clientSocket;
        _socketOut    = new PrintWriter(_clientSocket.getOutputStream(), true);
        _socketIn     = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));

        String tempNickname;
        while (_chatServer.nicknameCheck(tempNickname = _socketIn.readLine()) != -1) {
            switch (chatServer.nicknameCheck(tempNickname)) {
                case 0 -> _socketOut.printf("%sERROR: Nickname Already Exist%s%n", ConsoleColors.ANSI_YELLOW, ConsoleColors.ANSI_RESET);
                case 1 -> _socketOut.printf("%sERROR: Nickname Has Whitespaces%s%n", ConsoleColors.ANSI_YELLOW, ConsoleColors.ANSI_RESET);
                case 2 -> _socketOut.printf("%sERROR: Nickname Has Special Characters%s%n", ConsoleColors.ANSI_YELLOW, ConsoleColors.ANSI_RESET);
            }
        }
        _socketOut.println("/approved");
        _nickname = tempNickname;
    }

    public String getNickname() {
        return _nickname;
    }

    public void send(String msg) {
        _socketOut.println(msg);
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = _socketIn.readLine()) != null)
                if (msg.startsWith("@"))
                    _chatServer.directMessage(msg, this);
                else
                    _chatServer.broadcast(msg, this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                _chatServer.removeConnection(_nickname);
                _clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return _nickname;
    }
}
