# Editorial: Flatten Binary Tree to Linked List

## 1. Problem Overview
Transform a binary tree into a right-skewed list in-place such that traversing via `right` pointers yields the tree's pre-order traversal sequence, and all `left` pointers become `null`.

## 2. Intuition & Key Observation
Preorder traversal is **Root $\to$ Left $\to$ Right**.
Notice that in the flattened tree:
- `root.left` subtree should be placed directly between `root` and `root.right`.
- Specifically, the rightmost node of `root.left` subtree will connect to `root.right`.
- `root.right` then becomes `root.left`, and `root.left` is set to `null`.

### Approach: Morris-like Traversal (In-Place $\mathcal{O}(1)$ Auxiliary Space)
We iterate through nodes using a pointer `curr`:
1. If `curr.left != null`:
   - Find the rightmost node of `curr.left` (let it be `prev`).
   - Connect `prev.right = curr.right` (attaching original right subtree to the tail of left subtree).
   - Move left subtree to the right: `curr.right = curr.left`.
   - Set `curr.left = null`.
2. Advance `curr = curr.right`.
This transforms the tree in-place using **zero extra memory**!

## 3. Algorithm
```text
function flatten(root):
    curr = root
    while curr is not null:
        if curr.left is not null:
            prev = curr.left
            while prev.right is not null:
                prev = prev.right
            prev.right = curr.right
            curr.right = curr.left
            curr.left = null
        curr = curr.right
```

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N)$ where $N$ is the number of nodes. Each node's rightmost leaf is searched at most once, and every edge is traversed a constant number of times.
- **Space Complexity:** $\mathcal{O}(1)$ auxiliary space as no recursion stack or extra data structures are used.

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

void flatten(TreeNode* root) {
    TreeNode* curr = root;
    while (curr) {
        if (curr->left) {
            TreeNode* prev = curr->left;
            while (prev->right) {
                prev = prev->right;
            }
            prev->right = curr->right;
            curr->right = curr->left;
            curr->left = nullptr;
        }
        curr = curr->right;
    }
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

    flatten(root);

    TreeNode* curr = root;
    bool first = true;
    while (curr) {
        if (!first) cout << " ";
        cout << curr->val;
        first = false;
        curr = curr->right;
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

    public static void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = line.trim().split("\\s+");
        TreeNode root = buildTree(tokens);

        flatten(root);

        TreeNode curr = root;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (curr != null) {
            if (count++ > 0) sb.append(" ");
            sb.append(curr.val);
            curr = curr.right;
        }
        System.out.println(sb.toString());
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

def flatten(root):
    curr = root
    while curr:
        if curr.left:
            prev = curr.left
            while prev.right:
                prev = prev.right
            prev.right = curr.right
            curr.right = curr.left
            curr.left = None
        curr = curr.right

def main():
    tokens = sys.stdin.read().split()
    if not tokens:
        return
    root = build_tree(tokens)
    flatten(root)

    res = []
    curr = root
    while curr:
        res.append(str(curr.val))
        curr = curr.right
    print(" ".join(res))

if __name__ == "__main__":
    main()
```
