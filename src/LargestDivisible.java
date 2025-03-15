package src;

import java.util.*;

public class LargestDivisible {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i =0; i < n; i++){
            arr[i] = scanner.nextInt();
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j*j <= arr[i]; j++) {
                if (arr[i] % j == 0) {
                    count.put(j, count.getOrDefault(j, 0) + 1);
                    if (j != arr[i]/j) {
                        count.put(arr[i] / j, count.getOrDefault(arr[i] / j, 0) + 1);
                    }
                }
            }
        }
        int max = -1;
        for (int divisor : count.keySet()) {
            if (count.get(divisor) >= (n+1)/ 2) {
                max = Math.max(max, divisor);
            }
        }
        System.out.println(max);
    }
}
