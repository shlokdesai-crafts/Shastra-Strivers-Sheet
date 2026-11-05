# Editorial: Requirements Needed to Construct a Unique Binary Tree

## 1. Problem Overview
Determine whether two given traversal types can uniquely reconstruct a binary tree.

## 2. Core Concept
To construct a unique binary tree, the **Inorder traversal is strictly required**:
- Inorder traversal provides the relative left/right boundary separating left subtree nodes from right subtree nodes.
- Preorder traversal provides the root first (left-to-right).
- Postorder traversal provides the root last (left-to-right).
- Combinations that yield a unique binary tree:
  - Preorder (1) + Inorder (2) $\to$ **true**
  - Postorder (3) + Inorder (2) $\to$ **true**
- All other combinations (such as Preorder + Postorder, or identical pairs) $\to$ **false**.

## 3. Complexity
- **Time Complexity:** $\mathcal{O}(1)$
- **Space Complexity:** $\mathcal{O}(1)$
