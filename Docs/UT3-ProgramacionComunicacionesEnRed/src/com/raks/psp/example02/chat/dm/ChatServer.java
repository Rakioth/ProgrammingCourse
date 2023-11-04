package com.raks.psp.example02.chat.dm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ChatServer {

    private static final int MAX_PORT_NUMBER = 65535;
    private static final int MIN_PORT_NUMBER = 1;

    private final ServerSocket          _serverSocket;
    private final Map<String, ChatPeer> _chatUsers;

    public ChatServer(int portNumber) throws IOException {
        _serverSocket = new ServerSocket(portNumber);
        _chatUsers    = new HashMap<>();
    }

    public int nicknameCheck(String nickname) {
        if (_chatUsers.keySet().stream().anyMatch(nick -> nick.equalsIgnoreCase(nickname)))
            return 0;
        if (nickname.isBlank() || nickname.contains(" "))
            return 1;
        if (Pattern.compile("[^a-zA-Z0-9]").matcher(nickname).find())
            return 2;
        return -1;
    }

    public void start() {
        System.out.printf("%sServer Starting%s%n", ConsoleColors.ANSI_CYAN, ConsoleColors.ANSI_RESET);
        try {
            while (true) {
                Socket clientSocket = _serverSocket.accept();
                new Thread(() -> {
                    try {
                        ChatPeer chatPeer = new ChatPeer(this, clientSocket);
                        System.out.printf("%sDEBUG: %s entered the Chat%s%n", ConsoleColors.ANSI_YELLOW, chatPeer.getNickname(), ConsoleColors.ANSI_RESET);
                        synchronized (this) {
                            _chatUsers.put(chatPeer.getNickname(), chatPeer);
                        }
                        chatPeer.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Server Closed");
        }
    }

    public synchronized void broadcast(String msg, ChatPeer src) {
        if (msg.isBlank()) {
            src.send(String.format("%sERROR: No Message%s", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET));
            return;
        }

        _chatUsers.keySet()
                  .stream()
                  .filter(nick -> !nick.equalsIgnoreCase(src.getNickname()))
                  .forEach(nick -> _chatUsers.get(nick).send(String.format("%sFrom %s%s - %s", ConsoleColors.ANSI_PURPLE, src, ConsoleColors.ANSI_RESET, msg.trim())));
    }

    public synchronized void directMessage(String msg, ChatPeer src) {
        if (!msg.contains(" ")) {
            src.send(String.format("%sERROR: No Message%s", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET));
            return;
        }

        if (msg.substring(msg.indexOf(" ")).isBlank()) {
            src.send(String.format("%sERROR: No Message%s", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET));
            return;
        }

        List<String> user = _chatUsers.keySet()
                                      .stream()
                                      .filter(nick -> nick.equalsIgnoreCase(msg.substring(1, msg.indexOf(" ")))).toList();

        if (user.isEmpty()) {
            src.send(String.format("%sERROR: No User Found%s", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET));
            return;
        }

        user.forEach(nick -> {
            if (_chatUsers.get(nick) != src)
                _chatUsers.get(nick).send(String.format("%s[DM] From %s%s - %s", ConsoleColors.ANSI_PURPLE, src, ConsoleColors.ANSI_RESET, msg.substring(msg.indexOf(" ") + 1).trim()));
            else
                src.send(String.format("%sERROR: Can't send DM to Yourself%s", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET));
        });
    }

    public synchronized void removeConnection(String nickname) throws IOException {
        System.out.printf("%sDEBUG: %s left the Chat%s%n", ConsoleColors.ANSI_YELLOW, nickname, ConsoleColors.ANSI_RESET);
        _chatUsers.remove(nickname);
        if (_chatUsers.isEmpty()) _serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
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

        new ChatServer(portNumber).start();
    }

}