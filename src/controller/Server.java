package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Antonio Moreno Valls
 **/
public class Server extends Thread {

    private static final int PORT = 1024;
    private String message;

    private enum MoveTypes {
        DIRECTION_LEFT, DIRECTION_RIGHT
    }

    public Server() {

    }

    public String getMessage() {
        return message;
    }

    @Override
    public void run() {
        ServerSocket sSocket;
        try {
            sSocket = new ServerSocket(PORT);
            System.out.println("Server is ONLINE >> " + Inet4Address.getLocalHost().getHostAddress() + ":" + PORT);
            try {
                while (!sSocket.isClosed()) {
                    Thread t = new Thread(new Handler(sSocket.accept()));
                    t.start();
                }
            } finally {
                sSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class Handler implements Runnable {

        private final Socket socket;
        private BufferedReader bReader;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("\t" + "Client: " + socket.getInetAddress().getHostAddress());
            try {
                bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (socket.isConnected()) {
                    String type = bReader.readLine();

                    for (MoveTypes moveTypes : MoveTypes.values()) {
                        if (type.equals(MoveTypes.DIRECTION_LEFT.name())) {
                            message = MoveTypes.DIRECTION_LEFT.name();
                        }

                        if (type.equals(MoveTypes.DIRECTION_RIGHT.name())) {
                            message = MoveTypes.DIRECTION_RIGHT.name();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
