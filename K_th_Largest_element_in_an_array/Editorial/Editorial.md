# Editorial: Kth Largest Element in an Array

## 1. Overview
Given an integer array nums and an integer k, return the kth largest element in the array using a Min-Heap.

## 2. Approach & Complexity
- Standard optimal binary heap operations utilizing array indexing ($2i+1$, $2i+2$, $(i-1)/2$).
- Time Complexity: $\mathcal{O}(\log N)$ for heapify/extract, $\mathcal{O}(N)$ for build heap, $\mathcal{O}(N \log N)$ for heap sort.
