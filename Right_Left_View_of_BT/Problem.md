# Right and Left View of Binary Tree

## Problem Statement
Given the root of a binary tree, determine its **Right View** and **Left View**.

- **Right View:** The set of nodes visible when the binary tree is viewed from the right side, ordered from top to bottom (the rightmost node at each level).
- **Left View:** The set of nodes visible when the binary tree is viewed from the left side, ordered from top to bottom (the leftmost node at each level).

---

## Input Format
- A single line containing the space-separated level-order traversal of the binary tree.
- Null nodes are represented by the string `null`.

## Output Format
- Two lines:
  - **Line 1:** Space-separated integers representing the Right View of the binary tree from top to bottom.
  - **Line 2:** Space-separated integers representing the Left View of the binary tree from top to bottom.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 2 3 4 5 null 6

Output:
1 3 6
1 2 4

Explanation:
        1          <-- Level 0: Right: 1, Left: 1
      /   \
     2     3       <-- Level 1: Right: 3, Left: 2
    / \     \
   4   5     6     <-- Level 2: Right: 6, Left: 4

Right View: 1 3 6
Left View: 1 2 4
```

### Example 2
```text
Input:
1 null 3

Output:
1 3
1 3

Explanation:
Tree has root 1 with right child 3. Both right and left views see 1 at depth 0 and 3 at depth 1.
```
