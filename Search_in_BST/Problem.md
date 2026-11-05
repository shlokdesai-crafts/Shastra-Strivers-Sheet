# Search in a Binary Search Tree

## Problem Statement
Given the root of a Binary Search Tree (BST) and an integer `val`.
Find if the node with value equal to `val` exists in the BST. Return `true` if it exists, otherwise return `false`.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** An integer `val` to search for.

## Output Format
- Print `true` if `val` is found in the BST, otherwise `false`.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val}, \text{val} \le 10^5$
- The tree is guaranteed to be a valid BST.

---

## Examples

### Example 1
```text
Input:
4 2 7 1 3
2

Output:
true
```
