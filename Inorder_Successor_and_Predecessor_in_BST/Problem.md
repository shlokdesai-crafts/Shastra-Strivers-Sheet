# Inorder Successor and Predecessor in BST

## Problem Statement
Given the root of a Binary Search Tree (BST) and a key value `key`, find the **inorder predecessor** and **inorder successor** of the given key in the BST.

- **Inorder Predecessor:** The node with the largest value smaller than `key`. If none exists, return `-1`.
- **Inorder Successor:** The node with the smallest value greater than `key`. If none exists, return `-1`.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** An integer `key`.

## Output Format
- Two space-separated integers: `predecessor` and `successor`.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $1 \le \text{Node.val}, \text{key} \le 10^5$

---

## Examples

### Example 1
```text
Input:
8 1 9 null 4 null 10 3
8

Output:
4 9
```
