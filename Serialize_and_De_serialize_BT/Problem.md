# Serialize and De-serialize Binary Tree

## Problem Statement
Serialization is the process of converting a data structure or tree into a sequence of bits or a string so that it can be stored in a file or memory buffer, or transmitted across a network.
Deserialization is the reverse process: reconstructing the original tree structure from the serialized format.

Design an algorithm to **serialize** and **deserialize** a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

---

## Input Format
- A single line containing the space-separated level-order traversal of the binary tree with `null` representing empty nodes.

## Output Format
- A single line containing the space-separated level-order traversal of the reconstructed tree after serializing and deserializing.

---

## Constraints
- $0 \le \text{Number of nodes} \le 10^5$
- $-10^5 \le \text{Node.val} \le 10^5$

---

## Examples

### Example 1
```text
Input:
1 2 3 null null 4 5

Output:
1 2 3 null null 4 5
```
