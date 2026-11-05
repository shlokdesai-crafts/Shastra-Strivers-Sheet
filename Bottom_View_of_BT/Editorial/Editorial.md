# Editorial: Bottom View of Binary Tree

## 1. Problem Overview
We need to determine the bottom-most visible node for each vertical column (horizontal distance) of a binary tree. If looking at the tree from underneath, shallower nodes are shadowed by deeper nodes in the same vertical column.

## 2. Intuition & Key Observation
- A Breadth-First Search (BFS) / Level-Order Traversal naturally visits nodes from top to bottom, level by level.
- For a given horizontal distance (`hd`), every time we visit a node during BFS, it is at an equal or deeper level than the previous node at that same `hd`.
- Therefore, if we store the nodes in a hash map or ordered map indexed by `hd` and unconditionally **overwrite** the value whenever a node with that `hd` is processed, the map will naturally retain the deepest (bottom-most) node for each vertical line.

## 3. Algorithm Step-by-Step
1. Initialize an empty queue storing pairs of `(node, hd)`. Push `(root, 0)` into the queue.
2. Initialize an ordered map (or hash map) `hdToVal` to store `hd -> node.val`.
3. While the queue is not empty:
   - Dequeue the front element `(curr, hd)`.
   - Update `hdToVal[hd] = curr.val`.
   - If `curr.left` exists, push `(curr.left, hd - 1)` into the queue.
   - If `curr.right` exists, push `(curr.right, hd + 1)` into the queue.
4. Extract all values from `hdToVal` sorted by horizontal distance in ascending order and return them.

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N \log N)$ using an ordered map (`std::map` in C++ / `TreeMap` in Java) or $\mathcal{O}(N)$ using an unordered map while tracking the minimum and maximum horizontal distances.
- **Space Complexity:** $\mathcal{O}(N)$ for storing the queue and the mapping.

## 5. Implementations

### C++
```cpp
#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <queue>
#include <map>

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

vector<int> bottomView(TreeNode* root) {
    vector<int> res;
    if (!root) return res;
    map<int, int> mpp;
    queue<pair<TreeNode*, int>> q;
    q.push({root, 0});
    while (!q.empty()) {
        auto it = q.front();
        q.pop();
        TreeNode* node = it.first;
        int hd = it.second;
        mpp[hd] = node->val;
        if (node->left) q.push({node->left, hd - 1});
        if (node->right) q.push({node->right, hd + 1});
    }
    for (auto& p : mpp) res.push_back(p.second);
    return res;
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
    vector<int> res = bottomView(root);
    for (size_t i = 0; i < res.size(); i++) {
        cout << res[i] << (i + 1 == res.size() ? "" : " ");
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
    static class Pair {
        TreeNode node;
        int hd;
        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        String[] tokens = line.trim().split("\\s+");
        TreeNode root = buildTree(tokens);
        if (root == null) return;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            map.put(p.hd, p.node.val);
            if (p.node.left != null) q.add(new Pair(p.node.left, p.hd - 1));
            if (p.node.right != null) q.add(new Pair(p.node.right, p.hd + 1));
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int val : map.values()) {
            if (count++ > 0) sb.append(" ");
            sb.append(val);
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

def bottom_view(root):
    if not root:
        return []
    hd_map = {}
    q = deque([(root, 0)])
    while q:
        node, hd = q.popleft()
        hd_map[hd] = node.val
        if node.left:
            q.append((node.left, hd - 1))
        if node.right:
            q.append((node.right, hd + 1))
    sorted_hds = sorted(hd_map.keys())
    return [hd_map[hd] for hd in sorted_hds]

def main():
    lines = sys.stdin.read().split()
    if not lines:
        return
    root = build_tree(lines)
    res = bottom_view(root)
    print(*(res))

if __name__ == "__main__":
    main()
```
