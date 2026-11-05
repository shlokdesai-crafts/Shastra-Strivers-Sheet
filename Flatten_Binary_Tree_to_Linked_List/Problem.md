# Flatten Binary Tree to Linked List

## Problem Statement
Given the root of a binary tree, flatten the tree into a "linked list" **in-place**:

- The "linked list" should use the same `TreeNode` class where the `right` child pointer points to the next node in the list and the `left` child pointer is always `null`.
- The "linked list" should be in the same order as a **pre-order traversal** of the binary tree.

---

## Input Format
- A single line containing the space-separated level-order traversal of the binary tree with missing nodes represented by `null`.

## Output Format
- A single line containing space-separated integers representing the flattened linked list (the sequence formed by traversing through the `right` pointers).

---

## Constraints
- $0 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 2 5 3 4 null 6

Output:
1 2 3 4 5 6

Explanation:
Original tree:
        1
       / \
      2   5
     / \   \
    3   4   6

Flattened tree:
1 -> 2 -> 3 -> 4 -> 5 -> 6 (all via right pointers; all left pointers are null).
```

### Example 2
```text
Input:
0

Output:
0

Explanation:
Single node flattens to itself.
```
