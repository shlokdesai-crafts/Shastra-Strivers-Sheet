# Editorial: Heapify Algorithm

## 1. Overview
Given an array nums representing a min-heap, set the value at index ind to val and perform heapify to restore the min-heap property.

## 2. Approach & Complexity
- Standard optimal binary heap operations utilizing array indexing ($2i+1$, $2i+2$, $(i-1)/2$).
- Time Complexity: $\mathcal{O}(\log N)$ for heapify/extract, $\mathcal{O}(N)$ for build heap, $\mathcal{O}(N \log N)$ for heap sort.
