# Editorial: Print Root to Leaf Path in Binary Tree

## 1. Problem Overview
Given a binary tree, we need to collect and output all root-to-leaf paths in the tree.

## 2. Intuition & Key Observation
- A root-to-leaf path starts at the root and finishes at any node that has no left or right child.
- We can explore all paths using **Depth-First Search (DFS) / Backtracking**:
  - Maintain a dynamic list (e.g. `currentPath`) representing the path from root to current node.
  - When visiting a node, append its value to `currentPath`.
  - If the node is a leaf, record a snapshot of `currentPath`.
  - Otherwise, recursively visit `node.left` and `node.right`.
  - When backtracking (leaving the node), remove the node's value from `currentPath`.

## 3. Algorithm
1. Initialize an empty list of paths: `allPaths`.
2. Define recursive helper `findPaths(node, currentPath)`:
   - If `node == null`, return.
   - Append `node.val` to `currentPath`.
   - If `node.left == null && node.right == null`:
     - Add a copy of `currentPath` to `allPaths`.
   - Else:
     - If `node.left != null`, call `findPaths(node.left, currentPath)`.
     - If `node.right != null`, call `findPaths(node.right, currentPath)`.
   - Pop last element from `currentPath`.
3. Call `findPaths(root, currentPath)`.
4. Output each path in `allPaths` line by line.

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N)$ where $N$ is the number of nodes. Each node is visited once, and copying paths for each leaf node takes $\mathcal{O}(H)$ where $H$ is the depth. Total time is $\mathcal{O}(N)$.
- **Space Complexity:** $\mathcal{O}(H)$ for the recursion call stack and current path, where $H$ is the height of the tree.

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

void getPaths(TreeNode* root, vector<int>& path, vector<vector<int>>& allPaths) {
    if (!root) return;
    path.push_back(root->val);
    if (!root->left && !root->right) {
        allPaths.push_back(path);
    } else {
        if (root->left) getPaths(root->left, path, allPaths);
        if (root->right) getPaths(root->right, path, allPaths);
    }
    path.pop_back();
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string line;
    if (!getline(cin, line) || line.empty()) return 0;
    stringstream ss(line);
    string token;
    vector<string> tokens;
    while (ss >> token) tokens.push_back(token);
    TreeNode* root = buildTree(tokens);

    vector<int> path;
    vector<vector<int>> allPaths;
    getPaths(root, path, allPaths);

    for (const auto& p : allPaths) {
        for (size_t i = 0; i < p.size(); i++) {
            cout << p[i] << (i + 1 == p.size() ? "" : " ");
        }
        cout << "\n";
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

def root_to_leaf_paths(root):
    if not root:
        return []
    all_paths = []
    current_path = []

    def dfs(node):
        if not node:
            return
        current_path.append(node.val)
        if not node.left and not node.right:
            all_paths.append(list(current_path))
        else:
            if node.left:
                dfs(node.left)
            if node.right:
                dfs(node.right)
        current_path.pop()

    dfs(root)
    return all_paths

def main():
    tokens = sys.stdin.read().split()
    if not tokens:
        return
    root = build_tree(tokens)
    paths = root_to_leaf_paths(root)
    for p in paths:
        print(*(p))

if __name__ == "__main__":
    main()
```
