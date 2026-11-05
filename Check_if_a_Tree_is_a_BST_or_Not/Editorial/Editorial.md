# Editorial: Check if a Tree is a BST or Not

## 1. Approach: Valid Range Propagation
For each node, we pass down an allowed range `[minVal, maxVal]`:
- Root must lie in $(-\infty, +\infty)$.
- Left child must lie in $(\text{minVal}, \text{node.val})$.
- Right child must lie in $(\text{node.val}, \text{maxVal})$.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(H)$
