import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
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

    static void dfsRight(TreeNode root, int level, List<Integer> res) {
        if (root == null) return;
        if (res.size() == level) res.add(root.val);
        dfsRight(root.right, level + 1, res);
        dfsRight(root.left, level + 1, res);
    }

    static void dfsLeft(TreeNode root, int level, List<Integer> res) {
        if (root == null) return;
        if (res.size() == level) res.add(root.val);
        dfsLeft(root.left, level + 1, res);
        dfsLeft(root.right, level + 1, res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = line.trim().split("\\s+");
        TreeNode root = buildTree(tokens);

        List<Integer> rightView = new ArrayList<>();
        List<Integer> leftView = new ArrayList<>();
        dfsRight(root, 0, rightView);
        dfsLeft(root, 0, leftView);

        for (int i = 0; i < rightView.size(); i++) {
            System.out.print(rightView.get(i) + (i + 1 == rightView.size() ? "" : " "));
        }
        System.out.println();
        for (int i = 0; i < leftView.size(); i++) {
            System.out.print(leftView.get(i) + (i + 1 == leftView.size() ? "" : " "));
        }
        System.out.println();
    }
}