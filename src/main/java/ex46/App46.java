package ex46;
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

public class App46
{
    /* IndexOfSortedWord Function
    *
    *   Instantiates integer variable sortedWordIndex
    *   Enters for loop
    *       Sets sortedWordIndex to incrementing integer i if an element of sortedCountsList matches the integer unsortedCount
    *   Returns sortedWordIndex
    *
    * */

    public static int indexOfSortedWord(int unsortedCount, ArrayList<Integer> sortedCountsList)
    {
        int sortedWordIndex = 0;

        for(int i = 0; i < sortedCountsList.size(); i++)
        {
            if(sortedCountsList.get(i) == unsortedCount)
            {
                sortedWordIndex = i;
            }
        }

        return sortedWordIndex;
    }

    /* PrintSortedWordFrequency Function
    *
    *   Instantiates arraylists: One containing a word bank of every unique string from the input file, and
    *   another containing the frequency at which each word occurs in the input file. Indices of both objects
    *   are congruent
    *
    *   Instantiates arrays containing unsorted strings and frequencies.
    *   Enters for loop
    *       Sets each element of unsortedCounts and unsortedWords to each element of countsList and uniqueWordBank
    *   Sorts frequencyList and reverses the order so it is greatest to least
    *   Enters for loop
    *       Rearranges wordBank to match order in which frequencyList was just sorted
    *   Enters for loop
    *       Prints out frequencies of each word from greatest to least
    *
    * */
    public static void printSortedWordFrequency(ArrayList<String> uniqueWordBank, ArrayList<Integer> countsList)
    {
        ArrayList<String> wordBank = uniqueWordBank;
        ArrayList<Integer> frequencyList = countsList;

        int[] unsortedCounts = new int[countsList.size()];
        String[] unsortedWords = new String[uniqueWordBank.size()];

        for(int i = 0; i < countsList.size(); i++)
        {
            unsortedCounts[i] = countsList.get(i);
            unsortedWords[i] = uniqueWordBank.get(i);
        }

        Collections.sort(frequencyList);
        Collections.reverse(frequencyList);

        for(int i = 0; i < unsortedWords.length; i++)
        {
            wordBank.set(indexOfSortedWord(unsortedCounts[i], frequencyList), unsortedWords[i]);
        }

        for(int i = 0; i < frequencyList.size(); i++)
        {
            System.out.print(wordBank.get(i) + ": ");
            for(int j = 0; j < frequencyList.get(i); j++)
            {
                System.out.print("*");
            }
            System.out.println("");
        }

    }

    /* GetNumWords Function
    *
    *   Instantiates uniqueWord to whatever string the program is looking for
    *   Enters for loop
    *       Increments count variable if uniqueWord is found in the allWords arraylist which contains all words from the input file
    * */
    public static int getNumWords(ArrayList<String> uniqueWordBank, ArrayList<String> allWords, int uniqueWordBankIndex)
    {
        String uniqueWord = uniqueWordBank.get(uniqueWordBankIndex);
        int count = 0;

        for(int i = 0; i < allWords.size(); i++)
        {

            if(allWords.get(i).equals(uniqueWord))
            {
                count++;
            }
        }

        //System.out.println(count);
        return count;
    }

    /* SearchForWord Function
    *
    *   Instantiate isFoundInWordBank to false
    *   Enters for loop
    *       If the string the program is looking for is in the arraylist wordBank, then isFoundInWordBank is set to true
    *       Breaks from for loop
    *   Returns isFoundInWordBank
    *
    * */
    public static boolean searchForWord(ArrayList<String> wordBank, String stringToSearchFor)
    {
        boolean isFoundInWordBank = false;

        for(int i = 0; i < wordBank.size(); i++)
        {
            if(wordBank.get(i).equals(stringToSearchFor))
            {
                isFoundInWordBank = true;
                break;
            }
        }

        return isFoundInWordBank;
    }

    /* GetWordBank Function
    *
    *   Instantiate arraylist wordBank declares buffer string that is set to first element of allWords from parameter
    *   Adds first element of allWords to wordBank
    *   Enters for loop
    *       uniqueString acts as a buffer for each string in allWords
    *       If the string that is being scanned hasn't been already scanned before, then the string buffer is added to wordBank
    *   Returns wordBank
    *
    * */
    public static ArrayList<String> getWordBank(ArrayList<String> allWords)
    {
        ArrayList<String> wordBank = new ArrayList<String>();
        String uniqueString = allWords.get(0);
        wordBank.add(uniqueString);

        for(int i = 1; i < allWords.size(); i++)
        {
            uniqueString = allWords.get(i);
            if(!searchForWord(wordBank, uniqueString))
            {
                wordBank.add(uniqueString);
            }
        }

        return wordBank;
    }

    /* ReadInput Function
    *
    *   Instantiates allWords arraylist
    *   Enters while loop
    *       For each string that is scanned it is added to allWords arraylist
    *   Returns allWords arraylist
    *
    * */
    public static ArrayList<String> readInput(Scanner input)
    {
        ArrayList<String> allWords = new ArrayList<String>();
        while(input.hasNext())
        {
            allWords.add(input.next());
        }
        return allWords;
    }

    /* Main Function
    *
    *   Instantiates scanner object
    *   Instantiates arraylists allWords and uniqueWordBank and countsList
    *   Enters for loop
    *       Adds whatever getNumWords() returns to countsList arraylist. This will hold an array of integers denoting the frequency of each unique word
    *   Calls printSortedWordFrequency() function to start program
    *
    * */
    public static void main(String[] args)
    {
        Scanner input = null;

        try {
            input = new Scanner(Paths.get("src/main/java/ex46/exercise46_input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> allWords = readInput(input);
        ArrayList<String> uniqueWordBank = getWordBank(allWords);

        ArrayList<Integer> countsList = new ArrayList<Integer>();

        for(int i = 0; i < uniqueWordBank.size(); i++)
        {
            countsList.add(getNumWords(uniqueWordBank, allWords, i));
        }

        printSortedWordFrequency(uniqueWordBank, countsList);
    }
}
