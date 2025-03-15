package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BTPath {
    public static class Node{
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }
    public static boolean hasPath(Node node, int s) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null && node.value == s) {
            return true;
        }
        s -= node.value;
        return hasPath(node.left, s) || hasPath(node.right, s);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        String[] str = line.split(" ");

        if (str == null || str.length == 0 || str[0].equals("-")) {
            System.out.println("NO");
        }
        else {

            Node root = new Node(Integer.parseInt(str[0]));
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);

            int k = 1;
            while (!queue.isEmpty() && k < str.length) {
                Node current = queue.poll();
                if (k < str.length && !str[k].equals("-")) {
                    current.left = new Node(Integer.parseInt(str[k]));
                    queue.offer(current.left);
                }
                k++;
                if (k < str.length && !str[k].equals("-")) {
                    current.right = new Node(Integer.parseInt(str[k]));
                    queue.offer(current.right);
                }
                k++;
            }


            int s = Integer.parseInt(reader.readLine());
            if (hasPath(root,s )){
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }

        }

    }

}
