package chewyt;

import java.io.*;
import java.net.*;

public class Client {

    private static String ClientIP;
    private static int port;

    public static void main(String[] args) throws UnknownHostException, IOException {
        

        ClientIP = args[0].substring(0, args[0].indexOf(":"));
        port = Integer.parseInt(args[0].substring(args[0].indexOf(":")+1));

        Socket socket = new Socket(ClientIP,port);
        System.out.println("Connected");

        //To read cookie text from server socket
        BufferedReader br = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        //Sending commands to the server socket
        PrintWriter output  = new PrintWriter(socket.getOutputStream(),true);
        System.out.println("Waiting for command");
        // To read from the keyboard terminal
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String command  = "" ;

        try {
            while (!command.equals("close")) {
                System.out.print(">");
                command = keyboard.readLine();
                output.println(command);
                if(command.equals("get-cookie")){
                    System.out.println(br.readLine().substring(12));
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        finally{
            socket.close();
            System.exit(0);
        }
    }
    
}
