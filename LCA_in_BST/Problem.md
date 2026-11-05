# Lowest Common Ancestor in a Binary Search Tree

## Problem Statement
Given a Binary Search Tree (BST) and two node values `p` and `q` present in the BST, find their Lowest Common Ancestor (LCA).

According to Wikipedia: "The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow a node to be a descendant of itself)."

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** Two space-separated integers `p` and `q`.

## Output Format
- A single integer denoting the value of the LCA.

---

## Constraints
- $2 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$
- All node values are **unique**.
- $p \ne q$, and both exist in the BST.

---

## Examples

### Example 1
```text
Input:
6 2 8 0 4 7 9 null null 3 5
2 8

Output:
6
```
