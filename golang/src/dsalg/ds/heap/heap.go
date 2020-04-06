// Package heap provides implementations for max-heap and min heap using arrays.
package heap

import "fmt"

// Node is a node in a heap.
type Node struct {
	key string
}

// MaxHeap represents the max-heap data structure.
type MaxHeap struct {
	nodes []*Node
}

// swim re-heapify the maxheap from i up to the root.
func (h *MaxHeap) swim(i int) {
	if i <= 0 || i >= len(h.nodes) {
		return
	}

	parent := (i - 1) / 2
	left, right := parent*2+1, parent*2+2
	largest := left
	if right < len(h.nodes) && h.nodes[left].key < h.nodes[right].key {
		largest = right
	}

	if h.nodes[parent].key < h.nodes[largest].key {
		h.nodes[parent], h.nodes[largest] = h.nodes[largest], h.nodes[parent]

		// continue up to the root
		h.swim(parent)
	}
}

// Insert inserts a new node with the given key to the heap.
func (h *MaxHeap) Insert(k string) {
	h.nodes = append(h.nodes, &Node{key: k})
	h.swim(len(h.nodes) - 1)
}

// Root returns the root item in the heap. It's the largest item.
func (h *MaxHeap) Root() (string, bool) {
	if h == nil || h.nodes == nil {
		return "", false
	}
	return h.nodes[0].key, true
}

// sink re-heapify the maxheap from i down to bottom.
func (h *MaxHeap) sink(i int) {
	if i <= 0 || i >= h.Len() {
		return
	}

	left, right := i*2+1, i*2+2

	// no child
	if left >= h.Len() {
		return
	}

	largest := left
	if right < h.Len() && h.nodes[left].key < h.nodes[right].key {
		largest = right
	}

	if h.nodes[i].key < h.nodes[largest].key {
		h.nodes[i], h.nodes[largest] = h.nodes[largest], h.nodes[i]

		// continue down to the bottom
		h.sink(largest)
	}
}

// NewMaxHeap constructs a new max heap from the given items.
func NewMaxHeap(items *[]string) *MaxHeap {
	h := new(MaxHeap)

	if len(*items) == 0 {
		return h
	}

	// map items to nodes
	for i := 0; i < len(*items); i++ {
		h.nodes = append(h.nodes, &Node{key: (*items)[i]})
	}

	nonLeaveNodeStart := (h.Len() - 1) / 2

	// though this for loop seems to take O(NlogN), it actually only takes O(N).
	for i := nonLeaveNodeStart; i >= 0; i-- {
		h.sink(i)
	}

	return h
}

// Len returns the number of items in the heap.
func (h *MaxHeap) Len() int {
	return len(h.nodes)
}

// Exchange swaps the ith and jth items in the heap
func (h *MaxHeap) Exchange(i, j int) {
	if i < 0 || i >= h.Len() || j < 0 || j >= h.Len() {
		panic(fmt.Sprintf("Either i or j is in invalid range: i=%d, j=%d.", i, j))
	}
	h.nodes[i], h.nodes[j] = h.nodes[j], h.nodes[i]
}
