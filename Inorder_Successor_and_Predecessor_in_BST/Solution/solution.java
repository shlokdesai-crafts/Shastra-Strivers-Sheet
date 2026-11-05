import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
    static int findSuccessor(TreeNode root, int key) {
        int succ = -1;
        while (root != null) {
            if (root.val > key) {
                succ = root.val;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return succ;
    }

    static int findPredecessor(TreeNode root, int key) {
        int pred = -1;
        while (root != null) {
            if (root.val < key) {
                pred = root.val;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return pred;
    }

    public static TreeNode buildTree(String[] tokens) {
        if (tokens.length == 0 || tokens[0].equals("null") || tokens[0].isEmpty()) return null;
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < tokens.length) {
            TreeNode curr = q.poll();
            if (i < tokens.length) {
                if (!tokens[i].equals("null")) {
                    curr.left = new TreeNode(Integer.parseInt(tokens[i]));
                    q.add(curr.left);
                }
                i++;
            }
            if (i < tokens.length) {
                if (!tokens[i].equals("null")) {
                    curr.right = new TreeNode(Integer.parseInt(tokens[i]));
                    q.add(curr.right);
                }
                i++;
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String l1 = br.readLine();
        String l2 = br.readLine();
        if (l1 == null || l2 == null) return;
        TreeNode root = buildTree(l1.trim().split("\\s+"));
        int key = Integer.parseInt(l2.trim());
        int pred = findPredecessor(root, key);
        int succ = findSuccessor(root, key);
        System.out.println(pred + " " + succ);
    }
}
