package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class minGraphEdges {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line1 = reader.readLine();
            String[] str1 = line1.split(" ");
            for (int j = 0; j < n; j++){
                distance[i][j] = Integer.parseInt(str1[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distance[i][j] != distance[j][i]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        Set<String> edges = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean isEdge = true;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j && distance[i][j] == distance[i][k] + distance[k][j]) {
                        isEdge = false;
                        break;
                    }
                }
                if (isEdge) {
                    edges.add(i + "," + j);
                }
            }
        }
        System.out.println(edges.size());
    }
}
