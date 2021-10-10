package ex43;
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
    /* WriteToHTML
    *
    *   Instantiates buffered writer
    *   Writes siteName and authorName to output html file
    *
    * */
    public static void writeToHtml(String siteName, String authorName, String htmlPath)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(htmlPath));

            bw.write("<title>" + siteName + "</title>" + "\n");
            bw.write("<meta>" + authorName + "</meta>" + "\n");

            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    /* Create Files
    *
    *   Creates folders
    *   Instantiates path string to create html output file
    *   Creates html output file
    *   If user enters y or yes to wanting a js folder then creates js folder
    *   If user enter y or yes to wanting a css folder then creates css folder
    *
    * */
    public static void createFiles(Path websitePath, Path namedSitePath, String jsFolderResponse, String cssFolderResponse, String siteName, String authorName) throws IOException
    {
        Files.createDirectories(websitePath);
        Files.createDirectories(namedSitePath);

        String htmlPathString = "src/main/java/ex43/website/" + siteName + "/index.html";
        Files.createFile(Paths.get(htmlPathString));
        writeToHtml(siteName, authorName, htmlPathString);
        if(jsFolderResponse.equalsIgnoreCase("Y") || jsFolderResponse.equalsIgnoreCase("Yes"))
        {
            Files.createDirectories(Paths.get("src/main/java/ex43/website/" + siteName + "/js/"));
        }

        if(cssFolderResponse.equalsIgnoreCase("Y") || cssFolderResponse.equalsIgnoreCase("Yes"))
        {
            Files.createDirectories(Paths.get("src/main/java/ex43/website/" + siteName + "/css/"));
        }
    }

    /* Main Function
    *
    *   Instantiate variables and Scanner object.
    *   Prompts for website name, author's name, and whether the user wants a js or css folder
    *   Constructs a string that would be used to create paths for creating files
    *   Calls createFiles()
    *
    * */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String siteName;
        String authorName;
        String jsFolderResponse;
        String cssFolderResponse;
        String namedWebsitePathString;
        String jsPathString;
        String cssPathString;

        System.out.print("Site name: ");
        siteName = sc.nextLine();

        System.out.print("Author: ");
        authorName = sc.nextLine();

        System.out.print("Do you want a folder for JavaScript? ");
        jsFolderResponse = sc.nextLine();

        System.out.print("Do you want a folder for CSS? ");
        cssFolderResponse = sc.nextLine();

        namedWebsitePathString = "src/main/java/ex43/website/" + siteName;

        Path websitePath = Paths.get("src/main/java/ex43/website");
        Path namedSitePath = Paths.get(namedWebsitePathString);

        createFiles(websitePath, namedSitePath, jsFolderResponse, cssFolderResponse, siteName, authorName);
    }
}
