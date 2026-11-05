# Editorial: Insert a Given Node in BST

## 1. Approach
- Traverse down the tree using BST comparisons:
  - If `val < curr.val`: move left. If `curr.left == null`, attach `new TreeNode(val)` and break.
  - If `val > curr.val`: move right. If `curr.right == null`, attach `new TreeNode(val)` and break.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H)$.
- **Space Complexity:** $\mathcal{O}(1)$ iterative.
