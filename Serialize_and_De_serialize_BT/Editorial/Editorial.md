# Editorial: Serialize and De-serialize Binary Tree

## 1. Approach
- **Serialization:** Use standard BFS (Queue) level-order traversal. Store non-null node values and `null` markers separated by spaces.
- **Deserialization:** Tokenize the string. Use a queue to rebuild the tree level-by-level, attaching left and right child pointers to each dequeued node.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(N)$ for both serialization and deserialization.
- **Space Complexity:** $\mathcal{O}(N)$ for the queue.
