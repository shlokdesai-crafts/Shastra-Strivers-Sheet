# Construct a BST from Preorder Traversal

## Problem Statement
Given an array of integers `preorder`, which represents the **preorder traversal** of a Binary Search Tree (BST), reconstruct the BST and return its level-order traversal.

---

## Input Format
- A single line containing space-separated integers representing the `preorder` traversal of the BST.

## Output Format
- A single line containing the space-separated level-order traversal of the reconstructed BST.

---

## Constraints
- $1 \le \text{preorder.length} \le 10^4$
- $-10^5 \le \text{preorder}[i] \le 10^5$
- All values in `preorder` are **unique**.

---

## Examples

### Example 1
```text
Input:
8 5 1 7 10 12

Output:
8 5 10 1 7 null 12
```
