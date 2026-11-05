import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class solution {
    static Map<Integer, Integer> inMap = new HashMap<>();

    static TreeNode build(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int inRoot = inMap.get(rootVal);
        int numsLeft = inRoot - inStart;

        root.left = build(postorder, postStart, postStart + numsLeft - 1, inorder, inStart, inRoot - 1);
        root.right = build(postorder, postStart + numsLeft, postEnd - 1, inorder, inRoot + 1, inEnd);
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
        String l1 = br.readLine();
        String l2 = br.readLine();
        if (l1 == null || l2 == null) return;
        String[] postStr = l1.trim().split("\\s+");
        String[] inStr = l2.trim().split("\\s+");
        int n = postStr.length;
        int[] postorder = new int[n];
        int[] inorder = new int[n];
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(postStr[i]);
            inorder[i] = Integer.parseInt(inStr[i]);
            inMap.put(inorder[i], i);
        }
        TreeNode root = build(postorder, 0, n - 1, inorder, 0, n - 1);
        System.out.println(serialize(root));
    }
}
