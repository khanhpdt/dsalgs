# Heap

A heap is a nearly complete tree with a special property named heap property, which is about the relationship between a node and its children.

The heap property depends on the heap type. There are two types of heaps: max-heaps and min-heaps. In max-heaps, the property states that any node in the tree is equal to or larger than its children, and in min-heaps, it states that any node in the tree is equal to or smaller than its children.

## Binary heap

A binary heap is a heap satisfying binary tree properties, i.e., each node has at most two children.

A binary heap is typically implemented by an array. Given the position of a node in the array, its parent and children can be easily located. If the node index is `i`, then the indexes of its parent, left and right child are `floor((i - 1)/2)`, `2*i + 1`, `2*i + 2`, given that the node index starts at 0.

A simple analysis on the algorithm will state that it takes `O(n*logn)` time to build a heap from an array of `n` items. However, the algorithm and its analysis in [1] shows that it is possible for the algorithm to asymptotically take only `O(n)` time to do that.

# References

[1] [Introduction to algorithms, 3rd](http://www.amazon.com/Introduction-Algorithms-3rd-MIT-Press/dp/0262033844/ref=sr_1_1?s=books&ie=UTF8&qid=1461439930&sr=1-1&keywords=introduction+to+algorithms)