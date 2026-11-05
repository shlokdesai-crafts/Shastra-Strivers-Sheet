# Delete a Node in Binary Search Tree

## Problem Statement
Given a root node of a BST and a `key`, delete the node with the given key in the BST. Return the root node of the modified BST in level-order format.

The deletion can be divided into two stages:
1. Search for a node to remove.
2. If the node is found, delete the node.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** An integer `key` to delete.

## Output Format
- A single line containing the space-separated level-order traversal of the modified BST.

---

## Constraints
- $0 \le \text{Number of nodes} \le 10^4$
- $-10^5 \le \text{Node.val}, \text{key} \le 10^5$

---

## Examples

### Example 1
```text
Input:
5 3 6 2 4 null 7
3

Output:
5 4 6 2 null null 7
```
