import java.io.*;
import java.util.*;

// Help! I'm passing samples, but getting Wrong Answer on the full test suite.
//
// https://icpcarchive.ecs.baylor.edu/index.php?option=onlinejudge&page=show_problem&problem=4150
public class FindTheBug9 {
    public static class SegmentTreeNode {
        final static int DEFAULT = 1;
        private int val, l, r;
        SegmentTreeNode left, right;

        public static int getSign(int x) {
            if (x == 0) return 0;
            else if (x < 0) return -1;
            else return 1;
        }

        public SegmentTreeNode(int[] arr, int l, int r) {
            this.l = l;
            this.r = r;
            if (l == r) {
                this.val = arr[l];
            } else {
                int mid = (l + r) / 2;

                this.left = new SegmentTreeNode(arr, l, mid);
                this.right = new SegmentTreeNode(arr, mid + 1, r);
                this.val = left.val * right.val;
            }
        }

        // Find the product between a and b, inclusive, 0 indexed
        public int query(int a, int b) {
            if (b < l || r < a) {
                return DEFAULT;
            }
            if (a <= l && r <= b) {
                return this.val;
            }

            return left.query(a, b) * right.query(a, b);
        }

        public int update(int index, int value) {
            if (index < l || index > r) {
                return this.val;
            }

            if (l == index && r == index) {
                this.val = value;
                return this.val;
            }

            this.val = left.update(index, value) * right.update(index, value);
            return this.val;
        }
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line);

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            SegmentTreeNode root = new SegmentTreeNode(arr, 0, n - 1);
            // Faster than printing char by char
            StringBuilder stringBuilder = new StringBuilder();
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                // 1-indexed -> 0-indexed
                char type = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (type == 'C') {
                    root.update(a - 1, b);
                } else {
                    int result = root.query(a - 1, b - 1);
                    if (result == 0) {
                        stringBuilder.append('0');
                    } else if (result < 0) {
                        stringBuilder.append('-');
                    } else {
                        stringBuilder.append('+');
                    }
                }
            }

            System.out.println(stringBuilder.toString());
        }
    }
}
