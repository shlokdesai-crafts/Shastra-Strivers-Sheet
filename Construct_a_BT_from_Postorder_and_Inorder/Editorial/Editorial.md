# Editorial: Construct Binary Tree from Postorder and Inorder Traversal

## 1. Approach
- The last element of `postorder` is always the root.
- Find this root in `inorder` array using a Hash Map.
- Elements to the left of root in `inorder` form the left subtree; elements to the right form the right subtree.
- Recursively build subtrees.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(N)$
