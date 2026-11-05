import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
    static int idx = 0;

    static TreeNode bstFromPreorder(int[] preorder, int bound) {
        if (idx == preorder.length || preorder[idx] > bound) return null;
        TreeNode root = new TreeNode(preorder[idx++]);
        root.left = bstFromPreorder(preorder, root.val);
        root.right = bstFromPreorder(preorder, bound);
        return root;
    }

    static String serialize(TreeNode root) {
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
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = line.trim().split("\\s+");
        int[] preorder = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) preorder[i] = Integer.parseInt(tokens[i]);
        idx = 0;
        TreeNode root = bstFromPreorder(preorder, Integer.MAX_VALUE);
        System.out.println(serialize(root));
    }
}
