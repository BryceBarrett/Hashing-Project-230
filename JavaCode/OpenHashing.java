import java.io.*;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class will allow allow the user to use a hash function and create and manipulate a hash map. This class
 * will use OpenHashing for the hash function/map. It will use a SinglyLinkedList to handle collision amoung 
 * the things being placed into the hashmap.
 * 
 * @author Bryce Barrett 
 * @version 4/16/17
 */
public class OpenHashing
{
    // instance variables - replace the example below with your own
    private int arrSize;
    private ArrayList<String>[] hashMap;

    /**
     * Constructor for objects of class OpenHashing
     */
    public OpenHashing(int arrSize)
    {
        this.arrSize = arrSize;
        this.hashMap = new ArrayList[arrSize];
    }
    //http://research.cs.vt.edu/AVresearch/hashing/strings.php
    public int hashOne(String key)
    {
        int k = (int)key.length();
        int u = 0, n = 0;
        
        for(int i = 0; i < k; i++)
        {
            n = (int)key.charAt(i);
            u += (i + 1) * n;
        }
        return u % arrSize;
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
        return Math.abs(intSum) % arrSize;
    }
    
    //http://howtodoinjava.com/core-java/io/how-to-read-data-from-inputstream-into-string-in-java/
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
    
    /*
     * This is the public method that allows the user to convert a txt file to an array of strings.
     * 
     */
    public String[] txtToArr(String path) throws FileNotFoundException, IOException
    { 
       String wordString = txtFiletoString(path);
       String word[] = wordString.split("\\r?\\n");
       return word;
    }
    
    /*
     * This is the insert method to insert an array of strings into a hash table.
     * 
     */
    public void insert(String[] strArr, boolean hashOne)
    {
        if(hashOne == true)
        {
            for(int i = 0; i < strArr.length; i++)
            {
                String s = strArr[i];
                int key = hashOne(s);
                if(hashMap[key] == null)
                {
                    hashMap[key] = new ArrayList<String>();
                    hashMap[key].add(s);
                }
                else
                {
                    hashMap[key].add(s);
                }
            }
        }
        else
        {
            for(int i = 0; i < strArr.length; i++)
            {
                String s = strArr[i];
                int key = hashTwo(s);
                if(hashMap[key] == null)
                {
                    hashMap[key] = new ArrayList<String>();
                    hashMap[key].add(s);
                }
                else
                {
                    hashMap[key].add(s);
                }
               
            }
        }
    }
    /*
     * This method will allow me to test how many things each hash function is putting in each index of the 
     * array.
     */
    public void printArr(String[] strArr, boolean hashOne)
    {
        if(hashOne == true)
        {
             insert(strArr ,true);
             
             for(int i = 0; i < hashMap.length; i++)
             {
                 System.out.print(i + ": ");
                 if(hashMap[i] == null)
                 {
                     System.out.println("0");
                 }
                 else
                 {
                     System.out.println(hashMap[i].size());
                 }
             }
        }
        else
        {
            insert(strArr, false);
            for(int i = 0; i < hashMap.length; i++)
            {
                System.out.print(i + ": ");
                if(hashMap[i] == null)
                {
                    System.out.println("0");
                }
                else
                {
                    System.out.println(hashMap[i].size());
                }
            }
        }
    }
    public void printList(int index)
    {
        if(hashMap[index] == null)
        {
            System.out.println("Nothing at that index.");
        }
        else
        {
            for(int i = 0; i < hashMap[index].size(); i++)
            {
                System.out.println(hashMap[index].get(i));
            }
        }
    }
    /**
     * This method will allow me to search through a hashMap for a specific string while counting the number of
     * compares that occur.
     */
    public int search(String searchStr, boolean hashOne)
    {
        int probe = 0;
        //String results = "";
        if(hashOne == true)
        {
            int key = hashOne(searchStr);
            if(hashMap[key] == null)
            {
                probe++;
                 //results = "Probes: " + probe + " String not in hashMap";
            }
            else
            {
                for(int i = 0; i < hashMap[key].size(); i++)
                {
                    probe += 1;
                    if(hashMap[key].get(i).equals(searchStr))
                    {
                        //results = "Probes: " + probe + " " + searchStr;
                        break;
                    }
                    else if(hashMap[key].size() == probe)
                    {
                        //results = "Probes: " + probe + " String not in hashmap.";
                    
                    }
                
                }
            }
            return probe;//results;
        }
        else
        {
            int key = hashTwo(searchStr);
            if(hashMap[key] == null)
            {
                probe++;
                
                //results = "Probes: " + probe + " String not in hashMap";
            }
            else
            {
                for(int i = 0; i < hashMap[key].size(); i++)
                {
                    probe += 1;
                    if(hashMap[key].get(i).equals(searchStr))
                    {
                        //results = "Probes: " + probe + " " + searchStr;
                        break;
                    }
                    else if(hashMap[key].size() == probe)
                    {
                        //results = "Probes: " + probe + " String not in hashmap.";
                    
                    }
                
                }
            }
            return probe;//results;
        }
    }
    
    /*
     * This method will allow me to delete one string from the hashMap, if the string is in the 
     * hashmap. If the string is not in the hashmap then I will just return a string letting
     * the user know that the string that they want to delete is not in the hashMap.
     */
    public String delete(String deleteStr, boolean hashOne)
    {
        int probe = 0;
        String  results = "";
        if(hashOne == true)
        {
            int key = hashOne(deleteStr);
            if(hashMap[key] == null)
            {
                results = "Probes: " + probe + " String not in hashMap";
            }
            else
            {
                for(int i = 0; i < hashMap[key].size(); i++)
                {
                    probe += 1;
                    if(hashMap[key].get(i).equals(deleteStr))
                    {
                        hashMap[key].remove(i);
                        results = "Probes: " + probe + " " + deleteStr;
                        break;
                    }
                    else if(hashMap[key].size() == probe)
                    {
                        results = "Probes: " + probe + " String not in hashmap.";
                    
                    }
                
                }
            }
            return results;
        }
        else
        {
            int key = hashTwo(deleteStr);
            if(hashMap[key] == null)
            {
                results = "Probes: " + probe + " String not in hashMap";
            }
            else
            {
                for(int i = 0; i < hashMap[key].size(); i++)
                {
                    probe += 1;
                    if(hashMap[key].get(i).equals(deleteStr))
                    {
                        hashMap[key].remove(i);
                        results = "Probes: " + probe + " " + deleteStr;
                        break;
                    }
                    else if(hashMap[key].size() == probe)
                    {
                        results = "Probes: " + probe + " String not in hashmap.";
                    
                    }
                
                }
            }
            return results;
        }
    }

}


