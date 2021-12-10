package chewyt;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static int port;
    public static String file;

    public static void main(String[] args) throws NumberFormatException, IOException {

        if (args.length == 0) {

            port = 12345;
            file = "Cookies.txt";

        } else {

            port = Integer.parseInt(args[0]);
            file = args[1];
        }
        ServerSocket server = new ServerSocket(port);
        System.out.println("[SERVER] Server is waiting for client connection...");
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        while (!server.isClosed()) {
            Socket client = server.accept();
            System.out.println("[SERVER] Connected to client!");
            CookieClientHandler clienthandler = new CookieClientHandler(client, file);
            threadPool.submit(clienthandler);
            // Thread thread = new Thread(clienthandler);
            // thread.start();
        }
        /*
         * Socket client = server.accept();
         * System.out.println("[SERVER] Connected to client!");
         */

        // Printwriter to export data to socket terminal --> client
        // PrintWriter out = new PrintWriter(client.getOutputStream(), true); //true for
        // auto flushing

        // Buffered Reader to read data from Client socket for the commands
        // BufferedReader in = new BufferedReader(new
        // InputStreamReader(client.getInputStream()));

        // !! Buffered Reader to read cookie files wirtten in the Cookie Class instead

        // Create a cookie class object
        // Cookie cookie = new Cookie();

        // Initialising string variable for the data from the client 's command
        /*
         * String command ="";
         * try {
         * while (!command.equals("close")) {
         * command = in.readLine();
         * if(command.equals("get-cookie")){
         * String cookieText = cookie.send(args[1]);
         * System.out.println("Sending to client: "+cookieText);
         * out.println(cookieText);
         * }
         * else{
         * System.out.println("[SERVER] Incorrect command. Waiting for new command...");
         * }
         * }
         * } catch (IOException e) {
         * e.getStackTrace();
         * }
         * finally{
         * server.close();
         * client.close();
         * }
         */
    }
}
