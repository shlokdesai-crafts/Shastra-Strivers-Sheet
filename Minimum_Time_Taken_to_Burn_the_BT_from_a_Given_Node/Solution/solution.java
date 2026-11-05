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

    public static int minTimeToBurn(TreeNode root, int startVal) {
        if (root == null) return 0;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode[] startNode = new TreeNode[1];
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.val == startVal) startNode[0] = curr;
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.add(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.add(curr.right);
            }
        }

        if (startNode[0] == null) return 0;

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> fireQueue = new LinkedList<>();
        fireQueue.add(startNode[0]);
        visited.add(startNode[0]);
        int time = 0;

        while (!fireQueue.isEmpty()) {
            int size = fireQueue.size();
            boolean burnedAny = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = fireQueue.poll();
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    fireQueue.add(curr.left);
                    burnedAny = true;
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    fireQueue.add(curr.right);
                    burnedAny = true;
                }
                TreeNode parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    fireQueue.add(parent);
                    burnedAny = true;
                }
            }
            if (burnedAny) time++;
        }
        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine();
        if (line1 == null || line1.trim().isEmpty()) return;
        String[] tokens = line1.trim().split("\\s+");
        TreeNode root = buildTree(tokens);

        String line2 = br.readLine();
        if (line2 == null || line2.trim().isEmpty()) return;
        int start = Integer.parseInt(line2.trim());

        System.out.println(minTimeToBurn(root, start));
    }
}