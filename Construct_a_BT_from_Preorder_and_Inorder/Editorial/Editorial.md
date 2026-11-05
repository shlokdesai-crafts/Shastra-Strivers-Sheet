# Editorial: Construct Binary Tree from Preorder and Inorder Traversal

## 1. Approach
- The first element of `preorder` is always the root.
- Find this root in `inorder` array (using a Hash Map for $\mathcal{O}(1)$ lookup).
- Elements to the left of the root in `inorder` form the left subtree; elements to the right form the right subtree.
- Recursively build left and right subtrees.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$ using a hash map for inorder indices.
- **Space Complexity:** $\mathcal{O}(N)$ for hash map and recursion stack.
