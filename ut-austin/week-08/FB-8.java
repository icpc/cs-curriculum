import java.io.*;
import java.util.*;

/**
 * Problem is here: http://www.spoj.com/problems/UCV2013A/
 *
 * I'm not even passing one of the samples here... :(
 */
public class FindTheBug7 {
    private static final long MOD = 1000000007;

    /**
     * Computes base^exp modulo MOD
     *
     * We can write a^b as a summation of a^k where
     * k is a power of two
     * There's only O(log(b)) of these terms
     * Given a^k, computing a^(2k) is as simple as (a^k)^2 
     *
     * That said, I'm willing to bet my bug is in here...
     */
    public static long fastExp(long base, long exp) {
        if (exp == 1) return base;
        long ans = fastExp(base * base, exp / 2);

        if (exp % 2 == 1) {
            ans = (base * ans) % MOD;
        }

        return ans;
    }

    /**
     * Compute the inverse of a mod MOD
     * Only works for prime mods
     * For more information, see
     * https://en.wikipedia.org/wiki/Fermat%27s_little_theorem
     */
    public static long inverse(long a) {
        return fastExp(a, MOD - 2);
    }

    public static long solve(long N, long L) {
        // Answer is N^1 + N^2 + N^3 + ... + N^L
        // Sum               = N^1 + N^2 + N^3 + ... + N^L
        // Sum * N           = N^2 + N^3 + N^4 + ... + N^(L + 1)
        // Sum * N + N       = N + N^2 + N^3 + N^4 + ... + N^(L + 1)
        // Sum * N + N       = Sum + N^(L + 1)
        // Sum * (N - 1) + N = N^(L + 1)
        // Sum               = (N^(L + 1) - N) / (N - 1)

        long numerator = fastExp(N, L + 1) - N;
        // Any time a value can be negative in a mod problem, fix it ASAP
        // For example 3^4 (mod 10) = 1. Subtract 3 to get -2.
        numerator += MOD;
        numerator %= MOD;

        long denominatorInv = inverse(N - 1);

        return (numerator * denominatorInv) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            if (N == 0 && L == 0) {
                break;
            }

            System.out.println(solve(N, L));
        }
    }
}
