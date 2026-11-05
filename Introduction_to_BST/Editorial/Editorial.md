# Editorial: Introduction to BST

## 1. Property
A fundamental mathematical property of a Binary Search Tree is that its **inorder traversal visits elements in strictly ascending sorted order**.
Hence, an array represents an inorder traversal of a valid BST if and only if:
$$arr[i] > arr[i-1] \quad \forall \; 1 \le i < N$$

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(1)$
