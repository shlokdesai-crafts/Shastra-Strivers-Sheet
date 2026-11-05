# Morris Preorder Traversal

## Problem Statement
Given the root of a binary tree, return its **Preorder Traversal** using **Morris Traversal** to achieve $\mathcal{O}(1)$ auxiliary space complexity (without using recursion or an explicit stack).

Preorder traversal visits nodes in the order: **Root $\to$ Left $\to$ Right**.

---

## Input Format
- A single line containing the space-separated level-order traversal of the binary tree with `null` representing empty nodes.

## Output Format
- A single line containing space-separated integers representing the Preorder Traversal of the binary tree.

---

## Constraints
- $0 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 null 2 3

Output:
1 2 3
```
