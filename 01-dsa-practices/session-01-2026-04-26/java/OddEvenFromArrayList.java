package session-01-2026-04-26;

import java.util.ArrayList;
import java.util.HashMap;

public class OddEvenFromArrayList
{
    // Approach-1 : Using for loop and if-else condition
    public static void oddEvenFromArrayList()
    {
        System.out.print("Hello, this is the program to separate odd and even numbers from an array list.\n");
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Original array:");
        for (int k : arr) {
            System.out.print(" " + k + " ");
        }
        System.out.println("\n");

        int oddCount = 0;
        int evenCount = 0;
        int[] oddNumbersArray = new int[arr.length];
        int[] evenNumbersArray = new int[arr.length];

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] % 2 == 0) {
                evenNumbersArray[evenCount] = arr[i]; // Storing even numbers in the evenNumbers array
                evenCount++;

            } else {
                oddNumbersArray[oddCount] = arr[i]; // Storing odd numbers in the oddNumbers array
                 oddCount++;
            }
        }

        System.out.println("Even numbers in the array:");
        for (int i = 0; i < evenCount; i++) {
            System.out.print(evenNumbersArray[i] + " ");
        }
        System.out.println("\nEven count: " + evenCount);
        System.out.println("\nOdd numbers in the array:");
        for (int i = 0; i < oddCount; i++) {
            System.out.print(oddNumbersArray[i] + " ");
        }
        System.out.println("\nOdd count: " + oddCount);
    }

    // Approach -2 : Using two separate ArrayLists to store odd and even numbers

    public static void oddEvenFromArrayListUsingTwoSeparateArrayLists()
    {
        System.out.print("Hello, this is the program to separate odd and even numbers from an array list. Apprroach -2\n");

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Original array:");
        for (int k : arr) {
            System.out.print(" " + k + " ");
        }
        System.out.println("\n");

        ArrayList<Integer> oddNumbersList = new ArrayList<>();
        ArrayList<Integer> evenNumbersList = new ArrayList<>();

        for(int i: arr)
        {
            if(i%2==0)
            {
                evenNumbersList.add(i);
            }
            else
            {
                oddNumbersList.add(i);
            }
        }
        System.out.println("Even numbers in the array:");
        for(int i: evenNumbersList) {
            System.out.print(i + " ");
        }
        System.out.println("\nEven count: " + evenNumbersList.size());


        System.out.println("Odd numbers in the array:");
        for(int i: oddNumbersList) {
            System.out.print(i + " ");
        }
        System.out.println("\nOdd count: " + oddNumbersList.size());
    }


    //Approach -3  HashSet and HashMap can also be used to separate odd and even numbers from an array list,

    public static void oddEvenFromArrayListUsingHashSetAndHashMap()
    {
     System.out.println("Hello, this is the program to separate odd and even numbers from an array list. Approach -3\n");
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Original array:");

        for(int k : arr) {
            System.out.print(" " + k + " ");
        }
        System.out.println("\n");

        HashMap<Integer,Integer> oddEvenMap = new HashMap<>();

        for (int i : arr)
        {
            if (i % 2 == 0) {
                oddEvenMap.put(i, 0); // Storing even numbers with value 0
            } else {
                oddEvenMap.put(i, 1); // Storing odd numbers with value 1
            }
        }

    System.out.println("Even numbers in the array:");
        for (Integer key : oddEvenMap.keySet()) {
            if (oddEvenMap.get(key) == 0) {
                System.out.print(key + " ");
            }
        }
        System.out.println("\nEven count: " + oddEvenMap.values().stream().filter(value -> value == 0).count());

        System.out.println("Odd numbers in the array:");
        for (Integer key : oddEvenMap.keySet()) {
            if (oddEvenMap.get(key) == 1) {
                System.out.print(key + " ");
            }
        }
        System.out.println("\nOdd count: " + oddEvenMap.values().stream().filter(value -> value == 1).count());

    }

    public static void main(String[] args) {
        oddEvenFromArrayList();

        oddEvenFromArrayListUsingTwoSeparateArrayLists();
    }



}
