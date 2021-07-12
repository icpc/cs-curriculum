import java.io.*;
import java.util.*;

/**
 * The problem can be found here: http://www.spoj.com/problems/EZDIJKST/
 *
 * I'm getting WA (Wrong Answer) on the online judge. Can you figure out why?
 */
public class FindTheBug2 {
    public static class Node implements Comparable<Node> {
        int id;
        List<Edge> neighbors;
        long cost;
        boolean visited;

        public Node(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
            this.cost = Long.MAX_VALUE;
            this.visited = false;
        }

        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    public static class Edge {
        Node dest;
        int weight;

        public Edge(Node dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void dijkstra(List<Node> graph, Node source) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        source.cost = 0;
        source.visited = true;
        pq.add(source);

        while (!pq.isEmpty()) {
            Node current = pq.remove();

            for (Edge edge : current.neighbors) {
                Node nextNode = edge.dest;
                long newCost = current.cost + edge.weight;

                if (!nextNode.visited && newCost < nextNode.cost) {
                    nextNode.cost = newCost;
                    nextNode.visited = true;
                    pq.add(nextNode);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final Scanner scan = new Scanner(System.in);
        final int numCases = scan.nextInt();

        for (int caseNum = 0; caseNum < numCases; caseNum++) {
            // Read in input
            final int numVertices = scan.nextInt();
            final int numEdges = scan.nextInt();

            final List<Node> graph = new ArrayList<>(numVertices);
            for (int i = 0; i < numVertices; i++) {
                graph.add(new Node(i));
            }

            for (int i = 0; i < numEdges; i++) {
                // Convert to 0 indexing
                final int start = scan.nextInt() - 1;
                final int end = scan.nextInt() - 1;
                final int weight = scan.nextInt();

                final Node nodeStart = graph.get(start);
                final Node nodeEnd = graph.get(end);

                nodeStart.neighbors.add(new Edge(nodeEnd, weight));
            }

            // Convert to 0 indexing
            final int source = scan.nextInt() - 1;
            final int sink = scan.nextInt() - 1;

            // All of the costs will be saved in the node objects
            dijkstra(graph, graph.get(source));

            long ans = graph.get(sink).cost;
            if (ans == Long.MAX_VALUE) {
                System.out.println("NO");
            } else {
                System.out.println(ans);
            }
        }
    }
}
