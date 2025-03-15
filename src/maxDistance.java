package src;

import java.util.*;

public class maxDistance {
    static class myPair {
        int vertex;
        int weight;

        public myPair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    static void shortestPath(int s, ArrayList<ArrayList<myPair>> adj, int n) {
        PriorityQueue<myPair> pq = new PriorityQueue<>(n, Comparator.comparingInt(o -> o.weight));
        int[] dist = new int[n];
        for (int i = 0; i < n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        int maxDistance = 0;
        pq.add(new myPair(0, s));
        dist[s] = 0;
        while (!pq.isEmpty()) {
            int u = pq.poll().weight;
            for (myPair v : adj.get(u)) {
                if (dist[v.vertex] > dist[u] + v.weight) {
                    dist[v.vertex] = dist[u] + v.weight;
                    pq.add(new myPair(dist[v.vertex], v.vertex));
                }
            }
        }
        for (int i = 0; i < n; i++){
            if (dist[i] != Integer.MAX_VALUE) {
                maxDistance = Math.max(maxDistance,dist[i]);
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++){
            if (dist[i] == maxDistance){
                count++;
            }
        }
        System.out.println(s + " " + maxDistance + " " + count);

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int q = scanner.nextInt();
        ArrayList<ArrayList<myPair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for ( int i = 0; i < m; i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            if ( u != v ) {
                adj.get(u).add(new myPair(v, w));
                adj.get(v).add(new myPair(u, w));
            }else {
                adj.get(u).add(new myPair(v, w));
            }
        }
        for (int i = 0; i < q; i++){
            int s = scanner.nextInt();
            shortestPath(s , adj , n);
        }

    }


}
