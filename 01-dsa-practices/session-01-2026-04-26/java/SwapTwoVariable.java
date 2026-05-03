package session-01-2026-04-26;

public class SwapTwoVariable {

    //Approach-1 Using third variable
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Using third variable
        int temp = a; // temp now holds the value of a
        a = b;       // a now holds the value of b
        b = temp;    // b now holds the value of temp (which is the original value of a)

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }

    //Approach-2 Using arithmetic operator
    public static void swapUsingArithmetic() {
        int a = 10;
        int b = 20;
        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Using arithmetic operator
        a = a + b; // a now holds the sum of a and b
        b = a - b; // b now holds the original value of a
        a = a - b; // a now holds the original value of b

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }

    //Approach-3 Using bitwise operator
    public static void swapUsingBitwise() {
        int a = 10;
        int b = 20;
        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Using bitwise operator
        a = a ^ b; // a now holds the result of a XOR b
        b = a ^ b; // b now holds the original value of a
        a = a ^ b; // a now holds the original value of b

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
//Output:
//Before swapping: a = 10, b = 20
//After swapping: a = 20, b = 10
