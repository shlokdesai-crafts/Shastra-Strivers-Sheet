# Editorial: Implement Min Heap

## 1. Overview
Implement a MinHeap supporting insert, getMin, extractMin, heapSize, and isEmpty operations.

## 2. Approach & Complexity
- Standard optimal binary heap operations utilizing array indexing ($2i+1$, $2i+2$, $(i-1)/2$).
- Time Complexity: $\mathcal{O}(\log N)$ for heapify/extract, $\mathcal{O}(N)$ for build heap, $\mathcal{O}(N \log N)$ for heap sort.
