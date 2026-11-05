# Lowest Common Ancestor in Binary Tree

## Problem Statement
Given the root of a binary tree and two distinct node values `p` and `q` that exist in the tree, find their **Lowest Common Ancestor (LCA)**.

According to the definition of LCA on Wikipedia:
> "The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow a node to be a descendant of itself)."

All node values in the tree are guaranteed to be unique.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the binary tree with null nodes represented by `null`.
- **Line 2:** Two space-separated integers `p` and `q`.

## Output Format
- A single integer denoting the value of the Lowest Common Ancestor.

---

## Constraints
- $2 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$
- All node values are **unique**.
- $p \ne q$
- Both `p` and `q` are guaranteed to exist in the tree.

---

## Examples

### Example 1
```text
Input:
3 5 1 6 2 0 8 null null 7 4
5 1

Output:
3

Explanation:
The LCA of nodes 5 and 1 is 3.
```

### Example 2
```text
Input:
3 5 1 6 2 0 8 null null 7 4
5 4

Output:
5

Explanation:
The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```
