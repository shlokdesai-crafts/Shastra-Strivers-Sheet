import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
    public static String serialize(TreeNode root) {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                sb.append("null ");
            } else {
                sb.append(curr.val).append(" ");
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        return sb.toString().trim();
    }

    public static TreeNode deserialize(String data) {
        if (data == null || data.equals("null") || data.isEmpty()) return null;
        String[] tokens = data.split("\\s+");
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < tokens.length) {
            TreeNode parent = q.poll();
            if (i < tokens.length && !tokens[i].equals("null")) {
                parent.left = new TreeNode(Integer.parseInt(tokens[i]));
                q.add(parent.left);
            }
            i++;
            if (i < tokens.length && !tokens[i].equals("null")) {
                parent.right = new TreeNode(Integer.parseInt(tokens[i]));
                q.add(parent.right);
            }
            i++;
        }
        return root;
    }

    public static String printTree(TreeNode root) {
        if (root == null) return "null";
        List<String> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr != null) {
                res.add(String.valueOf(curr.val));
                q.add(curr.left);
                q.add(curr.right);
            } else {
                res.add("null");
            }
        }
        while (res.size() > 0 && res.get(res.size() - 1).equals("null")) {
            res.remove(res.size() - 1);
        }
        return String.join(" ", res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("null");
            return;
        }
        TreeNode root = deserialize(line.trim());
        String serialized = serialize(root);
        TreeNode reconstructed = deserialize(serialized);
        System.out.println(printTree(reconstructed));
    }
}
