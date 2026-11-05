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

    static void dfs(TreeNode root, List<Integer> current, List<List<Integer>> allPaths) {
        if (root == null) return;
        current.add(root.val);
        if (root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(current));
        } else {
            if (root.left != null) dfs(root.left, current, allPaths);
            if (root.right != null) dfs(root.right, current, allPaths);
        }
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = line.trim().split("\\s+");
        TreeNode root = buildTree(tokens);

        List<Integer> current = new ArrayList<>();
        List<List<Integer>> allPaths = new ArrayList<>();
        dfs(root, current, allPaths);

        for (List<Integer> p : allPaths) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < p.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(p.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}