package src;

import java.util.LinkedList;
import java.util.Scanner;

public class VertexCover {
    static class Edge {
        int u;
        int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Edge> allEdges = new LinkedList<>();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            allEdges.add(new Edge(u , v));
        }
        int numOfVertexCover = 0;
        boolean visit[] = new boolean[n];
        for (int i = 0; i < n; i++){
            visit[i] = false;
        }
        while (!allEdges.isEmpty()){
            Edge randomElement = allEdges.getLast();
            if (!visit[randomElement.u] && ! visit[randomElement.v]){
                visit[randomElement.u] = true;
                visit[randomElement.v] = true;
                numOfVertexCover += 2;
            }
            allEdges.removeLast();
        }
        System.out.println(numOfVertexCover);
    }
}