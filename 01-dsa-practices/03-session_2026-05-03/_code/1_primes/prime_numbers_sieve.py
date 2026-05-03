from typing import List

def sieve_of_eratosthenes(n: int) -> List[int]:
    """
    Generate all prime numbers up to n using Sieve of Eratosthenes.

    Args:
        n (int): Upper bound (inclusive)

    Returns:
        List[int]: List of prime numbers ≤ n
    """
    if n < 2:
        return []

    is_prime = [True] * (n + 1)
    is_prime[0] = is_prime[1] = False

    i = 2
    while i * i <= n:
        if is_prime[i]:
            for j in range(i * i, n + 1, i):
                is_prime[j] = False
        i += 1

    return [i for i in range(2, n + 1) if is_prime[i]]


if __name__ == "__main__":
    n = 30
    primes = sieve_of_eratosthenes(n)
    print(f"Primes up to {n}: {primes}")
