# Editorial: Minimum Time Taken to Burn Binary Tree from a Given Node

## 1. Problem Overview
A fire originates at a given target node `start` and burns all connected neighbors (parent, left child, right child) 1 step per second. We need to find how many seconds it takes until all nodes in the tree are burned.

## 2. Intuition & Key Observation
This is equivalent to finding the maximum shortest-path distance from the `start` node to any other node in the undirected graph representation of the binary tree.
We can model this as a multi-step BFS:
1. **Map Parent Pointers:**
   Binary trees only maintain downward edges. Perform a preliminary BFS/DFS traversal to record parent pointers for each node and locate the pointer to the `start` node.
2. **Radial BFS Simulation (Fire Spreading):**
   - Push `startNode` into a queue, and mark it as visited in a hash set.
   - Maintain a variable `time = 0`.
   - In each step of BFS, pop all nodes currently in the queue (the burning frontline).
   - For each node, inspect its 3 neighbors: `left`, `right`, and `parent`.
   - If an unvisited neighbor exists, mark it visited, push to queue, and flag that at least one new node burned during this second.
   - If any new node was burned in this round, increment `time++`.
3. When the queue becomes empty, return `time`.

## 3. Algorithm
```text
function minTimeToBurn(root, startVal):
    parentMap = {}
    startNode = null
    // BFS to find parentMap and startNode
    q = [root]
    while q is not empty:
        curr = q.pop()
        if curr.val == startVal: startNode = curr
        if curr.left:
            parentMap[curr.left] = curr
            q.push(curr.left)
        if curr.right:
            parentMap[curr.right] = curr
            q.push(curr.right)
            
    visited = {startNode}
    fireQueue = [startNode]
    time = 0
    
    while fireQueue is not empty:
        size = fireQueue.size()
        burnedAny = false
        for i in 0..size-1:
            curr = fireQueue.pop()
            for neighbor in [curr.left, curr.right, parentMap[curr]]:
                if neighbor exists and neighbor not in visited:
                    visited.add(neighbor)
                    fireQueue.push(neighbor)
                    burnedAny = true
        if burnedAny:
            time++
            
    return time
```

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(N)$ where $N$ is the number of nodes in the binary tree. Mapping parents takes $\mathcal{O}(N)$, and the BFS traversal visits each node at most once.
- **Space Complexity:** $\mathcal{O}(N)$ to store the parent pointers, visited hash set, and BFS queue.

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

int minTimeToBurn(TreeNode* root, int startVal) {
    if (!root) return 0;
    unordered_map<TreeNode*, TreeNode*> parentMap;
    TreeNode* startNode = nullptr;
    queue<TreeNode*> q;
    q.push(root);

    while (!q.empty()) {
        TreeNode* curr = q.front();
        q.pop();
        if (curr->val == startVal) startNode = curr;
        if (curr->left) {
            parentMap[curr->left] = curr;
            q.push(curr->left);
        }
        if (curr->right) {
            parentMap[curr->right] = curr;
            q.push(curr->right);
        }
    }

    if (!startNode) return 0;

    unordered_set<TreeNode*> visited;
    queue<TreeNode*> fireQueue;
    fireQueue.push(startNode);
    visited.insert(startNode);
    int time = 0;

    while (!fireQueue.empty()) {
        size_t size = fireQueue.size();
        bool burnedAny = false;

        for (size_t i = 0; i < size; i++) {
            TreeNode* curr = fireQueue.front();
            fireQueue.pop();

            if (curr->left && !visited.count(curr->left)) {
                visited.insert(curr->left);
                fireQueue.push(curr->left);
                burnedAny = true;
            }
            if (curr->right && !visited.count(curr->right)) {
                visited.insert(curr->right);
                fireQueue.push(curr->right);
                burnedAny = true;
            }
            if (parentMap.count(curr) && !visited.count(parentMap[curr])) {
                visited.insert(parentMap[curr]);
                fireQueue.push(parentMap[curr]);
                burnedAny = true;
            }
        }
        if (burnedAny) time++;
    }
    return time;
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

    int start;
    if (cin >> start) {
        cout << minTimeToBurn(root, start) << "\n";
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

def min_time_to_burn(root, start_val):
    if not root:
        return 0

    parent_map = {}
    start_node = None
    q = deque([root])

    while q:
        curr = q.popleft()
        if curr.val == start_val:
            start_node = curr
        if curr.left:
            parent_map[curr.left] = curr
            q.append(curr.left)
        if curr.right:
            parent_map[curr.right] = curr
            q.append(curr.right)

    if not start_node:
        return 0

    visited = {start_node}
    fire_queue = deque([start_node])
    time = 0

    while fire_queue:
        size = len(fire_queue)
        burned_any = False
        for _ in range(size):
            curr = fire_queue.popleft()
            if curr.left and curr.left not in visited:
                visited.add(curr.left)
                fire_queue.append(curr.left)
                burned_any = True
            if curr.right and curr.right not in visited:
                visited.add(curr.right)
                fire_queue.append(curr.right)
                burned_any = True
            parent = parent_map.get(curr)
            if parent and parent not in visited:
                visited.add(parent)
                fire_queue.append(parent)
                burned_any = True
        if burned_any:
            time += 1

    return time

def main():
    lines = sys.stdin.read().strip().splitlines()
    if not lines:
        return
    tokens = lines[0].split()
    root = build_tree(tokens)
    start = int(lines[1].strip())
    print(min_time_to_burn(root, start))

if __name__ == "__main__":
    main()
```
