import java.io.*;
import java.util.*;

/**
 * Problem statement here: http://codeforces.com/problemset/problem/706/C
 *
 * Good luck. This one's a hard problem!
 */
public class FindTheBug6 {
    private static int n;
    private static int[] energy;
    private static String[] words;
    private static String[] reversed;
    // dp[k][0] is minimum energy needed to store words from k to the end
    // dp[k][1] is minimum energy needed to store words from k to the end with words[k] reversed.
    private static long[][] dp;

    private static void readInput() {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        energy = new int[n];
        words = new String[n];
        reversed = new String[n];

        for (int i = 0; i < n; i++) {
            energy[i] = scan.nextInt();
        }

        for (int i = 0; i < n; i++) {
            words[i] = scan.next();
            reversed[i] = new StringBuilder(words[i]).reverse().toString();
        }

    }

    public static void main(String[] args) {
        readInput();
        dp = new long[n][2];
        // Initialize dp to all -1
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Base case
        dp[n - 1][0] = 0;
        dp[n - 1][1] = energy[n - 1];

        // For every index, for every possible flip of the last two
        for (int i = n - 2; i >= 0; i--) {
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < 2; l++) {
                    String cur = k > 0 ? reversed[i] : words[i];
                    String next = l > 0 ? reversed[i + 1] : words[i + 1];

                    // cur <= next
                    if (cur.compareTo(next) <= 0) {
                        long cost = dp[i + 1][l] + (k > 0 ? energy[i] : 0);

                        dp[i][k] = dp[i][k] == -1 ? cost : Math.min(dp[i][k], cost);
                    }
                }
            }
        }

        if (dp[0][0] == -1) {
            System.out.println(dp[0][1]);
        } else if (dp[0][1] == -1) {
            System.out.println(dp[0][0]);
        } else {
            System.out.println(Math.min(dp[0][0], dp[0][1]));
        }
    }
}
