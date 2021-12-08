package chewyt;

import java.io.*;
import java.net.*;

public class Server 
{
    public static void main( String[] args ) throws NumberFormatException, IOException
    {

        ServerSocket server  = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("[SERVER] Server is waiting for client connection...");

        while(!server.isClosed()){
            Socket client  = server.accept();
            System.out.println("[SERVER] Connected to client!");
            CookieClientHandler clienthandler = new CookieClientHandler(client,args[1]);
            Thread thread = new Thread(clienthandler);
            thread.start();
        }
        /* Socket client  = server.accept();
        System.out.println("[SERVER] Connected to client!"); */
        
        //Printwriter to export data to socket terminal --> client
        //PrintWriter out = new PrintWriter(client.getOutputStream(), true); //true for auto flushing

        //Buffered Reader to read data from Client socket for the commands
        //BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        // !! Buffered Reader to read cookie files wirtten in the Cookie Class instead
        
        //Create a cookie class object
        //Cookie cookie  =  new Cookie();

        //Initialising string variable for the data from the client 's command
        /* String command ="";
        try {
            while (!command.equals("close")) {
                command = in.readLine();
                if(command.equals("get-cookie")){
                    String cookieText  = cookie.send(args[1]);
                    System.out.println("Sending to client: "+cookieText);
                    out.println(cookieText);
                }
                else{
                    System.out.println("[SERVER] Incorrect command. Waiting for new command...");
                }
            }   
        } catch (IOException e) {
            e.getStackTrace();
        }
        finally{
            server.close();
            client.close();
        } */
    }
}
