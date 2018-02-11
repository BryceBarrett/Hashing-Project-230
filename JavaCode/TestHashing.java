import java.io.*;
import java.util.Random;
/**
 * This class will allow the OpenHashing and ClosedHashing classes to be tested.
 * 
 * 
 * The test cases that I have used are formed by changing the values of the loops within the this class.
 * 
 * During the tests I always have one of the classes tests completely commented out.
 * 
 * @author Bryce Barrett 
 * @version 4/16/17
 */
public class TestHashing
{
   public static void main(String[] args) throws FileNotFoundException, IOException
   {
       //This statement will need to change depending on where you store the txt file.
       String filePath = "C:/Users/bryce_000/Documents/College of Charleston/Sophomore/CSCI 230/project1/words.txt";
       
       /*
        * These are all test cases/random tests for the open hashing methods.
        */
       
       /*
       OpenHashing trial = new OpenHashing(1009);
      
       String[] fullList = trial.txtToArr(filePath);
       
       String[] newStest = new String[5050];
       for(int i = 0; i < 5050; i++)
       {
           newStest[i] = fullList[i];
           
       }
       trial.printArr(newStest, false);
       
       Random rand = new Random(); 
       
       String[] sucTest = new String[400];
       for(int i = 0; i < 400; i++)
       {
           int value = rand.nextInt(50000) + 10000;
           sucTest[i] = fullList[value];
           
       }
       int count = 0;
       long countT = 0;
       for(int i = 0; i < 400; i++)
       {
           String inStr = sucTest[i];
           long startTime = System.nanoTime();
           count += trial.search(inStr, false);
           System.out.println(count);
           long endTime = System.nanoTime();
           long duration = endTime - startTime;
           countT += duration;
           System.out.println("Time(in nanoseconds): " + duration);
       }
       System.out.println("Avg. Time: " + countT / 400);
       //System.out.println("Time(in nanoseconds): " + duration);
       */
      
       /*
        * These are the test cases/random tests for the closed hashing methods.
        * Data Structures and ALgorithms with Object-Oriented Design Patterns in Java//place when formula came from
        * pg241
        */
       
       ClosedHashing test = new ClosedHashing(3001);
       //String[] dblTest = {"nick", "sam", "aaa"};
       String[] fullList = test.txtToArr(filePath);
       
       Random rand = new Random();
       String[] newStest = new String[1650];
       for(int i = 0; i < 1650; i++)
       {
           //newStest[i] = fullList[i];
           int value = rand.nextInt(10000);
           newStest[i] = fullList[value];
       }
       test.printArr(newStest, false);
       
       //Random rand = new Random(); 
       
       String[] sucTest = new String[200];
       for(int i = 0; i < 200; i++)
       {
           int value = rand.nextInt(50000) + 10000;
           //sucTest[i] = fullList[value];
           sucTest[i] = fullList[value];
       }
       int count = 0;
       long countT = 0;
       for(int i = 0; i < 200; i++)
       {
           String inStr = sucTest[i];
           long startTime = System.nanoTime();
           count += test.search(inStr, false);
           System.out.println(count);
           long endTime = System.nanoTime();
           long duration = endTime - startTime;
           countT += duration;
           System.out.println("Time(in nanoseconds): " + duration);
       }
       System.out.println(countT/200 + "Avg time.");
       //System.out.println(test.search("abditive", false));
       
       
   }
}
