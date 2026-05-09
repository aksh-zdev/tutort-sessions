# Find sum of first n numbers

## Logic/ Psudocode 1 : Formula Based

Algorithm FindSum(n):

Input: Integer n
Output: Integer sum

1. Start
2. sum = (n*(n+1))/2
3. return sum
4. End

## Dry Run

Example 01: n = 5
  sum = (5*(5+1))/2 --> 15

## Logic/ Psudocode 2 : Loop Based

Algorithm FindSum(n)

Input: Integer n
Output: Integer sum

1. Start
2. sum <-- 0
3. For i from 1 to n do
    sum = sum + i
4. return sum
5. End

## Dry Run (loop based)

    Example 01: n = 6
    i = 1; sum = 1
    i = 2; sum = 3
    i = 3; sum = 6
    i = 4; sum = 10
    i = 5; sum = 15
    i = 6; sum = 21
    sum = 15
