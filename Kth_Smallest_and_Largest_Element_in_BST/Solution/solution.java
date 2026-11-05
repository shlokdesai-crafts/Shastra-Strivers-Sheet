import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
    static int countSmall = 0, countLarge = 0;
    static int kthSmall = -1, kthLarge = -1;

    static void inorder(TreeNode root, int k) {
        if (root == null || countSmall >= k) return;
        inorder(root.left, k);
        countSmall++;
        if (countSmall == k) {
            kthSmall = root.val;
            return;
        }
        inorder(root.right, k);
    }

    static void revInorder(TreeNode root, int k) {
        if (root == null || countLarge >= k) return;
        revInorder(root.right, k);
        countLarge++;
        if (countLarge == k) {
            kthLarge = root.val;
            return;
        }
        revInorder(root.left, k);
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
        int k = Integer.parseInt(l2.trim());

        countSmall = 0; countLarge = 0;
        kthSmall = -1; kthLarge = -1;
        inorder(root, k);
        revInorder(root, k);
        System.out.println(kthSmall + " " + kthLarge);
    }
}
