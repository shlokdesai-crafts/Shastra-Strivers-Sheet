# Kth Smallest and Largest Element in BST

## Problem Statement
Given the root of a Binary Search Tree (BST) and an integer `k`, find both the **kth smallest** and **kth largest** elements in the BST.

It is guaranteed that $k \le N$ (where $N$ is the number of nodes in the BST).

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** An integer `k`.

## Output Format
- Two space-separated integers: the kth smallest element followed by the kth largest element.

---

## Constraints
- $1 \le k \le N \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
5 3 6 2 4 null null 1
3

Output:
3 4

Explanation:
Inorder: [1, 2, 3, 4, 5, 6]
- 3rd smallest: 3
- 3rd largest: 4 (reverse inorder: 6, 5, 4)
```
