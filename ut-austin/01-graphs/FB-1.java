import java.io.*;
import java.util.*;

/**
 * Write a short description of the bug in this code and a possible fix. The
 * online judge throws a runtime error.
 *
 * Problem description:
 *
 * You are given a 2-dimensional array of 1s and 0s.
 *
 * If a cell contains a 1, then there is an obstacle in that cell and you
 * cannot walk over it. Find the shortest path from the top left corner of the
 * grid to the bottom right. You can only move in the four cardinal directions.
 * It is guaranteed that the top left and bottom right are both 0.
 *
 * If there's no path from the top left to the bottom right, print -1.
 *
 * Examples:
 * Input:
 * 4 4
 * 0 1 1 1
 * 0 0 1 1
 * 1 0 0 1
 * 1 0 0 0
 *
 * Output:
 * 6
 *
 * Input:
 * 3 4
 * 0 1 0 0
 * 0 1 0 0
 * 1 0 0 0
 *
 * Output:
 * -1
 */

public class FindTheBug1 {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static class State {
        int x, y, moves;

        public State(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        boolean[][] grid = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scan.nextInt() == 1;
            }
        }

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 0));

        boolean solved = false;
        // boolean arrays default to false
        boolean[][] seen = new boolean[n][m];

        while (!queue.isEmpty()) {
            State state = queue.remove();

            if (state.x == n - 1 && state.y == m - 1) {
                System.out.println(state.moves);
                solved = true;
                break;
            }

            // Don't continue if we've been down this path or it's blocked
            if (seen[state.x][state.y] || grid[state.x][state.y]) {
                continue;
            }

            seen[state.x][state.y] = true;

            for (int d = 0; d < 4; d++) {
                int newX = state.x + dx[d];
                int newY = state.y + dy[d];

                queue.add(new State(newX, newY, state.moves + 1));
            }
        }

        if (!solved) {
            System.out.println(-1);
        }
    }
}
