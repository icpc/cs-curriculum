import java.io.*;
import java.util.*;

/*
 * The problem statement can be found at http://www.spoj.com/problems/AGGRCOW/
 * 
 * The online judge gives TLE, but the sample test case works. Can you find the
 * bug?
 */
public class FindTheBug4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numStalls = Integer.parseInt(st.nextToken());
            int numCows = Integer.parseInt(st.nextToken());

            int[] stallPositions = new int[numStalls];
            for (int i = 0; i < numStalls; i++) {
                stallPositions[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(stallPositions);
            System.out.println(solve(stallPositions, numCows));
        }
    }

    private static int solve(int[] stallPositions, int numCows) {
        int low = 0;
        int high = stallPositions[stallPositions.length - 1];
        while (low + 1 < high) {
            int mid = low / 2 + high / 2;
            if (works(stallPositions, numCows, mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private static boolean works(int[] stallPositions, int numCows, int minDist) {
        int lastCowPosition = -minDist;
        for (int stallPosition : stallPositions) {
            if (stallPosition - lastCowPosition >= minDist) {
                lastCowPosition = stallPosition;
                numCows = Math.max(0, numCows - 1);
            }
        }

        return numCows == 0;
    }
}
