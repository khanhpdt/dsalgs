This note reviews the basic ideas of well-known comparison-sort algorithms, namely the sorting algorithms determining the order between two objects by _comparing_ them. It is known that the complexity of this type of sorting algorithms is lower bounded by `O(n\log n)` [2].

Let assume the sorting order is ascending.

## Selection sort

This algorithm splits the array into two areas: the sorted area __S__ containing elements in sorted order and the unsorted area __U__ containing the remaining unsorted elements. Initially, __S__ is empty and __U__ is the whole array. It is possible to implement this algorithm as an in-place sorting algorithm, and in that case __S__ should be positioned before __U__.

For each iteration, the algorithm _selects_ the minimum element from __U__ and adds it into the tail of __S__. Thus, __U__ is shrinked and __S__ is expanded after each iteration. The algorithm will run until __U__ is empty, which is when all items have already been sorted.

Characteristics:
- Insensitive to whether the input is sorted or not, because the algorithm always looks for the mininum element in __U__.
- Data movement is minimal: `n` exchanges are needed to sort `n` items.
- Use `n^2 / 2` compares and `n` exchanges to sort `n` items

[Computational complexity:](https://en.wikipedia.org/wiki/Selection_sort)

| Best | Average | Worst |
| :----: | :------: | :-----: |
| `O(n^2)` | `O(n^2)` | `O(n^2)` |

## Insertion sort

Similar to selection sort, this algorithm uses two areas __U__ and __S__ to keep track of unsorted and sorted items.
If the sorting is implemented as an in-place algorithm, __S__ should also stand before __U__ in the array.

For each iteration, the algorithm takes an element from __U__ and _inserts_ it into __S__ without breaking the order in __S__. As in selection sort, the algorithm continues until __U__ is empty. The difference between this algorithm and selection sort is that the sorting in this algorithm is done by the insert operation, while that in selection sort the select operation.

Characteristics:
- Sensitive to input data. The more the input array is sorted, the better insertion sort performs. The best case of this algorithm is when the input is already sorted, and the worst case is when the input is sorted in the reverse order. Compared to selection sort, insertion sort performs better on partially sorted arrays.
- Support [online](https://en.wikipedia.org/wiki/Online_algorithm) sort, because it does not need to know all the elements of __U__ in advance.
- Use `n^2 / 4` compares and `n^2 / 4` exchanges to sort a randomly ordered array of length `n` with distinct keys, on the average. The worst case is `n^2 / 2` compares and `n^2 / 2` exchanges and the best case is `n - 1` compares
and 0 exchanges.

[Computational complexity:](https://en.wikipedia.org/wiki/Insertion_sort)

| Best | Average | Worst |
| :----: | :------: | :-----: |
| `O(n)` | `O(n^2)` | `O(n^2)` |

## Bubble sort

Similar to selection sort and insertion sort, this algorithm also produces the two areas during the sorting. However, in constrast to those algorithms, if this algorithm sorts in-place, then __U__ should stand before __S__ in the array.

At each iteration, the largest element in __U__ is "bubbled up" until it reaches __S__. This "bubble up" operation consists of two steps: compare two neighboring elements and swap them if they are out of order. Since a larger element is always swapped with its right neighbor, the largest element will be shifted to the right (aka "bubbled up") and eventually to the end of __U__, and then it is moved to the head of __S__.

If we view the "bubble up" operation as the select operation in selection sort, this algorithm behaves similarly to selection sort, except that the selected element is the largest one, not the smallest one as in selection sort.

Like insertion sort, the best case of this algorithm is when the input is already sorted and the worst case is when the input is sorted in the reverse order.

[Computational complexity:](https://en.wikipedia.org/wiki/Bubble_sort)

| Best | Average | Worst |
| :----: | :------: | :-----: |
| `O(n)` | `O(n^2)` | `O(n^2)` |

## Shellsort

This algorithm is an extension of insertion sort to improve the performance on large arrays. 

In insertion sort, it is only possible to exchange two adjacent items, and if the number of the items is big, a lot of exchanges will need to be performed to bring an item to the right position. E.g., if the smallest item is the last item in the input array, `n` exchanges need to be done to bring it to the first position.

In shellsort, the input array is divided into many subarrays that are interleaved across the input array and contain the elements that are `h`-position apart from each other. Those subarrays are sorted by using insertion sort. After the sorting is done, the gap `h` is reduced, and new subarrays are formed from the new value of `h`. Those new subarrays will then be sorted, and this whole process will continue until `h = 1`. At this point, the whole array is sorted because `h = 1` means that the whole array is the only subarray.

Shellsort offers a better performance than insertion sort on large arrays by making a tradeoff between size and partial order in the subarrays. At the beginning, the gap `h` is big so the number of items in each subarray is small. Because the subarrays are small, sorting them requires a small number of exchanges regardless of the partial order is those subarrays. When `h` becomes small, each subarray becomes more or less large, but the number of exchanges is not necessarily big because the partial order in the subarrays has been established in the previous iterations.

[Computational complexity:](https://en.wikipedia.org/wiki/Shell_sort)

| Best | Average | Worst |
| :----: | :------: | :-----: |
| `O(n \log n)` | Depending on the gap sequence | `O(n (\log n)^2)` |

## Heap sort

This algorithm sorts by using the data structure _heap_ and its special heap property. Let just consider max heaps where the heap property says that each node in the heap is equal to or larger than its children. Because of this max-heap property, the largest element is always the root of the heap.

At each iteration, this algorithm swaps the largest element (which currently is the root of heap) with the last element in the array. After this step, the largest element is considered sorted and thus ignored in later iterations. Since the swap might break the max-heap property, the algorithm must first restructure the heap (but without considering the sorted elements) to ensure the property and then repeats the iteration with the restructured heap.

[Computational complexity:](https://en.wikipedia.org/wiki/Heapsort)

| Best | Average | Worst |
| :----: | :------: | :-----: |
| `O(n*logn)` | `O(n*logn)` | `O(n*logn)` |

## Merge sort

There are two approaches to implement merge sort. One is top-down and the other is bottom-up. 

The top-down implementation uses the divide-and-conquer technique to sort the input array. It first divides the array into two subarrays, then recursively sorts those subarrays, and finally _merges_ those subarrays to produce the sorted version of the original array. On the other hand, the bottom-up implementation starts from the smallest subarrays and _merges_ them until arriving at the whole array.

The crux of this algorithm is the merge operation, which combines two sorted arrays to produce a new sorted array with all the elements from those two arrays.

What this algorithm actually does is to organize a sequence of merges in such a way that the output is a sorted array. Thus, the sorting is actually done by the merge operation, and as a result the performance of this algorithm is determined by that of the merge operation.

Characteristics:
- Guarantee `O(nlogn)` to sort any array
- Use extra `O(n)` space for the auxiliary array used when merging. It is possible to avoid using the extra space, but the implementation would be more complicated [1].

[Computational complexity:](https://en.wikipedia.org/wiki/Merge_sort)

| Best | Average | Worst |
| :----: | :------: | :-----: |
| `O(n*logn)` | `O(n*logn)` | `O(n*logn)` |

## Quick sort

Like merge sort, this algorithm follows the divide-and-conquer technique.

The first step (divide) in this algorithm is to divide the input array into three parts: two subarrays and a pivot element, which must satisfy the following properties:

  - The pivot element stays between the two subarrays, i.e., the structure of the array after the divide step is `[first_subarray, pivot_element, second_subarray]`.
  - All elements in `first_subarray` <= `pivot_element` < all elements in `second_subarray`

The second step (conquer) is to sort each of the two subarrays recursively. As the pivot element is already in its correct position, it will not be considered by the algorithm anymore.

The final step (combine) is not necessary as the two subarrays and the pivot element are already in their correct positions.

The crux of this algorithm is the divide step, which is commonly referred to as the _partition_ step. This step is the one that actually does the sorting. Conceptually, this algorithm can be viewed as a sequence of partitionings.

The performance of this algorithm is mainly influenced by the balance between the partitions created in the divide step. The worst case occurs when one of the partitions is empty, whereas the best case does when the two partitions have approximately the same size. The performances of the algorithm in the worst and best case can be intuitively calculated as follows. The partitioning cost is the same in two cases, i.e., `O(n)`. In the worst case, the algorithm is recursively called `O(n)` times, whereas in the best case only `O(lg(n))`. So, in total, the worst case takes `O(n^2)` time, whereas the best case `O(n*lg(n))` time.

[Complexity:](https://en.wikipedia.org/wiki/Quicksort)

| Best | Average | Worst |
| :----: | :------: | :-----: |
| `O(n*logn)` or `O(n)` | `O(n*logn)` | `O(n^2)` |

## Complexity comparisons

See [Comparison of sorting algorithms](https://en.wikipedia.org/wiki/Sorting_algorithm#Comparison_of_algorithms) for a comprehensive comparison.

## Further reading

[1] [Algorithms, 4th](http://www.amazon.com/Algorithms-4th-Robert-Sedgewick/dp/032157351X/ref=sr_1_2?ie=UTF8&qid=1461440135&sr=8-2&keywords=algorithms)

[2] [Introduction to algorithms, 3rd](http://www.amazon.com/Introduction-Algorithms-3rd-MIT-Press/dp/0262033844/ref=sr_1_1?s=books&ie=UTF8&qid=1461439930&sr=1-1&keywords=introduction+to+algorithms)

[3] [The art of computer programming, Vol. 3](http://www.amazon.com/Art-Computer-Programming-Sorting-Searching/dp/0201896850/ref=sr_1_9?s=books&ie=UTF8&qid=1461485952&sr=1-9&keywords=the+art+of+computer+programming)

[4] [Sorting algorithm](https://en.wikipedia.org/wiki/Sorting_algorithm)

