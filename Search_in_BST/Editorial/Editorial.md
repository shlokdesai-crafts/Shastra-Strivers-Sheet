# Editorial: Search in a Binary Search Tree

## 1. Approach
Utilize the BST property:
- If current node value equals `val`, return `true`.
- If `val < curr.val`, search left subtree.
- If `val > curr.val`, search right subtree.
- If node becomes `null`, return `false`.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H)$, where $H$ is the tree height. $\mathcal{O}(\log N)$ on average, $\mathcal{O}(N)$ worst case.
- **Space Complexity:** $\mathcal{O}(1)$ using iterative search.
