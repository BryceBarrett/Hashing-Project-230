import java.io.*;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class will allow the user to create a Hashmap for strings by ClosedHashing. This class will create a closed
 * hashmap using the quadratic method of handling collision amoungest strings with the same key value.
 * 
 * @author Bryce Barrett
 * @version 4/16/17
 */
public class ClosedHashing
{
    private int arrSize;
    private String[] hashMap;
    /**
     * Constructor for objects of class ClosedHashing
     */
    public ClosedHashing(int arrSize)
    {
        this.arrSize = arrSize;
        this.hashMap = new String[arrSize];
        
    }
    //http://stackoverflow.com/questions/2624192/good-hash-function-for-strings
    public int hashOne(String key)
    {
        int k = (int)key.length();
        int u = 0, n = 0;
        
        for(int i = 0; i < k; i++)
        {
            n = (int)key.charAt(i);
            u += (i + 1) * n;
        }
        return u;
          
    }
    //http://research.cs.vt.edu/AVresearch/hashing/strings.php
    /*
     * This hash function works using the "folding" method. ie It will take the first 4 bytes of a string and
     * sum them up and then takes the next 4 and so on. The researcher who created this hash funcntion says it
     * may be very efficient for strings of longer lengths, but we may be able to make is efficient for strings
     * of shorter length as well by "folding" by 2 bytes instead of 4.
     */
    public int hashTwo(String key)
    {
        int intLength = key.length() / 4;
        long sum = 0;
        for(int j = 0; j < intLength; j++)
        {
            char c[] = key.substring(j*4, (j*4) + 4).toCharArray();
            long mult = 1;
            for(int k = 0; k < c.length; k++)
            {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        
        char c[] = key.substring(intLength *4).toCharArray();
        long mult = 1;
        for(int k = 0; k < c.length; k++)
        {
            sum += c[k] * mult;
            mult *= 256;
        }
        int intSum = (int)sum;
        return Math.abs(intSum);
    }
    
    private String txtFiletoString(String path) throws FileNotFoundException, IOException
    {
       try(BufferedReader br = new BufferedReader(new FileReader(path)))
       {
           StringBuilder sb = new StringBuilder();
           String line = br.readLine();
           
           while(line != null)
           {
               sb.append(line);
               sb.append(System.lineSeparator());
               line = br.readLine();
           }
           String everything = sb.toString();
           return everything;
       }
    }
    
    public String[] txtToArr(String path) throws FileNotFoundException, IOException
    { 
       String wordString = txtFiletoString(path);
       String word[] = wordString.split("\\r?\\n");
       return word;
    }
    
    public void insert(String inStr, boolean hashOne)
    {
        if(hashOne == true)
        {
            int key = hashOne(inStr);
            int i = 0;
            int hash = key % arrSize;
            while(i <= hashMap.length)
            {
                if(hashMap[hash] == null)
                {
                    hashMap[hash] = inStr;
                    break;
                }
                else
                {
                    i++;
                    hash = (key + (i * i)) % arrSize;
                }
            }
        }
        else
        {
            int key = hashTwo(inStr);
            int i = 0;
            int hash = key % arrSize;
            while(i <= hashMap.length)
            {
                if(hashMap[hash] == null)
                {
                    hashMap[hash] = inStr;
                    break;
                }
                else
                {
                    i++;
                    hash = (key + (i * i)) % arrSize;
                }
            }
            
        }
    }
    
    public int search(String inStr, boolean hashOne)
    {
        if(hashOne == true)
        {
            int key = hashOne(inStr);
            int i = 0;
            int hash = key % arrSize;
            int probes = 0;
            //String results = "Test";
            while(i <= hashMap.length + 1)
            {
                if(hashMap[hash] == null)
                {
                    probes++;
                    //results = "Probes: " + probes + " String not found.";
                    break;
                }
                else
                {
                    if(hashMap[hash].equals(inStr))
                    {
                        probes++;
                        //results = "Probes: " + probes + " " + inStr;
                        break;
                    }
                    else
                    {
                        probes++;
                        i++;
                        hash = (key + (i * i)) % arrSize;
                    }
                }
                
            }
            if(i > hashMap.length)
            {
                //results = "Probes: " + probes + " String not found.";
            }
            return probes;//results;
        }
        else
        {
            int key = hashTwo(inStr);
            int i = 0;
            int hash = key % arrSize;
            int probes = 0;
            //String results = "Test";
            while(i <= hashMap.length + 1)
            {
                if(hashMap[hash] == null)
                {
                    probes++;
                    //results = "Probes: " + probes + " String not found.";
                    break;
                }
                else
                {
                    if(hashMap[hash].equals(inStr))
                    {
                        probes++;
                        //results = "Probes: " + probes + " " + inStr;
                        break;
                    }
                    else
                    {
                        probes++;
                        i++;
                        hash = (key + (i * i)) % arrSize;
                    }
                }
            }
            if(i > hashMap.length)
            {
                //results = "Probes: " + probes + " String not found.";
            }
            return probes;//results;
        }
    }
    
    public String delete(String inStr, boolean hashOne)
    {
        if(hashOne == true)
        {
            int key = hashOne(inStr);
            int i = 0;
            int hash = key % arrSize;
            int probes = 0;
            String results = "Test";
            while(i <= hashMap.length + 1)
            {
                if(hashMap[hash] == null)
                {
                    results = "Probes: " + probes + " String not found.";
                    break;
                }
                else
                {
                    if(hashMap[hash].equals(inStr))
                    {
                        probes++;
                        hashMap[hash] = null;
                        results = "Probes: " + probes + " " + inStr;
                        break;
                    }
                    else
                    {
                        probes++;
                        i++;
                        hash = (key + (i * i)) % arrSize;
                    }
                }
                
            }
            if(i > hashMap.length)
            {
                results = "Probes: " + probes + " String not found.";
            }
            return results;
        }
        else
        {
            int key = hashTwo(inStr);
            int i = 0;
            int hash = key % arrSize;
            int probes = 0;
            String results = "Test";
            while(i <= hashMap.length + 1)
            {
                if(hashMap[hash] == null)
                {
                    results = "Probes: " + probes + " String not found.";
                    break;
                }
                else
                {
                    if(hashMap[hash].equals(inStr))
                    {
                        probes++;
                        hashMap[hash] = null;
                        results = "Probes: " + probes + " " + inStr;
                        break;
                    }
                    else
                    {
                        probes++;
                        i++;
                        hash = (key + (i * i)) % arrSize;
                    }
                }
                
            }
            if(i > hashMap.length)
            {
                results = "Probes: " + probes + " String not found.";
            }
            return results;
        }
    }
    
    public void printArr(String[] strArr, boolean hashOne)
    {
        if(hashOne == true)
        {
             for(int i = 0; i < strArr.length; i++)
             {
                 insert(strArr[i], true);
             }
             for(int i = 0; i < hashMap.length; i++)
             {
                 System.out.print(i + ": ");
                 if(hashMap[i] == null)
                 {
                     System.out.println("null");
                 }
                 else
                 {
                     System.out.println(hashMap[i]);
                 }
             }
        }
        else
        {
            for(int i = 0; i < strArr.length; i++)
             {
                 insert(strArr[i], false);
             }
            for(int i = 0; i < hashMap.length; i++)
            {
                System.out.print(i + ": ");
                if(hashMap[i] == null)
                {
                    System.out.println("null");
                }
                else
                {
                    System.out.println(hashMap[i]);
                }
            }
        }
    }
    /*
     * Simple method to loop through an array and print the values of it.
     */
    /*
    public void printArr(String[] inStr, boolean hashOne)
    {
        if(hashOne == true)
        {
            for(int i = 0; i < inStr.length; i++)
            {
                insert(inStr[i], true);
            }
            for(int i = 0; i < arrSize; i++)
            {
                System.out.println(hashMap[i]);
            }
        }
        else
        {
            for(int i = 0; i < inStr.length; i++)
            {
                insert(inStr[i], false);
            }
            for(int i = 0; i < arrSize; i++)
            {
                System.out.println(hashMap[i]);
            }
        }
    }*/
}
