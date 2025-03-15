package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MinStepsToReachN {

    static class State {
        int value, steps;

        State(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }
    }

    public static int minSteps(int m, int n) {
        if (m >= n) {
            return m-n;
        }

        Queue<State> queue = new LinkedList<>();
        boolean[] visited = new boolean[2 * n + 1];
        queue.add(new State(m, 0));
        visited[m] = true;
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.value == n) {
                return current.steps;
            }
            int nextDouble = current.value * 2;
            int nextDecrement = current.value - 1;
            if (nextDouble <= 2 * n && !visited[nextDouble]) {
                queue.add(new State(nextDouble, current.steps + 1));
                visited[nextDouble] = true;
            }
            if (nextDecrement >= 0 && !visited[nextDecrement]) {
                queue.add(new State(nextDecrement, current.steps + 1));
                visited[nextDecrement] = true;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(minSteps(m, n));
    }
}

