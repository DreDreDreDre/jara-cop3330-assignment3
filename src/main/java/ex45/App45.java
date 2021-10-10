package ex45;
import java.io.*;
import java.util.*;
import java.nio.file.DirectoryStream;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.stream.JsonReader;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Andre Jara
 */

public class App45
{
    /* ReadInput Function
    *
    *   Instantiate variables
    *   Enters while loop
    *       Scans next line
    *       Instantiates array of strings to hold a single line from input
    *       Runs through series of if statements replacing all instances of utilize with use and writes each word to output
    *       Closes input and output
    *
    * */
    public static void readInput(Scanner in, BufferedWriter bw) throws IOException {
        String scannedLineBuf;
        int numRows = 0;
        while(in.hasNextLine())
        {
            scannedLineBuf = in.nextLine();
            String[] wordsInLineBuf = scannedLineBuf.split(" ");
            for(int i = 0; i < wordsInLineBuf.length; i++)
            {
                if(wordsInLineBuf[i].equals("utilize"))
                {
                    wordsInLineBuf[i] = "use";
                    bw.write(wordsInLineBuf[i]);
                }
                else if(wordsInLineBuf[i].equals("\"utilize\""))
                {
                    wordsInLineBuf[i] = "\"use\"";
                    bw.write(wordsInLineBuf[i]);
                }
                else if(wordsInLineBuf[i].equals("utilizes"))
                {
                    wordsInLineBuf[i] = "uses";
                    bw.write(wordsInLineBuf[i]);
                }
                else if(wordsInLineBuf[i].equals("\"utilizes\""))
                {
                    wordsInLineBuf[i] = "\"uses\"";
                    bw.write(wordsInLineBuf[i]);
                }
                else if(wordsInLineBuf[i].equals("utilized"))
                {
                    wordsInLineBuf[i] = "used";
                }
                else if(wordsInLineBuf[i].equals("\"utilized\""))
                {
                    wordsInLineBuf[i] = "\"used\"";
                }
                else
                {
                    bw.write(wordsInLineBuf[i]);
                }

                if(i < wordsInLineBuf.length)
                {
                    bw.write(" ");
                }
            }
            bw.write("\n");
        }
        in.close();
        bw.close();
    }

    /* Main Function
    *   Instantiate strings and Scanner objects
    *   Prompts for name of output file
    *   Constructs path string to create file using Files class
    *   Instantiates BufferedWriter
    *   Calls readInput() function
    *
    * */
    public static void main(String[] args) throws IOException {
        String outputFileName;
        String outputPathString;
        Scanner sc = new Scanner(System.in);
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(Paths.get("src/main/java/ex45/exercise45_input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("What do you want to name the output file? ");
        outputFileName = sc.nextLine();
        outputPathString = "src/main/java/ex45/" + outputFileName + ".txt";

        Files.createFile(Paths.get(outputPathString));

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputPathString));

        assert fileInput != null;
        readInput(fileInput, bw);

    }
}
