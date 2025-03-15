package src;

import java.util.*;
public class MaxProduct {
    static int currentMax;
    static int dfs(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        int max1 = 0;
        int max2 = 0;
        int total = 0;
        for (int neighbor : adj.get(u)) {
            if (neighbor == v) continue;

            total = Math.max(total, dfs(adj, neighbor, u));

            if (currentMax > max1) {
                max2 = max1;
                max1 = currentMax;
            } else {
                max2 = Math.max(max2, currentMax);
            }
        }
        total = Math.max(total, max1 + max2);
        currentMax = max1 + 1;

        return total;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1 ; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int result = Integer.MIN_VALUE;
        int path1 = 0;
        int path2 = 0;
        for (int i = 0; i < n; i++) {
            for (int neighbor : adj.get(i)) {
                currentMax = 0;
                path1 = dfs(adj, neighbor, i);

                currentMax = 0;
                path2 = dfs(adj, i, neighbor);

                result = Math.max(result, path1 * path2);
            }
        }
        System.out.println(result);
    }
}