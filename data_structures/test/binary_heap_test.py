import random

from data_structures.binary_heap import BinaryHeap, MinHeapProperty, MaxHeapProperty


class TestBinaryMinHeapProperty:

    heap_property = None

    def setup_class(self):
        self.heap_property = MinHeapProperty()

    def test_check_node(self):
        assert self.heap_property.hold_for_item(2, 1)
        assert not self.heap_property.hold_for_item(1, 2)

    def test_check_heap(self):
        assert self.heap_property.hold_for_array([1, 2, 3])
        assert self.heap_property.hold_for_array([1, 3, 2])
        assert not self.heap_property.hold_for_array([2, 1, 3])
        assert not self.heap_property.hold_for_array([1, 2, 7, 4, 5, 6])
        assert self.heap_property.hold_for_array([1, 2, 7, 4, 5, 8])


class TestBinaryMaxHeapProperty:

    heap_property = None

    def setup_class(self):
        self.heap_property = MaxHeapProperty()

    def test_check_node(self):
        assert self.heap_property.hold_for_item(1, 2)
        assert not self.heap_property.hold_for_item(2, 1)

    def test_check_heap(self):
        assert self.heap_property.hold_for_array([3, 2, 1])
        assert self.heap_property.hold_for_array([3, 1, 2])
        assert not self.heap_property.hold_for_array([2, 1, 3])
        assert not self.heap_property.hold_for_array([7, 6, 4, 3, 2, 5])
        assert self.heap_property.hold_for_array([7, 6, 4, 3, 2, 1])


class BinaryHeapTestCommon:

    heap: BinaryHeap = None

    def test_build_heap_empty(self):
        self.heap.build([])
        assert self.heap.check_heap_property()

    def test_build_heap_one_element(self):
        self.heap.build([1])
        assert self.heap.check_heap_property()

    def test_build_heap_three_elements(self):
        self.heap.build([2, 1, 3])
        assert self.heap.check_heap_property()

    def test_build_heap_many(self):
        for i in range(100):
            arr = list(range(i))
            random.shuffle(arr)
            self.heap.build(arr)
            assert self.heap.check_heap_property()


class TestBinaryMaxHeap(BinaryHeapTestCommon):

    def setup_class(self):
        self.heap = BinaryHeap(MaxHeapProperty())


class TestBinaryMinHeap(BinaryHeapTestCommon):

    def setup_class(self):
        self.heap = BinaryHeap(MinHeapProperty())
