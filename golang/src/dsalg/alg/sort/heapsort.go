package sort

import "dsalg/ds/heap"

// HeapSort sorts the given items in ascending order.
// Complexity: O(Nlog(N)), where N is the number of items to sort.
func HeapSort(items *[]string) *[]string {
	h := heap.NewMaxHeap(items) // O(N)

	n := h.Len()
	for i := h.Len() - 1; i >= 1; i-- {
		h.Exchange(0, i) // move the largest item to last
		n--
		h.Sink(0, n)
	}

	sorted := h.Keys()
	return &sorted
}
