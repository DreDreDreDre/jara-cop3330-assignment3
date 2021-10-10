package ex42;
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

public class App42
{
    /* WriteToFile Function
    *
    *   Writes format for output file
    *   Enters for loop
    *       Write each line including first name, last name, and salary
    * */
    public static void writeToFile(String[] lastNames, String[] firstNames, String[] numbers, PrintWriter pw)
    {
        pw.printf("%8s %8s %8s\n", "Last", "First", "Salary");
        pw.println("----------------------------");
        for(int i = 0; i < lastNames.length; i++)
        {
            pw.printf("%8s %8s %8s\n", lastNames[i], firstNames[i], numbers[i]);
        }
    }

    /* ReadInput Function
    *
    *   Instantiates arraylist
    *   Enters while loop
    *       Adds next string to arraylist
    *   Returns arraylist
    * */
    public static ArrayList<String> readInput(Scanner in)
    {
        ArrayList<String> linesList = new ArrayList<String>();
        while(in.hasNext())
        {
            linesList.add(in.next());
        }
        return linesList;
    }

    /* Main Function
    *
    *   Instantiate String arrays and Scanner object along with arraylist of strings by using readInput() function
    *   Instantiate integers that keep track of the index where the first name, last name, and salary end in the input file
    *   Enters for loop
    *       Sets integer variables to the index of which each respective string ends
    *       Sets the elements of each array of last names, first names, and salaries to substrings in which the indices are used
    *   Instantiate buffered writer object
    *   Writes to output file
    *   Closes writer
    * */
    public static void main(String[] args){
        Scanner input = null;
        try {
            input = new Scanner(Paths.get("src/main/java/ex42/exercise42_input.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }


        assert input != null;
        ArrayList<String> linesList = readInput(input);
        String[] lastNames = new String[linesList.size()];
        String[] firstNames = new String[linesList.size()];
        String[] numbers = new String[linesList.size()];

        int lastNameEnd;
        int firstNameEnd;
        int numEnd;

        int i = 0;
            for(int j = 0; j < linesList.get(i).length() && i < linesList.size(); j++)
            {
                lastNameEnd = linesList.get(i).indexOf(",");
                firstNameEnd = linesList.get(i).indexOf(",", lastNameEnd + 1);
                numEnd = linesList.get(i).length();

                lastNames[j] = linesList.get(i).substring(0, lastNameEnd);
                firstNames[j] = linesList.get(i).substring(lastNameEnd + 1, firstNameEnd);
                numbers[j] = linesList.get(i).substring(firstNameEnd + 1, numEnd);
                i++;
                if(i > linesList.size() - 1)
                {
                   break;
                }
            }


        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/main/java/ex42/exercise42_output.txt"));
            writeToFile(lastNames, firstNames, numbers, pw);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
