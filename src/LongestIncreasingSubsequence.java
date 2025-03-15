package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws IOException {

        for (int i =1 ; i < 2001; i++){
            System.out.print(i + " ");
        }
        for (int i = 1999; i >=1; i--){
            System.out.print(i + " ");
        }
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        String line1 = reader.readLine();
        String[] str1 = line1.split(" ");
        int A[] = new int[n];
        for (int i = 0; i < n; i++){
            A[i] = Integer.parseInt(str1[i]);
        }
        int m =  Integer.parseInt(reader.readLine());
        String line2 = reader.readLine();
        String[] str2 = line2.split(" ");
        int B[] = new int[m];
        for (int i = 0; i < m; i++){
            B[i] = Integer.parseInt(str2[i]);
        }

        int dp[] = new int[m];
        for (int i = 0; i < m; i++){
            dp[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            int current = 0;
            for (int j = 0; j < m; j++) {
                if (A[i] == B[j])
                    if (current + 1 > dp[j])
                        dp[j] = current + 1;

                if (A[i] > B[j])
                    if (dp[j] > current)
                        current = dp[j];
            }
        }

        int max = 0;
        for (int i=0; i<m; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }


        System.out.println(max);
    }
}