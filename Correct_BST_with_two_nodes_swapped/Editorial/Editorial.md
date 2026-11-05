# Editorial: Recover BST (Two Nodes Swapped)

## 1. Approach: Inorder Violation Detection
In an inorder traversal of a BST, elements must be strictly increasing.
When two nodes are swapped:
- If the swapped nodes are not adjacent, there will be **two violations** where `prev.val > curr.val`:
  - First violation: `first = prev`, `middle = curr`.
  - Second violation: `last = curr`.
- If the swapped nodes are adjacent, there is only **one violation**: swap `first` and `middle`.
- Otherwise swap `first` and `last`.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(H)$
