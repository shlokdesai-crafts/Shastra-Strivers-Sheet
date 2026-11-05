# Editorial: Lowest Common Ancestor in Binary Tree

## 1. Problem Overview
Given a binary tree with unique values and two target nodes `p` and `q`, we need to find the lowest node in the tree that has both `p` and `q` as descendants.

## 2. Intuition & Key Observation
We can utilize a post-order / bottom-up recursive approach:
- If the current node is `null`, or equal to `p`, or equal to `q`, we return the current node.
- Otherwise, we search in both the left and right subtrees:
  - If both left and right recursive calls return a non-null node, it means `p` is in one subtree and `q` is in the other subtree. Hence, the current node is their lowest common ancestor!
  - If only one recursive call returns non-null, that subtree contains either the LCA or one of the nodes, so we pass that result up.
  - If both return `null`, neither node is present in this subtree, so return `null`.

## 3. Algorithm
```text
function lowestCommonAncestor(root, p, q):
    if root is null or root.val == p or root.val == q:
        return root
    
    left = lowestCommonAncestor(root.left, p, q)
    right = lowestCommonAncestor(root.right, p, q)
    
    if left is not null and right is not null:
        return root
    
    return left if left is not null else right
```

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N)$ where $N$ is the number of nodes in the binary tree. In the worst case, every node is visited once.
- **Space Complexity:** $\mathcal{O}(H)$ recursive stack space where $H$ is the height of the tree. $\mathcal{O}(N)$ in the worst case (skewed tree) and $\mathcal{O}(\log N)$ for balanced trees.

## 5. Implementations

### C++
```cpp
#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <queue>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left, *right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

TreeNode* buildTree(const vector<string>& tokens) {
    if (tokens.empty() || tokens[0] == "null") return nullptr;
    TreeNode* root = new TreeNode(stoi(tokens[0]));
    queue<TreeNode*> q;
    q.push(root);
    size_t i = 1;
    while (!q.empty() && i < tokens.size()) {
        TreeNode* curr = q.front();
        q.pop();
        if (i < tokens.size()) {
            if (tokens[i] != "null") {
                curr->left = new TreeNode(stoi(tokens[i]));
                q.push(curr->left);
            }
            i++;
        }
        if (i < tokens.size()) {
            if (tokens[i] != "null") {
                curr->right = new TreeNode(stoi(tokens[i]));
                q.push(curr->right);
            }
            i++;
        }
    }
    return root;
}

TreeNode* lowestCommonAncestor(TreeNode* root, int p, int q) {
    if (!root || root->val == p || root->val == q) return root;
    TreeNode* left = lowestCommonAncestor(root->left, p, q);
    TreeNode* right = lowestCommonAncestor(root->right, p, q);
    if (left && right) return root;
    return left ? left : right;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string line1;
    if (!getline(cin, line1) || line1.empty()) return 0;
    stringstream ss(line1);
    string token;
    vector<string> tokens;
    while (ss >> token) tokens.push_back(token);
    TreeNode* root = buildTree(tokens);

    int p, q;
    if (cin >> p >> q) {
        TreeNode* lca = lowestCommonAncestor(root, p, q);
        if (lca) cout << lca->val << "\n";
    }
    return 0;
}
```

### Java
```java
import java.io.*;
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class Solution {
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

    static TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine();
        if (line1 == null || line1.trim().isEmpty()) return;
        String[] tokens = line1.trim().split("\\s+");
        TreeNode root = buildTree(tokens);

        String line2 = br.readLine();
        if (line2 == null || line2.trim().isEmpty()) return;
        String[] pq = line2.trim().split("\\s+");
        int p = Integer.parseInt(pq[0]);
        int q = Integer.parseInt(pq[1]);

        TreeNode lca = lowestCommonAncestor(root, p, q);
        if (lca != null) {
            System.out.println(lca.val);
        }
    }
}
```

### Python
```python
import sys
from collections import deque

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def build_tree(tokens):
    if not tokens or tokens[0] == "null":
        return None
    root = TreeNode(int(tokens[0]))
    q = deque([root])
    i = 1
    while q and i < len(tokens):
        curr = q.popleft()
        if i < len(tokens):
            if tokens[i] != "null":
                curr.left = TreeNode(int(tokens[i]))
                q.append(curr.left)
            i += 1
        if i < len(tokens):
            if tokens[i] != "null":
                curr.right = TreeNode(int(tokens[i]))
                q.append(curr.right)
            i += 1
    return root

def lowest_common_ancestor(root, p, q):
    if not root or root.val == p or root.val == q:
        return root
    left = lowest_common_ancestor(root.left, p, q)
    right = lowest_common_ancestor(root.right, p, q)
    if left and right:
        return root
    return left if left else right

def main():
    lines = sys.stdin.read().strip().splitlines()
    if not lines:
        return
    tokens = lines[0].split()
    root = build_tree(tokens)
    pq = lines[1].split()
    p, q = int(pq[0]), int(pq[1])
    lca = lowest_common_ancestor(root, p, q)
    if lca:
        print(lca.val)

if __name__ == "__main__":
    main()
```
