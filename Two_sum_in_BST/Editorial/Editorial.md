# Editorial: Two Sum in a BST

## 1. Approach: Dual BST Iterators (Two-Pointer on BST)
We can solve this in $\mathcal{O}(N)$ time and $\mathcal{O}(H)$ memory without generating an array:
- Iterator 1: standard next (left $\to$ root $\to$ right, ascending).
- Iterator 2: before / reverse next (right $\to$ root $\to$ left, descending).
- Initialize $i = \text{it1.next()}$ and $j = \text{it2.next()}$.
- While $i < j$:
  - If $i + j == k$: return `true`.
  - If $i + j < k$: advance $i = \text{it1.next()}$.
  - If $i + j > k$: advance $j = \text{it2.next()}$.
- Return `false`.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$
- **Space Complexity:** $\mathcal{O}(H)$
