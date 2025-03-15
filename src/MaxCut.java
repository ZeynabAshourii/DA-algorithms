package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxCut {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        int[][] T = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] strings = reader.readLine().split(" ");
            T[i][0] = Integer.parseInt(strings[0]);
            T[i][1] = Integer.parseInt(strings[1]);
        }
        if (n == 1) {
            System.out.println(1);
        }else {
            int count = 1;
            int last = T[0][0];
            for (int i = 1; i < n - 1; i++) {
                int x = T[i][0];
                int h = T[i][1];
                if (x - h > last) {
                    count++;
                    last = x;
                }
                else if (x + h < T[i + 1][0]) {
                    count++;
                    last = x + h;
                } else {
                    last = x;
                }
            }
            count++;
            System.out.println(count);
        }
    }
}

