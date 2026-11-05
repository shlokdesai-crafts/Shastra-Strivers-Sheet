# Editorial: Floor and Ceil in a BST

## 1. Approach
- **Floor:**
  - If `root.val == key`: floor is `root.val`.
  - If `root.val < key`: `root.val` is a potential candidate for floor! Record it, and move right to find a larger valid candidate.
  - If `root.val > key`: Move left.
- **Ceil:**
  - If `root.val == key`: ceil is `root.val`.
  - If `root.val > key`: `root.val` is a potential candidate for ceil! Record it, and move left to find a smaller valid candidate.
  - If `root.val < key`: Move right.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H)$ for each search.
- **Space Complexity:** $\mathcal{O}(1)$ iterative.
