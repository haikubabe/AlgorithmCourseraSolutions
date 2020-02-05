package divide_and_conquer.week1;

import java.math.BigInteger;

public class Karatsuba
{

    private static BigInteger karatsuba(BigInteger x, BigInteger y) {
        if (x.compareTo(BigInteger.valueOf(10)) < 0 && y.compareTo(BigInteger.valueOf(10)) < 0)
            return x.multiply(y);

        int lenx = String.valueOf(x).length();
        int leny = String.valueOf(y).length();
        int n = Math.max(lenx, leny);
        int m = n / 2;

        BigInteger a = x.divide(BigInteger.valueOf(10).pow(m));
        BigInteger b = x.mod(BigInteger.valueOf(10).pow(m));
        BigInteger c = y.divide(BigInteger.valueOf(10).pow(m));
        BigInteger d = y.mod(BigInteger.valueOf(10).pow(m));

        BigInteger z2 = karatsuba(a, c);
        BigInteger z0 = karatsuba(b, d);
        BigInteger z1 = karatsuba(a.add(b), c.add(d));
        BigInteger z3 = z1.subtract(z2).subtract(z0);

        return z2.multiply(BigInteger.valueOf(10).pow(2 * m)).add(z0).add(z3.multiply(BigInteger.valueOf(10).pow(m)));
    }

    public static void main(String[] args) {
        BigInteger x = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger y = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        System.out.println(karatsuba(x, y));
    }
}
