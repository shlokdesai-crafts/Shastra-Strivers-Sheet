# Two Sum in a Binary Search Tree

## Problem Statement
Given the root of a Binary Search Tree (BST) and an integer `k`, return `true` if there exist two elements in the BST such that their sum is equal to `k`, or `false` otherwise.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** An integer `k`.

## Output Format
- Print `true` if such a pair exists, otherwise `false`.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val}, k \le 10^5$

---

## Examples

### Example 1
```text
Input:
5 3 6 2 4 null 7
9

Output:
true

Explanation:
Nodes 2 and 7 sum to 9 (also 3 and 6, 4 and 5).
```
