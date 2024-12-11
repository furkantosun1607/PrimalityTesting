import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {
    public static void main(String[] args) {
        BigInteger number = new BigInteger("2521008887"); // Test number
        int iterations = 5; // Number of trials
        System.out.println(number + " is prime? " + isPrime(number, iterations));
    }

    public static boolean isPrime(BigInteger n, int iterations) {
        if (n.compareTo(BigInteger.TWO) < 0) return false;
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) return true;
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return false;

        // Decompose n-1 as 2^e * d
        BigInteger d = n.subtract(BigInteger.ONE);
        int e = 0;
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            e++;
        }

        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            BigInteger a = BigInteger.TWO.add(new BigInteger(n.bitLength() - 2, random).mod(n.subtract(BigInteger.TWO)));
            BigInteger x = a.modPow(d, n); // Compute a^d % n

            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }

            boolean isComposite = true;
            for (int j = 0; j < e - 1; j++) {
                x = x.modPow(BigInteger.TWO, n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    isComposite = false;
                    break;
                }
            }
            if (isComposite) return false;
        }
        return true; // "Probably prime"
    }

    
}
