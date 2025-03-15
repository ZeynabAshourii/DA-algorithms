package src;

import java.util.ArrayList;
import java.util.Scanner;

public class MinChangeStep {
    public static int longestPalindromicSubsequence(ArrayList<Integer> block) {
        int n = block.size();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (block.get(i).equals(block.get(j))) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> block = new ArrayList<>();
        block.add(scanner.nextInt());
        for (int i = 1; i < n; i++){
            int x = scanner.nextInt();
            if (!block.get(block.size()-1).equals(x)){
                block.add(x);
            }
        }
        int result = longestPalindromicSubsequence(block);
        System.out.println(result/2 + block.size() - result);

    }
}
