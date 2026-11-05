# Editorial: Print All Nodes at a Distance of K in Binary Tree

## 1. Problem Overview
Given a binary tree, a target node, and distance $K$, find all nodes exactly $K$ edges away from the target in any direction (towards descendants or towards ancestors).

## 2. Intuition & Key Observation
In a standard binary tree, node pointers only move downward (`left` and `right`). To move upward towards ancestors, we must establish **parent pointers**:
1. First pass (BFS or DFS): Traverse the tree to map every node to its parent pointer and locate the target node pointer.
2. Second pass (Radial BFS): Start a BFS from the target node, exploring in all 3 possible directions:
   - Left child
   - Right child
   - Parent node
3. Maintain a `visited` set so nodes are not re-visited.
4. Stop when the BFS queue is at distance $K$. The remaining elements in the queue are all nodes at distance $K$!
5. Sort their values in ascending order and return.

## 3. Algorithm
1. `parentMap = {}`, find `targetNode`.
2. BFS from `root` to populate `parentMap`.
3. `queue = [targetNode]`, `visited = {targetNode}`, `currDist = 0`.
4. While `queue` is not empty:
   - If `currDist == k`, break.
   - For each node currently in queue:
     - Check `node.left`: if exists and not visited, mark visited and enqueue.
     - Check `node.right`: if exists and not visited, mark visited and enqueue.
     - Check `parentMap[node]`: if exists and not visited, mark visited and enqueue.
   - `currDist++`.
5. Extract values from queue, sort in ascending order, and print.

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N + M \log M)$ where $N$ is total nodes in the binary tree (for BFS traversal) and $M$ is the number of nodes at distance $K$ (for sorting, $M \le N$). Total time is $\mathcal{O}(N \log N)$.
- **Space Complexity:** $\mathcal{O}(N)$ to store parent pointers, visited set, and BFS queue.

## 5. Implementations

### C++
```cpp
#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <queue>
#include <unordered_map>
#include <unordered_set>
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

vector<int> distanceK(TreeNode* root, int targetVal, int k) {
    if (!root) return {};
    if (k == 0) return {targetVal};

    unordered_map<TreeNode*, TreeNode*> parentMap;
    TreeNode* targetNode = nullptr;
    queue<TreeNode*> q;
    q.push(root);

    while (!q.empty()) {
        TreeNode* curr = q.front();
        q.pop();
        if (curr->val == targetVal) targetNode = curr;
        if (curr->left) {
            parentMap[curr->left] = curr;
            q.push(curr->left);
        }
        if (curr->right) {
            parentMap[curr->right] = curr;
            q.push(curr->right);
        }
    }

    if (!targetNode) return {};

    unordered_set<TreeNode*> visited;
    queue<TreeNode*> qDist;
    qDist.push(targetNode);
    visited.insert(targetNode);
    int dist = 0;

    while (!qDist.empty()) {
        if (dist == k) break;
        size_t size = qDist.size();
        for (size_t i = 0; i < size; i++) {
            TreeNode* curr = qDist.front();
            qDist.pop();

            if (curr->left && !visited.count(curr->left)) {
                visited.insert(curr->left);
                qDist.push(curr->left);
            }
            if (curr->right && !visited.count(curr->right)) {
                visited.insert(curr->right);
                qDist.push(curr->right);
            }
            if (parentMap.count(curr) && !visited.count(parentMap[curr])) {
                visited.insert(parentMap[curr]);
                qDist.push(parentMap[curr]);
            }
        }
        dist++;
    }

    vector<int> res;
    while (!qDist.empty()) {
        res.push_back(qDist.front()->val);
        qDist.pop();
    }
    sort(res.begin(), res.end());
    return res;
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

    int target, k;
    if (cin >> target >> k) {
        vector<int> res = distanceK(root, target, k);
        for (size_t i = 0; i < res.size(); i++) {
            cout << res[i] << (i + 1 == res.size() ? "" : " ");
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

def distance_k(root, target_val, k):
    if not root:
        return []
    if k == 0:
        return [target_val]

    parent_map = {}
    target_node = None
    q = deque([root])

    while q:
        curr = q.popleft()
        if curr.val == target_val:
            target_node = curr
        if curr.left:
            parent_map[curr.left] = curr
            q.append(curr.left)
        if curr.right:
            parent_map[curr.right] = curr
            q.append(curr.right)

    if not target_node:
        return []

    visited = {target_node}
    q_dist = deque([target_node])
    dist = 0

    while q_dist:
        if dist == k:
            break
        size = len(q_dist)
        for _ in range(size):
            curr = q_dist.popleft()
            if curr.left and curr.left not in visited:
                visited.add(curr.left)
                q_dist.append(curr.left)
            if curr.right and curr.right not in visited:
                visited.add(curr.right)
                q_dist.append(curr.right)
            parent = parent_map.get(curr)
            if parent and parent not in visited:
                visited.add(parent)
                q_dist.append(parent)
        dist += 1

    res = [node.val for node in q_dist]
    res.sort()
    return res

def main():
    lines = sys.stdin.read().strip().splitlines()
    if not lines:
        return
    tokens = lines[0].split()
    root = build_tree(tokens)
    target = int(lines[1].strip())
    k = int(lines[2].strip())
    res = distance_k(root, target, k)
    print(*(res))

if __name__ == "__main__":
    main()
```
