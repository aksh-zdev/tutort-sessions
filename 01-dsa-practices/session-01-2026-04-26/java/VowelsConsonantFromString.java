package session-01-2026-04-26;

import java.util.HashSet;

public class VowelsConsonantFromString
{
    String str=" Hello World";

    //Approach 1: Using For Loop
    public void usingForLoop()
    {
        int vowelCount=0;
        int consonantCount=0;

        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' ||
               ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
            {
                vowelCount++;
            }
            else if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
            {
                consonantCount++;
            }
        }

        System.out.println("Number of Vowels: "+vowelCount);
        System.out.println("Number of Consonants: "+consonantCount);
    }

    //Approach 2: Using For Each Loop
    public void usingForEachLoop()
    {
        int vowelCount=0;
        int consonantCount=0;

        for(char ch : str.toCharArray())
        {
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' ||
               ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')
            {
                vowelCount++;
            }
            else if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
            {
                consonantCount++;
            }
        }

        System.out.println("Number of Vowels: "+vowelCount);
        System.out.println("Number of Consonants: "+consonantCount);
    }


    //Approach 3: Using HashSet/HashMap
    public void usingHashSet()
    {
        int vowelCount=0;
        int consonantCount=0;

        String vowels="aeiouAEIOU";
        HashSet<Character> vowelSet=new HashSet<>();

        for(char ch : vowels.toCharArray())
        {
            vowelSet.add(ch);
        }

        for(char ch : str.toCharArray())
        {
            if(vowelSet.contains(ch))
            {
                vowelCount++;
            }
            else if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
            {
                consonantCount++;
            }
        }

        System.out.println("Number of Vowels: "+vowelCount);
        System.out.println("Number of Consonants: "+consonantCount);
    }

    //Approach 4: Using Regular Expressions
    public void usingRegularExpressions()
    {
        int vowelCount=0;
        int consonantCount=0;

        String lowerStr=str.toLowerCase();

        for(char ch : lowerStr.toCharArray())
        {
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
            {
                vowelCount++;
            }
            else if(ch>='a' && ch<='z')
            {
                consonantCount++;
            }
        }

        System.out.println("Number of Vowels: "+vowelCount);
        System.out.println("Number of Consonants: "+consonantCount);
    }

}
//Output:
//Number of Vowels: 3
//Number of Consonants: 7
