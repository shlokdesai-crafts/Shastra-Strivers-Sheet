# Editorial: Morris Preorder Traversal

## 1. Approach
Preorder traversal is **Root $\to$ Left $\to$ Right**:
- For current node `curr`:
  - If `curr.left == null`: Print `curr.val`, move `curr = curr.right`.
  - Else find inorder predecessor `prev`:
    - If `prev.right == null`: Print `curr.val` **before moving left** (since root is visited before left subtree in preorder), set thread `prev.right = curr`, and move `curr = curr.left`.
    - If `prev.right == curr`: Cut thread `prev.right = null`, and move `curr = curr.right`.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(1)$
