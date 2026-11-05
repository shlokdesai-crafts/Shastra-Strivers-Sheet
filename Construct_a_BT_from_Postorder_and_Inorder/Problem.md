# Construct Binary Tree from Postorder and Inorder Traversal

## Problem Statement
Given two integer arrays `inorder` and `postorder` where `inorder` is the inorder traversal of a binary tree and `postorder` is the postorder traversal of the same tree, construct and return the binary tree in level-order format.

All values in the tree are unique.

---

## Input Format
- **Line 1:** Space-separated integers representing the `postorder` traversal.
- **Line 2:** Space-separated integers representing the `inorder` traversal.

## Output Format
- A single line containing the level-order traversal of the reconstructed binary tree, with missing nodes denoted by `null`.

---

## Constraints
- $1 \le \text{postorder.length} \le 3000$
- $\text{inorder.length} == \text{postorder.length}$
- $-3000 \le \text{postorder}[i], \text{inorder}[i] \le 3000$
- All values in the tree are **unique**.

---

## Examples

### Example 1
```text
Input:
9 15 7 20 3
9 3 15 20 7

Output:
3 9 20 null null 15 7
```
