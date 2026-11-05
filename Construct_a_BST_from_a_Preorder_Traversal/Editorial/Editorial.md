# Editorial: Construct a BST from a Preorder Traversal

## 1. Approach: Upper Bound Recursion
We can construct the BST in $\mathcal{O}(N)$ using an upper bound:
- Maintain an index pointer `i` in `preorder`.
- For the current subtree, pass an `upperBound` (initially $+\infty$).
- If `i == preorder.length` or `preorder[i] > upperBound`, return `null`.
- Create `root = new TreeNode(preorder[i++])`.
- Build left subtree with `upperBound = root.val`.
- Build right subtree with `upperBound` preserved.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(H)$
