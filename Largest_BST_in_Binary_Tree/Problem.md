# Largest BST in Binary Tree

## Problem Statement
Given the root of a binary tree, find the size (total number of nodes) of the **largest subtree** that is also a valid Binary Search Tree (BST).

---

## Input Format
- A single line containing the space-separated level-order traversal of the binary tree with `null` representing empty nodes.

## Output Format
- A single integer denoting the size of the largest BST subtree.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
10 5 15 1 8 null 7

Output:
3

Explanation:
The largest BST is the subtree rooted at 5 with nodes [5, 1, 8] (size = 3).
```
