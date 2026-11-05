# Editorial: Kth Smallest and Largest Element in BST

## 1. Approach
- **Inorder Traversal (Left $\to$ Root $\to$ Right):** Produces elements in ascending order. The $k$-th visited element is the **kth smallest**.
- **Reverse Inorder Traversal (Right $\to$ Root $\to$ Left):** Produces elements in descending order. The $k$-th visited element is the **kth largest**.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H + k)$.
- **Space Complexity:** $\mathcal{O}(H)$ for stack space.
