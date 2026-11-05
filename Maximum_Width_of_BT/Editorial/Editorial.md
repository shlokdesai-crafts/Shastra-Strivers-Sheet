# Editorial: Maximum Width of Binary Tree

## 1. Problem Overview
We need to find the maximum horizontal span (width) across all levels in a binary tree, treating the tree as a complete binary tree where position gaps are counted.

## 2. Intuition & Key Observation
In a 0-indexed complete binary tree:
- If a parent node has index $i$:
  - Its left child has index $2i + 1$
  - Its right child has index $2i + 2$
- The width of any level is simply:
  $$\text{Width} = \text{last\_index} - \text{first\_index} + 1$$
- **Integer Overflow Prevention:** For deep trees, $2i$ can quickly overflow standard 32-bit and even 64-bit integers.
  To prevent this, at each level we normalize the indices by subtracting the first (minimum) index of that level:
  $$\text{curr\_idx} = \text{index} - \text{min\_level\_index}$$
  Because width is relative, this preserves the exact distance without unbounded growth!

## 3. Algorithm (BFS with Index Normalization)
1. If `root == null`, return 0.
2. Initialize queue with pair `(root, 0)`.
3. Set `maxWidth = 0`.
4. While queue is not empty:
   - Let `size = queue.size()`.
   - Let `minIdx = queue.front().index`.
   - Initialize `first = 0`, `last = 0`.
   - Loop $i$ from 0 to `size - 1`:
     - Pop `(node, index)`.
     - Normalized index: `currId = index - minIdx`.
     - If $i == 0$: `first = currId`.
     - If $i == size - 1$: `last = currId`.
     - If `node.left` exists: push `(node.left, 2 * currId + 1)`.
     - If `node.right` exists: push `(node.right, 2 * currId + 2)`.
   - `maxWidth = max(maxWidth, last - first + 1)`.
5. Return `maxWidth`.

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N)$ where $N$ is the number of nodes in the binary tree. Every node is queued and processed once.
- **Space Complexity:** $\mathcal{O}(W)$ where $W$ is the maximum width of the tree, which in the worst case is $\mathcal{O}(N)$.

## 5. Implementations

### C++
```cpp
#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <queue>
#include <algorithm>

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

unsigned long long widthOfBinaryTree(TreeNode* root) {
    if (!root) return 0;
    unsigned long long ans = 0;
    queue<pair<TreeNode*, unsigned long long>> q;
    q.push({root, 0});

    while (!q.empty()) {
        size_t size = q.size();
        unsigned long long minIdx = q.front().second;
        unsigned long long first = 0, last = 0;

        for (size_t i = 0; i < size; i++) {
            unsigned long long curId = q.front().second - minIdx;
            TreeNode* node = q.front().first;
            q.pop();

            if (i == 0) first = curId;
            if (i == size - 1) last = curId;

            if (node->left) q.push({node->left, 2 * curId + 1});
            if (node->right) q.push({node->right, 2 * curId + 2});
        }
        ans = max(ans, last - first + 1);
    }
    return ans;
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
    cout << widthOfBinaryTree(root) << "\n";
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
    static class Pair {
        TreeNode node;
        long idx;
        Pair(TreeNode node, long idx) {
            this.node = node;
            this.idx = idx;
        }
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

    public static long widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        long ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            long minIdx = q.peek().idx;
            long first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                long curId = p.idx - minIdx;
                if (i == 0) first = curId;
                if (i == size - 1) last = curId;

                if (p.node.left != null) q.add(new Pair(p.node.left, 2 * curId + 1));
                if (p.node.right != null) q.add(new Pair(p.node.right, 2 * curId + 2));
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = line.trim().split("\\s+");
        TreeNode root = buildTree(tokens);
        System.out.println(widthOfBinaryTree(root));
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

def width_of_binary_tree(root):
    if not root:
        return 0
    max_width = 0
    q = deque([(root, 0)])

    while q:
        size = len(q)
        min_idx = q[0][1]
        first = 0
        last = 0
        for i in range(size):
            node, idx = q.popleft()
            cur_idx = idx - min_idx
            if i == 0:
                first = cur_idx
            if i == size - 1:
                last = cur_idx
            if node.left:
                q.append((node.left, 2 * cur_idx + 1))
            if node.right:
                q.append((node.right, 2 * cur_idx + 2))
        max_width = max(max_width, last - first + 1)

    return max_width

def main():
    tokens = sys.stdin.read().split()
    if not tokens:
        return
    root = build_tree(tokens)
    print(width_of_binary_tree(root))

if __name__ == "__main__":
    main()
```
