# Correct BST with Two Nodes Swapped (Recover BST)

## Problem Statement
You are given the root of a Binary Search Tree (BST), where the values of **exactly two nodes** of the tree were swapped by mistake.
Recover the tree without changing its structure and return its level-order traversal.

---

## Input Format
- A single line containing the space-separated level-order traversal of the distorted BST with `null` representing empty nodes.

## Output Format
- A single line containing the space-separated level-order traversal of the corrected BST.

---

## Constraints
- $2 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 3 null null 2

Output:
3 1 null null 2

Explanation:
3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 restores the BST.
```
