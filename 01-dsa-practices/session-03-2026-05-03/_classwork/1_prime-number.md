# Find All Prime Numbers from 1 to n

- What is a Prime number?

>>> A prime number is a natural number greater than 1 that has exactly two distinct positive divisors: 1 and itself.

- Core idea

>>> We do NOT check if a number is prime. We eliminate non-prime numbers.

## Logic: Sieve of Eratosthenes

### Thinking Pattern

- Assume all numbers are prime initially (optimistic assumption)
- Eliminate multiples of each number
- If a number is still not eliminated --> it is prime
- Start eliminating from i² (avoid redundant work)
- Stop when i × i > n

### Algorithm

```pseudocode
Algorithm PrimeNumbersList(n)

Input: Integer n
Output: List of prime numbers from 1 to n

1. Start

2. Create an array isPrime[0....n] and initialize all values to true

3. Set isPrime[0] = false
4. Set isPrime[1] = false

5. For i = 2 while (i * i <= n)
    If isPrime[i] == true
      For j = i * i; j <= n; j = j + i
        isPrime[j] = false

6. For i = 2 to n
    If isPrime[i] == true
      print i

7. End
```

### Dry Run

> Example 01: n = 20

| Steps  | Index              |    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 |
|--------|--------------------|----|---|---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|----|----|----|----|----|
| Step 1 | Init               |    | T | T | T | T | T | T | T | T | T | T | T  | T  | T  | T  | T  | T  | T  | T  | T  | T  | T  |
| Step 2 | [0]=F, [1]=F       |    | F | F | T | T | T | T | T | T | T | T | T  | T  | T  | T  | T  | T  | T  | T  | T  | T  | T  |
| Step 3 | i=2                |j=4 | F | F | T | T | F | T | F | T | F | T | F  | T  | F  | T  | F  | T  | F  | T  | F  | T  | F  |
| Step 4 | i=3                |j=9 | F | F | T | T | F | T | F | T | F | F | F  | T  | F  | T  | F  | F  | F  | T  | F  | T  | F  |
| Step 5 | i=4 (skip)         |j=16| F | F | T | T | F | T | F | T | F | F | F  | T  | F  | T  | F  | F  | F  | T  | F  | T  | F  |
| Step 6 | i=5 (out of range) |j=25| F | F | T | T | F | T | F | T | F | F | F  | T  | F  | T  | F  | F  | F  | T  | F  | T  | F  |
| Step 7 | **Result**         |    | F | F | T | T | F | T | F | T | F | F | F  | T  | F  | T  | F  | F  | F  | T  | F  | T  | F  |

**Primes:** 2, 3, 5, 7, 11, 13, 17, 19

> Example 01: n = 30

| Index  | Init  | [0]=F, [1]=F | [i]=2; j = 4 to 30, increment by 2  | [i]=3; j = 9 to 30, increment by 3  | [i]=4; skipped as covered by i=2 | [i]=5; j=25 to 30, increment by 5 | [i]=6; [j]=36 > 30 loop never runs |
|------- |-------|--------------|-------------------------------------|-------------------------------------|----------------------------------|-----------------------------------|------------------------------------|
|   0    |   T   |       F      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   1    |   T   |       F      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   2    |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   3    |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   4    |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   5    |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   6    |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   7    |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   8    |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   9    |   T   |       T      |                  T                  |                  F                  |                 F                |                  F                |                 F                  |
|   10   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   11   |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   12   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   13   |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   14   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   15   |   T   |       T      |                  T                  |                  F                  |                 F                |                  F                |                 F                  |
|   16   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   17   |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   18   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   19   |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   20   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   21   |   T   |       T      |                  T                  |                  F                  |                 F                |                  F                |                 F                  |
|   22   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   23   |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   24   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   25   |   T   |       T      |                  T                  |                  T                  |                 T                |                  F                |                 F                  |
|   26   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   27   |   T   |       T      |                  T                  |                  F                  |                 F                |                  F                |                 F                  |
|   28   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |
|   29   |   T   |       T      |                  T                  |                  T                  |                 T                |                  T                |                 T                  |
|   30   |   T   |       T      |                  F                  |                  F                  |                 F                |                  F                |                 F                  |

**Primes:** 2, 3, 5, 7, 11, 13, 17, 19, 23, 29
