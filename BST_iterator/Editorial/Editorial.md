# Editorial: BST Iterator

## 1. Approach: Controlled Stack
- Use a Stack to store all left ancestors down to the leftmost leaf.
- When `next()` is called:
  - Pop the top node `curr`.
  - Push all left nodes of `curr.right` onto the stack.
  - Return `curr.val`.
- `hasNext()` simply checks if `stack.isEmpty()`.
- Each node is pushed and popped exactly once, guaranteeing $\mathcal{O}(1)$ amortized time.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(1)$ amortized per call.
- **Space Complexity:** $\mathcal{O}(H)$ memory.
