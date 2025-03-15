package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MinLength {
    public static int minSize(String s) {
        Stack<Character> string = new Stack<>();
        for (char character : s.toCharArray()) {
            if (!string.isEmpty()) {
                if (string.peek() == 'B' && character == 'B') {
                    string.pop();
                } else if (string.peek() == 'A' && character == 'B') {
                    string.pop();
                } else {
                    string.push(character);
                }
            } else {
                string.push(character);
            }
        }
        return string.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++){
            System.out.println(minSize(reader.readLine()));
        }
    }
}

