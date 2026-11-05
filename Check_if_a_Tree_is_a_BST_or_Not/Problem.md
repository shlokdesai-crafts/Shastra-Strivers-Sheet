# Check if a Tree is a BST or Not

## Problem Statement
Given the root of a binary tree, determine whether it is a valid **Binary Search Tree (BST)**.

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys **strictly less than** the node's key.
- The right subtree of a node contains only nodes with keys **strictly greater than** the node's key.
- Both the left and right subtrees must also be binary search trees.

---

## Input Format
- A single line containing the space-separated level-order traversal of the binary tree with `null` representing empty nodes.

## Output Format
- Print `true` if the tree is a valid BST, otherwise `false`.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
2 1 3

Output:
true
```

### Example 2
```text
Input:
5 1 4 null null 3 6

Output:
false

Explanation:
The root node's value is 5 but its right child's value is 4 (and 4's left child is 3 < 5).
```
