package chewyt;

import java.io.*;
import java.net.*;

public class Client {

    private static String ClientIP;
    private static int port;

    public static void main(String[] args) throws UnknownHostException, IOException {

        boolean isLogin = false;

        // Makin default IP and port in case of empty args
        if (args.length == 0) {
            ClientIP = "localhost";
            port = 12345;
        } else {
            ClientIP = args[0].substring(0, args[0].indexOf(":"));
            port = Integer.parseInt(args[0].substring(args[0].indexOf(":") + 1));

        }

        Socket socket = new Socket(ClientIP, port);
        System.out.println("Connected");

        // To read cookie text from server socket
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        // Sending commands to the server socket
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Waiting for command");
        // To read from the keyboard terminal
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String command = "";
        String login = "";

        try {

            long startTime = 0;
            long stopTime;
            long duration;

            while (!command.equals("close")) {
                System.out.print(">");
                command = keyboard.readLine(); // typing command into terminal and save into vraible for checking
                if (!command.equals("close") && !command.equals("get-cookie")) {
                    output.println(command); // send command to server (wrong command input)
                }
                if (command.equals("get-cookie")) {

                    stopTime = System.nanoTime();
                    duration = (stopTime - startTime) / 1000000000;
                    System.out.println(startTime);
                    System.out.println(duration);
                    if (startTime == 0 || duration > 5) {
                        isLogin = false;
                        System.out.println(isLogin);
                    }

                    if (isLogin == true) {

                        output.println(command); // send get-cookie command to server
                        System.out.println(br.readLine().substring(12)); // send to server if login is true
                        startTime = System.nanoTime();
                    } else {
                        System.out
                                .println("You are not registered. Please choose a username and password e.g. abc/123");
                        System.out.println("Login >");
                        login = keyboard.readLine();
                        // save to users.txt
                        FileWriter fileWriter = new FileWriter(("users.txt"), true);
                        fileWriter.append(login + "\n");
                        fileWriter.close();
                        // set to Logged in status
                        isLogin = true;
                        // send get-cookie command to server
                        output.println(command); // send get-cookie command to server
                        System.out.println(br.readLine().substring(12)); // send to server in logged in status
                        startTime = System.nanoTime();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
            System.exit(0);
        }
    }

}
