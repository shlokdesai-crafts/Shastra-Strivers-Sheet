# Editorial: Largest BST in Binary Tree

## 1. Approach: Bottom-up Postorder
For each node, we return 4 values from its subtrees:
1. `maxVal` in subtree
2. `minVal` in subtree
3. `maxSize` of valid BST
4. `isBST` boolean flag

At `curr`:
- It is a valid BST if `left.isBST && right.isBST && left.maxVal < curr.val && curr.val < right.minVal`.
- If valid: `size = 1 + left.size + right.size`.
- Else: `size = max(left.size, right.size)`.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(H)$
