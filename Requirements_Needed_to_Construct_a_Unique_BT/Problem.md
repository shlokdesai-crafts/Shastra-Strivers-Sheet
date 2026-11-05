# Requirements Needed to Construct a Unique Binary Tree

## Problem Statement
Given two integers `a` and `b` representing two tree traversal types:
- **1:** Preorder Traversal
- **2:** Inorder Traversal
- **3:** Postorder Traversal

Determine whether a **unique** binary tree can be constructed using the two given traversals.
Return `true` if a unique binary tree can be formed, otherwise return `false`.

---

## Input Format
- A single line containing two space-separated integers `a` and `b`.

## Output Format
- Print `true` if a unique binary tree can be constructed, otherwise `false`.

---

## Constraints
- $1 \le a, b \le 3$

---

## Examples

### Example 1
```text
Input:
1 2

Output:
true

Explanation:
Preorder (1) and Inorder (2) can uniquely determine both the root and distinguish between left and right subtrees.
```

### Example 2
```text
Input:
1 3

Output:
false

Explanation:
Preorder (1) and Postorder (3) without Inorder cannot distinguish whether a child is a left child or right child when a node has only one child.
```
