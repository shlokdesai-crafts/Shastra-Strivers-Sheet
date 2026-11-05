# Editorial: Count Total Nodes in a Complete Binary Tree

## 1. Problem Overview
Given a complete binary tree, determine the total count of nodes in $\mathcal{O}(\log^2 N)$ time.

## 2. Intuition & Key Observation
A naive traversal (e.g. standard recursion or BFS) visits all $N$ nodes taking $\mathcal{O}(N)$ time. However, we can exploit the complete binary tree property:
- For any subtree, if we calculate:
  - `leftHeight`: The height obtained by traversing solely along left children (`node = node.left`).
  - `rightHeight`: The height obtained by traversing solely along right children (`node = node.right`).
- **Case 1: `leftHeight == rightHeight`**
  The subtree is a **perfect binary tree** of height $h$. The number of nodes in a perfect binary tree of height $h$ is mathematically:
  $$2^h - 1 = (1 \ll h) - 1$$
  This calculation takes only $\mathcal{O}(h)$ time without having to traverse the subtree!
- **Case 2: `leftHeight != rightHeight`**
  The subtree is not completely filled at the bottom. We recursively compute:
  $$1 + \text{countNodes}(\text{root.left}) + \text{countNodes}(\text{root.right})$$
  In this recursive step, at least one of the two subtrees is guaranteed to be a perfect binary tree and will terminate in $\mathcal{O}(h)$ time!

## 3. Algorithm
```text
function getLeftHeight(node):
    height = 0
    while node is not null:
        height++
        node = node.left
    return height

function getRightHeight(node):
    height = 0
    while node is not null:
        height++
        node = node.right
    return height

function countNodes(root):
    if root is null: return 0
    lh = getLeftHeight(root)
    rh = getRightHeight(root)
    if lh == rh:
        return (1 << lh) - 1
    return 1 + countNodes(root.left) + countNodes(root.right)
```

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(\log^2 N)$. The height of the complete binary tree is $\mathcal{O}(\log N)$. At each level of recursion, finding height takes $\mathcal{O}(\log N)$ operations. Since one subtree is always perfect and solved immediately, the recursion depth is at most $\mathcal{O}(\log N)$. Total time is $\mathcal{O}(\log N \times \log N) = \mathcal{O}(\log^2 N)$, far superior to $\mathcal{O}(N)$!
- **Space Complexity:** $\mathcal{O}(\log N)$ for the recursive call stack space.

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

int getLeftHeight(TreeNode* node) {
    int h = 0;
    while (node) {
        h++;
        node = node->left;
    }
    return h;
}

int getRightHeight(TreeNode* node) {
    int h = 0;
    while (node) {
        h++;
        node = node->right;
    }
    return h;
}

int countNodes(TreeNode* root) {
    if (!root) return 0;
    int lh = getLeftHeight(root);
    int rh = getRightHeight(root);
    if (lh == rh) return (1 << lh) - 1;
    return 1 + countNodes(root->left) + countNodes(root->right);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string line;
    if (!getline(cin, line) || line.empty()) {
        cout << 0 << "\n";
        return 0;
    }
    stringstream ss(line);
    string token;
    vector<string> tokens;
    while (ss >> token) tokens.push_back(token);
    TreeNode* root = buildTree(tokens);
    cout << countNodes(root) << "\n";
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

    static int getLeftHeight(TreeNode node) {
        int h = 0;
        while (node != null) {
            h++;
            node = node.left;
        }
        return h;
    }

    static int getRightHeight(TreeNode node) {
        int h = 0;
        while (node != null) {
            h++;
            node = node.right;
        }
        return h;
    }

    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        int lh = getLeftHeight(root);
        int rh = getRightHeight(root);
        if (lh == rh) return (1 << lh) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        String[] tokens = line.trim().split("\\s+");
        TreeNode root = buildTree(tokens);
        System.out.println(countNodes(root));
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

def get_left_height(node):
    h = 0
    while node:
        h += 1
        node = node.left
    return h

def get_right_height(node):
    h = 0
    while node:
        h += 1
        node = node.right
    return h

def count_nodes(root):
    if not root:
        return 0
    lh = get_left_height(root)
    rh = get_right_height(root)
    if lh == rh:
        return (1 << lh) - 1
    return 1 + count_nodes(root.left) + count_nodes(root.right)

def main():
    tokens = sys.stdin.read().split()
    if not tokens:
        print(0)
        return
    root = build_tree(tokens)
    print(count_nodes(root))

if __name__ == "__main__":
    main()
```
