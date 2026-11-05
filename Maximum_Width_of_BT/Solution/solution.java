import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
    static class Pair {
        TreeNode node;
        long idx;
        Pair(TreeNode node, long idx) {
            this.node = node;
            this.idx = idx;
        }
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

    public static long widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        long ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            long minIdx = q.peek().idx;
            long first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                long curId = p.idx - minIdx;
                if (i == 0) first = curId;
                if (i == size - 1) last = curId;

                if (p.node.left != null) q.add(new Pair(p.node.left, 2 * curId + 1));
                if (p.node.right != null) q.add(new Pair(p.node.right, 2 * curId + 2));
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = line.trim().split("\\s+");
        TreeNode root = buildTree(tokens);
        System.out.println(widthOfBinaryTree(root));
    }
}