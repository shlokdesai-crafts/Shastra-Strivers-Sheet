# Insert a Given Node in BST

## Problem Statement
Given the root of a Binary Search Tree (BST) and a value `val` to insert, insert the value into the BST and return the root of the modified BST in level-order traversal format.

It is guaranteed that the new value does not already exist in the original BST.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** An integer `val` to insert.

## Output Format
- A single line containing the space-separated level-order traversal of the modified BST.

---

## Constraints
- $0 \le \text{Number of nodes} \le 10^4$
- $-10^5 \le \text{Node.val}, \text{val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
4 2 7 1 3
5

Output:
4 2 7 1 3 5
```
