# Editorial: Delete a Node in BST

## 1. Approach
When deleting a node `target`:
- **Case 1: Leaf node (no children):** Simply remove the node.
- **Case 2: One child:** Connect parent directly to the child.
- **Case 3: Two children:** Find the inorder successor (smallest node in right subtree) or inorder predecessor. Replace target's value with successor's value, and delete the successor from the right subtree.

## 2. Complexity
- **Time Complexity:** $\mathcal{O}(H)$.
- **Space Complexity:** $\mathcal{O}(H)$ for recursion stack.
