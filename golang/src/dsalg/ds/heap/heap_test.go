package heap

import "testing"

func TestMaxHeap(t *testing.T) {
	h := new(MaxHeap)

	h.Insert("c")
	if r, _ := h.Root(); r != "c" {
		t.Errorf(`root = %q, want: "c"`, r)
	}

	h.Insert("a")
	if r, _ := h.Root(); r != "c" {
		t.Errorf(`root = %q, want: "c"`, r)
	}

	h.Insert("d")
	if r, _ := h.Root(); r != "d" {
		t.Errorf(`root = %q, want: "d"`, r)
	}
}

func TestRootEmpty(t *testing.T) {
	var h *MaxHeap = nil
	if _, ok := h.Root(); ok {
		t.Errorf("root of nil return OK OK")
	}

	h = new(MaxHeap)
	if _, ok := h.Root(); ok {
		t.Errorf("root of empty returns OK")
	}
}
