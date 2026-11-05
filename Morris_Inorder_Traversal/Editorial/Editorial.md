# Editorial: Morris Inorder Traversal

## 1. Intuition: Threaded Binary Tree
Morris traversal eliminates the need for stack/recursion by creating temporary threads:
- For the current node `curr`, if `curr.left` is null, print `curr.val` and move `curr = curr.right`.
- If `curr.left` exists, find its **inorder predecessor** (rightmost node in left subtree):
  - If `prev.right == null`: Create temporary thread `prev.right = curr`, then move `curr = curr.left`.
  - If `prev.right == curr`: Thread already exists. Cut the thread `prev.right = null`, print `curr.val`, and move `curr = curr.right`.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$ (each edge is traversed at most 3 times).
- **Space Complexity:** $\mathcal{O}(1)$ auxiliary space.
