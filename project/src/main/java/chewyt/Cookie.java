package chewyt;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Cookie {

    ArrayList <String> cookieList  =new ArrayList<>();

    public String send(String filename) throws IOException, FileNotFoundException{

        //filename = "Cookies.txt"; Forced assingment to skip sys arg input

        File file = new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        System.out.println("File .exists: "+file.exists());
        
        
        try (FileReader fr = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fr);
            
            String line=null;
            cookieList.clear();   
            while( (line = reader.readLine()) !=null ){
                cookieList.add(line);
            }
            reader.close(); //close the reader
            
        } catch (FileNotFoundException e) {
            System.out.println("A File not found error occurred.");
            e.printStackTrace();
        }
         catch (IOException e) {
        System.out.println("An IO error occurred.");
        e.printStackTrace();
         }

      

        Random rand = new Random();
        int luckynum = rand.nextInt(cookieList.size()-1);
        //System.out.println(cookieList);
        return "cookie-text "+ cookieList.get(luckynum);
        
    }

    /* public static void main(String[] args) throws IOException {
        Cookie cookie = new Cookie();
        for (int i = 0 ; i < 10 ; i++){
            System.out.println(cookie.send("Cookies.txt"));
        }
        
        
    } */

}