# Editorial: Minimum and Maximum in BST

## 1. Approach
- In a BST, the smallest value is located at the **leftmost** node: follow `curr = curr.left` until `curr.left == null`.
- The largest value is located at the **rightmost** node: follow `curr = curr.right` until `curr.right == null`.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H)$, $\mathcal{O}(\log N)$ average.
- **Space Complexity:** $\mathcal{O}(1)$.
