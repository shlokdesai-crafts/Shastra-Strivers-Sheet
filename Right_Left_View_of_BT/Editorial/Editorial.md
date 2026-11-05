# Editorial: Right and Left View of Binary Tree

## 1. Problem Overview
We need to capture the visible outline of a binary tree from both the right side and the left side.

## 2. Intuition & Key Observation
At each depth (level) of the tree:
- The **Right View** consists of the rightmost node.
- The **Left View** consists of the leftmost node.

We can solve both views using either:
1. **Recursive DFS:**
   - For **Right View**, traverse **Root $\to$ Right $\to$ Left**. The first time we reach a new level (`level == result.size()`), that node is guaranteed to be the rightmost node at that level.
   - For **Left View**, traverse **Root $\to$ Left $\to$ Right**. The first time we reach a new level (`level == result.size()`), that node is guaranteed to be the leftmost node at that level.
2. **Iterative BFS (Level Order):**
   - At each level with $K$ nodes in the queue:
     - The first node (index `0`) is the Left View node.
     - The last node (index `K - 1`) is the Right View node.

DFS is exceptionally concise, requiring only $\mathcal{O}(H)$ call stack space.

## 3. Algorithm (Recursive DFS)
### Right View:
```text
function dfsRight(node, level, rightList):
    if node is null: return
    if level == rightList.size():
        rightList.add(node.val)
    dfsRight(node.right, level + 1, rightList)
    dfsRight(node.left, level + 1, rightList)
```

### Left View:
```text
function dfsLeft(node, level, leftList):
    if node is null: return
    if level == leftList.size():
        leftList.add(node.val)
    dfsLeft(node.left, level + 1, leftList)
    dfsLeft(node.right, level + 1, leftList)
```

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N)$ where $N$ is the number of nodes in the binary tree. Every node is visited once.
- **Space Complexity:** $\mathcal{O}(H)$ auxiliary space for the recursive call stack, where $H$ is the tree height. In the worst case (skewed tree), $H = N$; for a balanced tree, $H = \log N$.

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

void dfsRight(TreeNode* root, int level, vector<int>& res) {
    if (!root) return;
    if (res.size() == (size_t)level) res.push_back(root->val);
    dfsRight(root->right, level + 1, res);
    dfsRight(root->left, level + 1, res);
}

void dfsLeft(TreeNode* root, int level, vector<int>& res) {
    if (!root) return;
    if (res.size() == (size_t)level) res.push_back(root->val);
    dfsLeft(root->left, level + 1, res);
    dfsLeft(root->right, level + 1, res);
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

    vector<int> rightView, leftView;
    dfsRight(root, 0, rightView);
    dfsLeft(root, 0, leftView);

    for (size_t i = 0; i < rightView.size(); i++) {
        cout << rightView[i] << (i + 1 == rightView.size() ? "" : " ");
    }
    cout << "\n";
    for (size_t i = 0; i < leftView.size(); i++) {
        cout << leftView[i] << (i + 1 == leftView.size() ? "" : " ");
    }
    cout << "\n";
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

def get_views(root):
    right_view = []
    left_view = []

    def dfs_right(node, level):
        if not node:
            return
        if len(right_view) == level:
            right_view.append(node.val)
        dfs_right(node.right, level + 1)
        dfs_right(node.left, level + 1)

    def dfs_left(node, level):
        if not node:
            return
        if len(left_view) == level:
            left_view.append(node.val)
        dfs_left(node.left, level + 1)
        dfs_left(node.right, level + 1)

    dfs_right(root, 0)
    dfs_left(root, 0)
    return right_view, left_view

def main():
    tokens = sys.stdin.read().split()
    if not tokens:
        return
    root = build_tree(tokens)
    rv, lv = get_views(root)
    print(*(rv))
    print(*(lv))

if __name__ == "__main__":
    main()
```
