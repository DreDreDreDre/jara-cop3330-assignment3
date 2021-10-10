package ex41;
import java.io.*;
import java.util.*;
import java.nio.file.DirectoryStream;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Andre Jara
 */

public class App
{

    /* Main Function
    *
    *   Instantiate strings along with input and arraylist objects
    *   Enter while loop
    *       Scan next string in input file
    *       Add first and last names to arraylist
    *       Increment the number of names
    *   Sort arraylist
    *   Instantiate output
    *   Enter while loop to write to output
    *
    * */
    public static void main( String[] args )
    {
        String lastName;
        String firstName;
        int numNames = 0;
        Scanner input = null;
        try
        {
            input = new Scanner(Paths.get("src/main/java/ex41/exercise41_input.txt"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ArrayList<String> namesList = new ArrayList<String>();

        while(input.hasNext())
        {
            lastName = input.next();
            firstName = input.next();
            namesList.add(lastName + " " + firstName);
            numNames++;
        }

        Collections.sort(namesList);

        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("src/main/java/ex41/exercise41_output.txt"));
            for(int i = 0; i < numNames; i++)
            {
                output.write(namesList.get(i) + "\n");
            }
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
