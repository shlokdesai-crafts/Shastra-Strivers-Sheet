# Binary Search Tree Iterator

## Problem Statement
Implement the `BSTIterator` class that represents an iterator over the **in-order traversal** of a Binary Search Tree (BST):
- `BSTIterator(TreeNode root)` Initializes an object of the `BSTIterator` class.
- `boolean hasNext()` Returns `true` if there exists a number in the traversal to the right of the pointer, otherwise returns `false`.
- `int next()` Moves the pointer to the right, then returns the number at the pointer.

The operations should achieve $\mathcal{O}(1)$ average time complexity and use $\mathcal{O}(H)$ memory.
Simulate the iterator to output the entire traversal sequence.

---

## Input Format
- A single line containing the space-separated level-order traversal of the BST with `null` representing empty nodes.

## Output Format
- A single line containing space-separated integers produced by repeatedly calling `next()` while `hasNext()` is true.

---

## Constraints
- $1 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
7 3 15 null null 9 20

Output:
3 7 9 15 20
```
