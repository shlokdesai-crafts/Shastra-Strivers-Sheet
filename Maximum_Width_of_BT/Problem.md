# Maximum Width of Binary Tree

## Problem Statement
Given the root of a binary tree, return the **maximum width** of the given tree.

The **maximum width** of a tree is the maximum width among all levels.
The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will fit in a **64-bit signed integer**.

---

## Input Format
- A single line containing the space-separated level-order traversal of the binary tree with missing nodes represented by `null`.

## Output Format
- A single integer representing the maximum width of the binary tree.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 3 2 5 3 null 9

Output:
4

Explanation:
The maximum width exists in the third level with length 4 (nodes [5, 3, null, 9]).
          1 (idx: 0)
        /   \
       3     2
      / \     \
     5   3     9
Indices at level 3: 5 has index 0, 3 has index 1, 9 has index 3.
Width = 3 - 0 + 1 = 4.
```

### Example 2
```text
Input:
1 3 2 5 null null 9 6 null 7

Output:
7

Explanation:
The maximum width exists in the fourth level with length 7 (nodes [6, null, null, null, null, null, 7]).
```
