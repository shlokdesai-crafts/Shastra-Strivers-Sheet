# Introduction to BST

## Problem Statement
Given an array of integers `arr`, determine whether the array represents a valid **Inorder Traversal** of a Binary Search Tree (BST).

Recall that in a valid BST with distinct elements, the Inorder Traversal must always be **strictly increasing** ($arr[i] < arr[i+1]$ for all valid indices).
Return `true` if it can represent an Inorder Traversal of a BST, otherwise `false`.

---

## Input Format
- A single line containing space-separated integers representing the array `arr`.

## Output Format
- Print `true` if the array is strictly increasing, otherwise `false`.

---

## Constraints
- $1 \le \text{arr.length} \le 10^5$
- $-10^5 \le \text{arr}[i] \le 10^5$

---

## Examples

### Example 1
```text
Input:
2 4 6 8 10

Output:
true

Explanation:
The array is strictly increasing.
```

### Example 2
```text
Input:
1 3 3 5

Output:
false

Explanation:
The array contains duplicate values (3 and 3), so it is not strictly increasing.
```
