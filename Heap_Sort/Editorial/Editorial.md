# Editorial: Heap Sort

## 1. Overview
Given an array of integers nums, sort it in ascending order using the Heap Sort algorithm in O(N log N) time and O(1) auxiliary space.

## 2. Approach & Complexity
- Standard optimal binary heap operations utilizing array indexing ($2i+1$, $2i+2$, $(i-1)/2$).
- Time Complexity: $\mathcal{O}(\log N)$ for heapify/extract, $\mathcal{O}(N)$ for build heap, $\mathcal{O}(N \log N)$ for heap sort.
