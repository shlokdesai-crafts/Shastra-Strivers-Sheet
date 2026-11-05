# Editorial: Inorder Successor and Predecessor in BST

## 1. Approach
- **Successor:**
  - If `root.val > key`: it is a candidate for successor! Record it, and move left to check for a smaller valid successor.
  - If `root.val <= key`: move right.
- **Predecessor:**
  - If `root.val < key`: it is a candidate for predecessor! Record it, and move right to check for a larger valid predecessor.
  - If `root.val >= key`: move left.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H)$
- **Space Complexity:** $\mathcal{O}(1)$
