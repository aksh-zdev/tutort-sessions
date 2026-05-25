#include <iostream>
#include <vector>

using namespace std;

/**
 * Generate all prime numbers up to n using Sieve of Eratosthenes.
 */
vector<int> sieveOfEratosthenes(int n) {
    vector<int> primes;

    if (n < 2) {
        return primes;
    }

    vector<bool> isPrime(n + 1, true);
    isPrime[0] = isPrime[1] = false;

    for (int i = 2; i * i <= n; i++) {
        if (isPrime[i]) {
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    for (int i = 2; i <= n; i++) {
        if (isPrime[i]) {
            primes.push_back(i);
        }
    }

    return primes;
}

int main() {
    int n = 30;
    vector<int> primes = sieveOfEratosthenes(n);

    cout << "Primes up to " << n << ": ";
    for (int p : primes) {
        cout << p << " ";
    }
    cout << endl;

    return 0;
}
