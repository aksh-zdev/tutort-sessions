package main

import (
	"fmt"
)

/**
 * Generate all prime numbers up to n using Sieve of Eratosthenes.
 */
func sieveOfEratosthenes(n int) []int {
	if n < 2 {
		return []int{}
	}

	isPrime := make([]bool, n+1)
	for i := range isPrime {
		isPrime[i] = true
	}

	isPrime[0] = false
	isPrime[1] = false

	for i := 2; i*i <= n; i++ {
		if isPrime[i] {
			for j := i * i; j <= n; j += i {
				isPrime[j] = false
			}
		}
	}

	primes := []int{}
	for i := 2; i <= n; i++ {
		if isPrime[i] {
			primes = append(primes, i)
		}
	}

	return primes
}

func main() {
	n := 30
	primes := sieveOfEratosthenes(n)
	fmt.Printf("Primes up to %d: %v\n", n, primes)
}
