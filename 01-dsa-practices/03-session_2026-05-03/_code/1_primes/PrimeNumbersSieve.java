import java.util.ArrayList;
import java.util.List;

public class PrimeNumbersSieve {

    /**
     * Generate all prime numbers up to n using Sieve of Eratosthenes.
     *
     * @param n Upper bound (inclusive)
     * @return List of prime numbers ≤ n
     */
    public static List<Integer> sieveOfEratosthenes(int n) {
        List<Integer> primes = new ArrayList<>();

        if (n < 2) {
            return primes;
        }

        boolean[] isPrime = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            isPrime[i] = true;
        }

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int n = 30;
        List<Integer> primes = sieveOfEratosthenes(n);
        System.out.println("Primes up to " + n + ": " + primes);
    }
}
