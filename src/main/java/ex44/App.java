package ex44;
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

public class App
{
    /* ReadProduct Function
    *
    *   Instantiate strings and boolean
    *   Enters while loop
    *   Sets variable denoting product name to nextName
    *   Sets string to the next string
    *   Sets boolean variable to true if the product is found
    *   Prints product name
    *   Returns found
    *
    * */
    public static boolean readProduct(JsonReader reader, String productName) throws IOException {
        String prodName;
        String string;
        boolean found = false;

        while(reader.hasNext())
        {
            prodName = reader.nextName();
            string = reader.nextString();

            if(string.equals(productName))
            {
                found = true;
            }

            if(found)
            {
                System.out.println(prodName + ": " + string);
            }
        }


        //System.out.println(found);
        return found;
    }

    /* ReadJson Function
    *
    *   Instantiate booleans;
    *   Instantiate reader object, nextName, and array
    *   Enters while loop
    *       Begins object
    *       Sets boolean variable found to what readProduct() returns
    *       Sets buffer boolean variable to what variable found is set to
    *       Ends object
    *   If product is not found, prints "Sorry, that product was not found in our inventory."
    *   Ends array and object
    *   Returns boolean value foundProduct
    * */
    public static boolean readJson(JsonReader reader, String input) throws IOException
    {
        boolean found = true;
        boolean foundProduct = false;

        reader.beginObject();
        reader.nextName();
        reader.beginArray();

        while (reader.hasNext()) {
            reader.beginObject();

            found = readProduct(reader, input);

            if (found)
            {
                foundProduct = found;
            }

            reader.endObject();
        }

        if (!foundProduct)
        {
            System.out.println("Sorry, that product was not found in our inventory.");
        }


        reader.endArray();
        reader.endObject();

        return foundProduct;

    }

    /* Main Function
    *
    *   Instantiates scanner object and boolean variables found and foundProduct
    *   Enters do-while loop
    *       Prompts for product name
    *       Instantiates JsonReader reader
    *       Calls readJson returning it to variable foundProduct
    *       Closes reader
    *
    * */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean found = true;
        boolean foundProduct = false;

        do {


            System.out.print("What is the product name? ");
            input = sc.nextLine();

            try {
                JsonReader reader = new JsonReader(new FileReader("src/main/java/ex44/exercise44_input.json"));

                foundProduct = readJson(reader, input);

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(!foundProduct);
    }
}