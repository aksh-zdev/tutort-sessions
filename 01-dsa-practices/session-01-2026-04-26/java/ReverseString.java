package session-01-2026-04-26;

public class ReverseString {
    String str="Hello";

    //Approach-1 StringBuilder
    public void reverseString()
    {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        System.out.println("Reversed String: " + sb.toString());
    }

    //Approach -2 Using char array
    public void reverseStringUsingCharArray()
    {
        char[] charArray = str.toCharArray();
        String reversed = "";
        for (int i = charArray.length - 1; i >= 0; i--) {
            reversed += charArray[i];
        }
        System.out.println("Reversed String: " + reversed);
    }

    //Approach -3 Using String concatenation
    public void reverseStringUsingConcatenation()
    {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        System.out.println("Reversed String: " + reversed);
    }

    //Approach -4 Using StringBuffer
    public void reverseStringUsingStringBuffer()
    {
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        System.out.println("Reversed String: " + sb.toString());
    }

}
//Output:
//Reversed String: olleH
