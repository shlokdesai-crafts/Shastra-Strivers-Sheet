# Editorial: Build Heap from a Given Array

## 1. Overview
Given an arbitrary array of integers nums, convert it in-place into a binary min-heap in O(N) time.

## 2. Approach & Complexity
- Standard optimal binary heap operations utilizing array indexing ($2i+1$, $2i+2$, $(i-1)/2$).
- Time Complexity: $\mathcal{O}(\log N)$ for heapify/extract, $\mathcal{O}(N)$ for build heap, $\mathcal{O}(N \log N)$ for heap sort.
