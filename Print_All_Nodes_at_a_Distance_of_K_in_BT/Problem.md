# Print All Nodes at a Distance of K in Binary Tree

## Problem Statement
Given the root of a binary tree, the value of a target node `target`, and an integer `k`, return an array of the values of all nodes that have a distance `k` from the target node.

The distance between two nodes is defined as the number of edges on the shortest path between them.
Output the resulting node values as space-separated integers sorted in ascending order. If no nodes are at distance `k`, output an empty line.

All node values in the tree are guaranteed to be unique.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the binary tree with missing nodes represented by `null`.
- **Line 2:** An integer `target` (the value of the target node).
- **Line 3:** An integer `k` (the distance).

## Output Format
- A single line containing space-separated integers of node values at distance `k`, sorted in ascending order.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$
- All node values are **unique**.
- `target` is guaranteed to exist in the tree.
- $0 \le k \le 10^5$

---

## Examples

### Example 1
```text
Input:
3 5 1 6 2 0 8 null null 7 4
5
2

Output:
1 4 7

Explanation:
Nodes at distance 2 from node 5:
- 5 -> 3 -> 1 (distance 2, node 1)
- 5 -> 2 -> 7 (distance 2, node 7)
- 5 -> 2 -> 4 (distance 2, node 4)
Sorted in ascending order: 1 4 7
```

### Example 2
```text
Input:
1
1
3

Output:
(empty line)

Explanation:
No node exists at distance 3 from root 1.
```
