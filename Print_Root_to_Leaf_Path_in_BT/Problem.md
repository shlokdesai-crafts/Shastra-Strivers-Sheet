# Print Root to Leaf Path in Binary Tree

## Problem Statement
Given the root of a binary tree, find and return **all root-to-leaf paths**.

A leaf node is a node with no children (`left == null` and `right == null`).
Each path should be printed as a sequence of space-separated node values from the root to the leaf node.
The paths must be printed in the order they are traversed in a standard pre-order traversal (from left branch to right branch).

---

## Input Format
- A single line containing the level-order traversal of the binary tree, separated by spaces.
- Missing (null) children are denoted by `null`.

## Output Format
- Each root-to-leaf path on a separate line as space-separated integers.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 2 3 null 5

Output:
1 2 5
1 3

Explanation:
All root-to-leaf paths are:
- Path 1: 1 -> 2 -> 5
- Path 2: 1 -> 3
```

### Example 2
```text
Input:
1

Output:
1

Explanation:
Single node is both root and leaf.
```
