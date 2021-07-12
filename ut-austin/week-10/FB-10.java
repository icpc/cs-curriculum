import java.io.*;
import java.util.*;

// http://www.spoj.com/problems/POTHOLE/
public class FindTheBug10 {
    // Edits the maxFlow array
    // returns if there is a path from source to sink
    public static boolean bfs(int[][] graph, int source, int sink, int[] previous, int[] maxFlow) {
        Arrays.fill(previous, -1);
        Arrays.fill(maxFlow, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        maxFlow[source] = Integer.MAX_VALUE;

        boolean foundSink = false;

        while (!queue.isEmpty()) {
            int location = queue.remove();
            int flow = maxFlow[location];

            foundSink |= (location == sink);

            for (int nextLocation = 0; nextLocation < graph.length; nextLocation++) {
                int capacity = graph[location][nextLocation];

                // Only go to unvisited nodes with valid edges
                if (previous[nextLocation] == -1 && capacity > 0) {
                    previous[nextLocation] = location;
                    maxFlow[nextLocation] = Math.min(flow, capacity);

                    queue.add(nextLocation);
                }
            }
        }

        return foundSink;
    }

    // Note: edits the graph data structure
    public static int fordFulkerson(int[][] graph, int source, int sink) {
        int flow = 0;

        // The max flow to every node.
        int[] maxFlow = new int[graph.length];
        int[] previous = new int[graph.length];

        // While there's a path from source to sink
        while (bfs(graph, source, sink, previous, maxFlow)) {
            // Find the maximum amount of flow that can reach the sink
            int currentFlow = maxFlow[sink];
            flow += currentFlow;

            // Update all the edge capacities
            int node = sink;
            while (node != source) {
                int previousNode = previous[node];
                // Update the residual graph
                graph[previousNode][node] -= currentFlow;
                graph[node][previousNode] += currentFlow;

                node = previousNode;
            }
        }

        return flow;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numCases = scan.nextInt();

        while (numCases-- > 0) {
            int numChambers = scan.nextInt();
            // Store the graph as an adjacency matrix for simplicity's sake.
            int[][] graph = new int[numChambers][numChambers];

            for (int start = 0; start < numChambers - 1; start++) {
                int numConnections = scan.nextInt();

                for (int j = 0; j < numConnections; j++) {
                    int end = scan.nextInt();
                    end--;

                    // only one person can go through each edge.
                    graph[start][end] = 1;
                }
            }

            System.out.println(fordFulkerson(graph, 0, numChambers - 1));
        }
    }
}
