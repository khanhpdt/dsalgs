// Package heap provides implementations for max-heap and min heap using arrays.
package heap

type Node struct {
	key string
}

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
