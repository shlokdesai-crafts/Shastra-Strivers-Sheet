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

    public static List<Integer> distanceK(TreeNode root, int targetVal, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        if (k == 0) {
            res.add(targetVal);
            return res;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode[] targetNode = new TreeNode[1];
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.val == targetVal) targetNode[0] = curr;
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                q.add(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                q.add(curr.right);
            }
        }

        if (targetNode[0] == null) return res;

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> qDist = new LinkedList<>();
        qDist.add(targetNode[0]);
        visited.add(targetNode[0]);
        int dist = 0;

        while (!qDist.isEmpty()) {
            if (dist == k) break;
            int size = qDist.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = qDist.poll();
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    qDist.add(curr.left);
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    qDist.add(curr.right);
                }
                TreeNode parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    qDist.add(parent);
                }
            }
            dist++;
        }

        while (!qDist.isEmpty()) {
            res.add(qDist.poll().val);
        }
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine();
        if (line1 == null || line1.trim().isEmpty()) return;
        String[] tokens = line1.trim().split("\\s+");
        TreeNode root = buildTree(tokens);

        String line2 = br.readLine();
        String line3 = br.readLine();
        if (line2 == null || line3 == null) return;
        int target = Integer.parseInt(line2.trim());
        int k = Integer.parseInt(line3.trim());

        List<Integer> res = distanceK(root, target, k);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(res.get(i));
        }
        System.out.println(sb.toString());
    }
}