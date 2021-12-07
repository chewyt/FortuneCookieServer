package chewyt;

//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;

/**
 * Unit test for simple Main.
 */
public class CookieTest 
{
   
    @Test
    public void assertEqualcondition() {
        
        Cookie cookietest = new Cookie();
        ArrayList<String> cookieCheck = new ArrayList<>();
        cookieCheck.add("There is only one cookie");
        cookieCheck.add("There are two cookies");
        cookieCheck.add("There are two cookies");
        cookieCheck.add("There are a few cookies");
        cookieCheck.add("I do not want to share you my cookies");
        cookieCheck.add("I had stolen your cookies");
        cookieCheck.add("Please accept the cookies");

        System.out.println(cookieCheck);
                                  
        String path = "C:/Data_Chew/Coding/NUSISS/FortuneCookieServer/project/src/main/java/Cookies.txt";
        File file  = new File(path);
        boolean existFile = file.exists();
        System.out.println("Does file exists?" + existFile);
        
        
        try {
            
            String expected = cookietest.send(path);
            boolean output1 = expected.startsWith("cookie-text "); //expected true
            boolean output2 = cookieCheck.contains(expected.substring(12)); //expected any one of the lines
            System.out.println(output1+" "+output2 +" "+expected.substring(12)+" in "+cookieCheck);   
            assertTrue(output1&&output2&&existFile); 
        } catch (FileNotFoundException e) {    
            e.printStackTrace();
        } catch (IOException e) {          
            e.printStackTrace();
        }

        
        
        
        
    }

    

}