# Algorithm for checking if a number is Prime

## Goal/ Problem: Given a number n determine whether it is prime

### Approch - 01: Brute Force

#### Step-by-step Algorithm

1. Start
2. Input number n
3. if n <= 1
    return Not Prime
4. For i = 2 to n-1
    if n % i == 0
      return Not Prime
5. If no divisior found
    return Prime
6. End

#### Dry Run

Example 01: n = 7
check 7 % 2 --> not 0
check 7 % 3 --> not 0
check 7 % 4 --> not 0
check 7 % 5 --> not 0
check 7 % 6 --> not 0
==> Prime

Example 02: n = 9
check 9 % 2 --> not 0
check 9 % 3 --> 0
==> Not Prime

### Approch -02: Square root

#### Step-by-step Algorithm (Square Root)

1. Start
2. Input number n
3. if n <= 1
    return Not Prime
4. if n == 2
    return Prime
5. if n % 2 == 0
    return Not Prime (even numbers > 2 are not prime)
6. set i = 3
7. while i * i <= n
    if n % i == 0
      return Not Prime
    increment i = i + 2 (skip enven numbers)
8. Return Prime
9. End
