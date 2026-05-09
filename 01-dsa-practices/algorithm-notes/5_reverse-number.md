# Reverse Number

## Logic/ Psuedocode

    Algorithm ReverseNumber(n)

    Input: Integer n
    Output: Integer Reverse

    1. Start
    2. Reverse <-- 0
    3. While n != 0 do
        remainder <-- n % 10
        n <-- n // 10
        Reverse = (Reverse * 10) + remainder
        Digits = Digits + 1
    4. Return Reverse
    5. End

## Dry Run

    Example 01: n = 1234
    Step = 1; remainder = 4;  Reverse = 0 * 10 + 4 = 4; n = 123;
    Step = 2; remainder = 3;  Reverse = 4 * 10 + 3 = 43; n = 12;
    Step = 3; remainder = 2;  Reverse = 43 * 10 + 2 = 432; n = 1;
    Step = 4; remainder = 1;  Reverse = 432 * 10 + 1 = 4321; n = 0;
    Reverse = 4321

## Edge Cases
