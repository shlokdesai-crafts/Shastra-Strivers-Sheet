# Bottom View of Binary Tree

## Problem Statement
Given the root of a binary tree, return the **bottom view** of the binary tree from left to right.

A node is included in the bottom view if it is the bottom-most node at its horizontal distance from the root. If there are multiple bottom-most nodes at the same horizontal distance and same depth, the node that appears later in the level-order traversal (i.e. rightmost at that level) is considered visible.

The horizontal distance of the root node is considered `0`. For any node:
- Its left child has a horizontal distance of `hd - 1`
- Its right child has a horizontal distance of `hd + 1`

---

## Input Format
- A single line containing the level-order traversal of the binary tree, separated by spaces.
- Missing (null) children are denoted by `null`.

## Output Format
- A single line containing space-separated integers representing the bottom view of the binary tree, ordered from the leftmost horizontal distance to the rightmost horizontal distance.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
20 8 22 5 3 4 25 null null 10 14

Output:
5 10 4 14 25

Explanation:
          20 (hd: 0)
        /    \
      8       22 (hd: 1)
     / \     /  \
    5   3   4    25
       / \
      10 14
Horizontal Distances:
- hd = -2: Node 5
- hd = -1: Node 10
- hd = 0:  Node 4 (overwrites 3 and 20)
- hd = 1:  Node 14 (overwrites 22)
- hd = 2:  Node 25
Result: 5 10 4 14 25
```

### Example 2
```text
Input:
1 2 3 4 5 6 7

Output:
4 2 6 3 7

Explanation:
Nodes at hd:
- hd = -2: 4
- hd = -1: 2
- hd = 0:  6 (since 6 is deeper than 1)
- hd = 1:  3
- hd = 2:  7
Result: 4 2 6 3 7
```
