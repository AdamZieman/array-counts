/**
 * Name: Adam Zieman (zieman)
 * Course: CSCI 241 - Computer Science I
 * Assignment: 7
 * 
 * Project/Class Description:
 * Will either work with a default array or an array that is user defined in size with indexs
 * randomly assigned between 1 and 99. The program will display the original array, and the values
 * of the lowest and second lowest index. The program will display how many of the indexs are within
 * a range of 1-29; 30-39; 40-59; 60-79; 80-99. Notify the user how many unique index values there are
 * and display an array with the unique values.
 * 
 * Known bugs:
 * None
 */

// imports the scanner class
import java.util.Scanner;

public class ArrayCounts
{
    public static void main (String [] args)
    {
        // Declares a variable to standard input
        Scanner keyboard = new Scanner(System.in);

        // declare the array to hold random positive 
        // integers. At this time, fill it with test numbers.
        int [] num = {70,25,12,90,25,15,42,70,42,81,38,42,51,32,49,9,
                52,65,9,65,65,75,49,22,91};

        // Ask user to if they wish to use the test array or
        // make an array of their own size
        int userChoice;
        do {
            System.out.print("Please enter 1 for the test array, 2 for your choice: ");
            userChoice = keyboard.nextInt();

            // Displays an error message if the input is out of range
            if (userChoice != 1 && userChoice != 2)
                System.out.println("Invalid entry!");

        } while (userChoice != 1 && userChoice != 2);

        // If they choose their own array, ask for size and 
        // instantiate a new array, assigning it to num.
        // Fill with random integer values 1-99
        if (userChoice == 2) {
            System.out.print("Please enter size of array: ");
            int userArraySize = keyboard.nextInt();

            // overwrites the default value of num to an instantiate variable.
            num = new int [userArraySize];

            // Assign a random integer to the indexs of the array
            for (int index = 0; index < num.length; index++) {
                num[index] = (int)(Math.random() * 99) + 1;
            }
        }

        // print array contents, 10 values per line
        System.out.println("Printing original array...");

        // call the printArray() method to print the values in original array
        printArray(num);

        // find lowest value
        int lowest = findLowest(num);
        System.out.println("The lowest number in the array is " + lowest);

        // find 2nd lowest value
        // ignore duplicate values
        int secondLowest = findSecondLowest(num);
        System.out.println("The second lowest number in the array is " 
            + secondLowest);  
        System.out.println();                            

        // call method to fill in blocks array
        int [] blocks = buildBlockArray(num);

        // print the blocks array
        System.out.println("Printing blocks ...");
        System.out.println("  <20  <40  <60  <80 <100");
        System.out.println(" ---- ---- ---- ---- ----");

        // print the blocks array contents
        for (int i = 0; i < blocks.length; i++) {
            System.out.printf("%5d", blocks[i]);
        }
        System.out.println();

        // create an array with no duplicates in it by calling
        // the eliminateDuplicates() method, which returns an array
        // that uniqueValues will hold.
        int [] uniqueValues = eliminateDuplicates(num);

        System.out.println();
        System.out.println("This array has " 
            + uniqueValues.length + " unique entries");

        // print the array containing the unique values
        printArray(uniqueValues);
    }

    // Place all new methods below main(); in the sequence specified in the
    // assignment description.

    // Prints a neat table that contains ten integers per row
    public static void printArray(int [] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%5d", array[i]);

            // Starts a new line every tenth integer
            if (i % 10 == 0 && i > 0)
                System.out.println();
        }
        System.out.println();
    }

    // Analyzes the array to find the value of the index with the smallest integer
    public static int findLowest(int [] array) {
        int lowestValue = array[0];

        // Cycles through the entire array to compare the value of what is stored in lowestValue
        for (int i = 1; i < array.length; i++) {
            if (array[i] < lowestValue)
                lowestValue = array[i];
        }

        return lowestValue;
    }

    // Analyzes the array to find the value of the index with the second smallest integer
    public static int findSecondLowest(int [] array) {
        int lowestValue = findLowest(array);
        int secondLowest = array[0];

        // Cycles through the values of the array's indexs
        for (int i = 1; i < array.length; i++) {
            // Assigns the value of the second smallest integer if current cycle is smaller
            // than what is stored and is not equal to the smallest integer
            if (array[i] < secondLowest && array[i] != lowestValue)
                secondLowest = array[i];
        }

        return secondLowest;
    }

    // Counts how many integers are within a specified range, storing that value in an array
    public static int [] buildBlockArray(int [] array) {
        int [] arrayRanges = new int [5];
        int [] truncatedArray;

        // Cycles through the array, incrementing each range by 1 when the cylce index is within that range
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 20)
                arrayRanges[0]++;
            else if (array[i] < 40)
                arrayRanges[1]++;
            else if (array[i] < 60)
                arrayRanges[2]++;
            else if (array[i] < 80)
                arrayRanges[3]++;
            else if (array[i] < 100)
                arrayRanges[4]++;
        }

        return arrayRanges;
    }

    // Creates a new array that holds no duplicate index values
    public static int [] eliminateDuplicates(int [] array) {
        int [] arrayDuplicate = array;

        // Substitutes a number for -1 if it is a duplicate
        for (int i = 1; i < array.length; i ++ ) {
            for (int ii = 0; ii < i; ii++) {
                if (arrayDuplicate[i] == array[ii] && array[ii] != -1)
                    arrayDuplicate[i] = -1;
            }
        }

        // Counts how many duplicates (-1s) there are
        int count = 0;
        for (int j = 0; j < arrayDuplicate.length; j++) {
            if (arrayDuplicate[j] == -1)
                count++;
        }

        // Instantiates an array to the length of the original - the amount of duplicate (-1s)
        // values
        int [] truncatedArray = new int [array.length - count];
        int kk = 0;
        // Assigns the value of the index of the original array to the new array if the index
        // value is not a duplicate (-1).
        for (int k = 0; k < arrayDuplicate.length; k++) {
            if (arrayDuplicate[k] != -1) {
                truncatedArray[kk] = arrayDuplicate[k];
                kk++;
            }
        }

        return truncatedArray;
    }

}
