package chewyt;

import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class CookieClientHandler implements Runnable {

    public static ArrayList<CookieClientHandler> clienthandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String filename;

    CookieClientHandler(Socket socket, String filename) {
        try {
            this.socket = socket;
            this.bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            this.br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            clienthandlers.add(this);
            this.filename = filename;
        } catch (IOException e) {
            closeEverything(socket, br, bw);
        }
    }

    @Override
    public void run() {
        Cookie cookie = new Cookie();
        String command = "";
        while (socket.isConnected()) {
            try {
                while (!command.equals("close")) {
                    command = br.readLine();
                    if (command.equals("get-cookie")) {
                        String cookieText = cookie.send(filename);
                        System.out.println("Sending to client: " + cookieText); // Print on Server terminal
                        bw.write(cookieText);
                        bw.newLine();
                        bw.flush(); // flush to Client Terminal
                    } else {
                        System.out.println("[SERVER] Incorrect command. Waiting for new command..."); // Print on server
                                                                                                      // terminal
                    }
                }
            } catch (IOException e) {
                closeEverything(socket, br, bw);
                break;
            }
        }
    }

    public void removeClientHandler() {
        clienthandlers.remove(this);
        System.out.println("How many active threads after user left: " + Thread.activeCount());
    }

    public void closeEverything(Socket socket, BufferedReader br, BufferedWriter bw) {
        removeClientHandler();
        try {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
