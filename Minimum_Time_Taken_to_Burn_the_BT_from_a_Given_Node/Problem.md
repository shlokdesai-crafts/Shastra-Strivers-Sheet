# Minimum Time Taken to Burn the Binary Tree from a Given Node

## Problem Statement
Given a binary tree with unique values and a target node value `start` where a fire originates at time $t = 0$.

In each second, fire spreads from any burning node to all of its adjacent nodes (i.e. its parent, its left child, and its right child).
Find the **minimum time (in seconds)** required to burn the entire binary tree.

All node values in the binary tree are unique. The start node is guaranteed to exist in the tree.

---

## Input Format
- **Line 1:** Space-separated level-order traversal of the binary tree with null nodes represented by `null`.
- **Line 2:** An integer `start` denoting the value of the node from which the fire begins.

## Output Format
- A single integer denoting the minimum time in seconds required to burn the entire binary tree.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$
- All node values are **unique**.
- `start` is guaranteed to exist in the tree.

---

## Examples

### Example 1
```text
Input:
1 2 3 4 null 5 6 null 7 null null null 8
8

Output:
7

Explanation:
At t = 0: Fire starts at node 8.
At t = 1: Fire spreads to node 6.
At t = 2: Fire spreads to node 3.
At t = 3: Fire spreads to node 1 and 5.
At t = 4: Fire spreads to node 2.
At t = 5: Fire spreads to node 4.
At t = 6: Fire spreads to node 7.
Entire tree burned in 7 seconds.
```

### Example 2
```text
Input:
1 2 3
2

Output:
2

Explanation:
At t = 0: Node 2 is on fire.
At t = 1: Fire spreads to parent 1.
At t = 2: Fire spreads from 1 to node 3.
Total time = 2 seconds.
```
