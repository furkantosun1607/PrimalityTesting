import java.util.Random;

public class Fermat {


    public static void main(String[] args) {
        long number = 16785407L  ; 
        int k = 10; 
        String result = isProbablePrime(number, k);
        System.out.println("The number " + number + " is " + result + ".");
    }

    public static long modularExponentiation(long base, long exp, long mod) {
        long result = 1;
        base = base % mod; 

        while (exp > 0) {
            if (exp % 2 == 1) { 
                result = (result * base) % mod;
            }
            exp = exp / 2; 
            base = (base * base) % mod;
        }

        return result;
    }

    public static String isProbablePrime(long n, int k) {
        if (n <= 1) return "Composite"; 
        if (n == 2) return "Prime";    
        if (n % 2 == 0) return "Composite"; 

        Random random = new Random();
        for (int i = 0; i < k; i++) {
            long a = 2 + Math.abs(random.nextLong() % (n - 3)); 
            if (modularExponentiation(a, n - 1, n) != 1) {
                return "Composite"; 
            }
        }

        return "Prime"; 
    }

}
