# Count Total Nodes in a Complete Binary Tree

## Problem Statement
Given the root of a **complete binary tree**, return the total number of nodes in the tree.

According to Wikipedia, every level in a complete binary tree, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between $1$ and $2^h$ nodes inclusive at the last level $h$.

Design an algorithm that runs in strictly less than $\mathcal{O}(N)$ time complexity, specifically $\mathcal{O}(\log^2 N)$ time.

---

## Input Format
- A single line containing the space-separated level-order traversal of the complete binary tree with null nodes represented by `null`.

## Output Format
- A single integer denoting the total number of nodes in the complete binary tree.

---

## Constraints
- The tree is guaranteed to be a **complete binary tree**.
- $0 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 2 3 4 5 6

Output:
6

Explanation:
        1
      /   \
     2     3
    / \   /
   4   5 6
Total nodes = 6.
```

### Example 2
```text
Input:
1

Output:
1

Explanation:
Single node has 1 node.
```
