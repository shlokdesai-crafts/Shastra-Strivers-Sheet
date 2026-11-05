# Editorial: Heaps (Theory & Indexing)

## 1. Overview
Given an array representation of a binary tree of size N and an index ind (0-based), determine:
1. Parent index (or -1 if root)
2. Left child index (or -1 if out of bounds)
3. Right child index (or -1 if out of bounds)

## 2. Approach & Complexity
- Standard optimal binary heap operations utilizing array indexing ($2i+1$, $2i+2$, $(i-1)/2$).
- Time Complexity: $\mathcal{O}(\log N)$ for heapify/extract, $\mathcal{O}(N)$ for build heap, $\mathcal{O}(N \log N)$ for heap sort.
