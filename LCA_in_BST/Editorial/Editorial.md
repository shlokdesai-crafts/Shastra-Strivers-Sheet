# Editorial: LCA in a BST

## 1. Approach
Take advantage of BST ordering:
- Start at the root.
- If both `p` and `q` are smaller than current node, the LCA must be in the left subtree.
- If both `p` and `q` are greater than current node, the LCA must be in the right subtree.
- If one is smaller (or equal) and the other is greater (or equal), the paths diverge, meaning the current node is the LCA!

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H)$
- **Space Complexity:** $\mathcal{O}(1)$ iterative
