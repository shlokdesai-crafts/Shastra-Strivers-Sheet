# Floor and Ceil in a Binary Search Tree

## Problem Statement
Given the root of a Binary Search Tree and a key value `key`:
- **Floor:** The greatest node value in the BST that is smaller than or equal to `key`. If no such value exists, return `-1`.
- **Ceil:** The smallest node value in the BST that is greater than or equal to `key`. If no such value exists, return `-1`.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the BST with `null` representing empty nodes.
- **Line 2:** An integer `key`.

## Output Format
- A single line containing two space-separated integers: `floor` and `ceil`.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $1 \le \text{Node.val}, \text{key} \le 10^5$

---

## Examples

### Example 1
```text
Input:
10 5 15 2 8 13 17
14

Output:
13 15

Explanation:
- Floor of 14 is 13 (largest value <= 14).
- Ceil of 14 is 15 (smallest value >= 14).
```
