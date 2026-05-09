# Count Digits in a Number

## Logic/ Psuedocode

Algorithm CountDigits(n)

Input: Integer n
Output Integer Digits

1. Start
2. If n == 0 then
    return 1
3. Digits <-- 0
4. While n != 0 do
    n <-- n // 10,
    Digits <-- Digits + 1
5. return Digits
6. End

## Dry Run

Example 01: n = 4567
n = 4567; Digits = 0
n = 456; Digits = 1
n = 45; Digits = 2
n = 4; Digits = 3
n = 0; Digits = 4
Digits = 4
