import java.io.*;
import java.util.*;

/**
 * You're given intervals with a start and end time.
 * Print the size of the largest set of intervals that can be scheduled without overlap.
 *
 * The first line of input begins with T, the number of test cases.
 * Each test case begins with an integer N, the number of intervals that are
 * available. Afterwards follow N lines, each with two integers, representing
 * the start and end of intervals. Two intervals that intersect only at a
 * single point are not considered to overlap.
 *
 * Example Input:
 * 1
 * 4
 * 2 3
 * 3 4
 * 2 6
 * 3 5
 *
 * Example Output:
 * 2
 *
 * Scheduling (2, 3) and (3, 4) gives you two intervals, the best you can do.
 */
public class FindTheBug5 {
    private static class Interval implements Comparable<Interval> {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval other) {
            // Sort by start, then by end
            if (start == other.start) {
                return Integer.compare(end, other.end);
            }

            return Integer.compare(start, other.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer;
        StringBuilder out = new StringBuilder();
        while (T-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            Interval[] intervals = new Interval[n];

            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());

                intervals[i] = new Interval(start, end);
            }

            Arrays.sort(intervals);
            int count = 1;
            int end = intervals[0].end;
            for (int i = 1; i < n; i++) {
                if (intervals[i].start >= end) {
                    end = intervals[i].end;
                    count += 1;
                }
            }

            out.append(count);
            out.append('\n');
        }

        System.out.print(out.toString());
    }
}
