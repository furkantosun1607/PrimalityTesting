import java.util.Random;

public class SolovayStrassen {
    public static void main(String[] args) {
        long n =  6620830889L  ; 
        int k = 5;   

        if (isProbablePrime(n, k)) {
            System.out.println(n + " is probably prime.");
        } else {
            System.out.println(n + " is composite.");
        }
    }

    public static long modularExponentiation(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp % 2) == 1) { 
                result = (result * base) % mod;
            }
            exp = exp / 2;
            base = (base * base) % mod;
        }
        return result;
    }

    public static int jacobiSymbol(long a, long n) {
        if (n <= 0 || n % 2 == 0) {
            return 0; 
        }
        int result = 1;
        a = a % n;

        while (a != 0) {
            while (a % 2 == 0) { 
                a = a / 2;
                if (n % 8 == 3 || n % 8 == 5) {
                    result = -result;
                }
            }
            long temp = a;
            a = n;
            n = temp;

            if (a % 4 == 3 && n % 4 == 3) {
                result = -result;
            }
            a = a % n;
        }
        return (n == 1) ? result : 0;
    }

    public static boolean isProbablePrime(long n, int k) {
        if (n < 2) {
            return false; 
        }
        if (n == 2) {
            return true; 
        }
        if (n % 2 == 0) {
            return false; 
        }

        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + Math.abs(rand.nextLong() % (n - 3)); 
            int jacobi = jacobiSymbol(a, n);
            long modExp = modularExponentiation(a, (n - 1) / 2, n);

            if (jacobi == 0 || modExp != ((jacobi + n) % n)) {
                return false; 
            }
        }
        return true; 
    }
}
