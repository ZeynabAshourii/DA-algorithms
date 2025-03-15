package src;

import java.util.*;

public class LeastFatigue {
    static class Edge {
        int to, reverse;
        double cost , cap;

        Edge(int to, double cap, double cost, int reverse) {
            this.to = to;
            this.cap = cap;
            this.cost = cost;
            this.reverse = reverse;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static double minCost(List<Edge>[] graph, int source, int sink) {
        int n = graph.length;
        double[] distance = new double[n];
        int[] prevNode = new int[n];
        int[] prevEdge = new int[n];
        double minCost = 0;

        while (true) {
            Arrays.fill(distance, INF);
            distance[source] = 0;

            boolean updated = true;
            for (int i = 0; i < n - 1 && updated; i++) {
                updated = false;
                for (int u = 0; u < n; u++) {
                    if (distance[u] == INF) continue;
                    for (int j = 0; j < graph[u].size(); j++) {
                        Edge edge = graph[u].get(j);
                        if (edge.cap > 0 && distance[edge.to] > distance[u] + edge.cost + 0.0000001) {
                            distance[edge.to] = distance[u] + edge.cost;
                            prevNode[edge.to] = u;
                            prevEdge[edge.to] = j;
                            updated = true;
                        }
                    }
                }
            }
            if (distance[sink] == INF) break;
            double flow = INF;
            for (int v = sink; v != source; v = prevNode[v]) {
                Edge edge = graph[prevNode[v]].get(prevEdge[v]);
                flow = Math.min(flow, edge.cap);
            }

            for (int v = sink; v != source; v = prevNode[v]) {
                Edge edge = graph[prevNode[v]].get(prevEdge[v]);
                edge.cap -= flow;
                graph[v].get(edge.reverse).cap += flow;
            }
            minCost += flow * distance[sink];
        }

        return minCost;
    }

    public static void addEdge(List<Edge>[] graph, int from, int to, double capacity, double cost) {
        graph[from].add(new Edge(to, capacity, cost, graph[to].size()));
        graph[to].add(new Edge(from, 0, -cost, graph[from].size() - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int[] h = new int[k];
        for (int i = 0; i < k; i++){
            h[i] = scanner.nextInt();
        }
        int n = scanner.nextInt();
        int[] m = new int[n];
        for (int i = 0; i < n; i++){
            m[i] = scanner.nextInt();
        }
        int[][] S = new int[k][n];
        for (int i = 0; i < k; i++){
            for (int j = 0; j < n; j++){
                S[i][j] = scanner.nextInt();
            }
        }

        int nodes = k+n+2;
        List<Edge>[] graph = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < k; i++){
            addEdge(graph , 0 , i+1, h[i] , 0);
        }
        for (int j = 0; j < n; j++){
            addEdge(graph , k+1+j ,k+n+1 , m[j] , 0);
        }
        for (int i = 0; i < k; i++){
            for (int j = 0; j < n; j++){
                addEdge(graph , i+1 , k+1+j , Integer.MAX_VALUE , 1.0/S[i][j]);
            }
        }

        System.out.println(minCost(graph, 0, k+n+1));
    }
}
