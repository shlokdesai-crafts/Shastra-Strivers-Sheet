import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
    static class NodeInfo {
        int minVal, maxVal, size;
        boolean isBST;
        NodeInfo(int minVal, int maxVal, int size, boolean isBST) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.size = size;
            this.isBST = isBST;
        }
    }

    static NodeInfo helper(TreeNode root) {
        if (root == null) {
            return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }
        NodeInfo left = helper(root.left);
        NodeInfo right = helper(root.right);

        if (left.isBST && right.isBST && left.maxVal < root.val && root.val < right.minVal) {
            int minVal = Math.min(root.val, left.minVal);
            int maxVal = Math.max(root.val, right.maxVal);
            return new NodeInfo(minVal, maxVal, left.size + right.size + 1, true);
        }

        return new NodeInfo(0, 0, Math.max(left.size, right.size), false);
    }

    public static int largestBST(TreeNode root) {
        return helper(root).size;
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
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        TreeNode root = buildTree(line.trim().split("\\s+"));
        System.out.println(largestBST(root));
    }
}
