import random

from data_structures.binary_heap import BinaryHeap


class TestBinaryHeap:

    heap: BinaryHeap = None

    def setup_class(self):
        self.heap = BinaryHeap()

    def test_build_heap_empty(self):
        self.heap.build([])
        assert self.satisfy_max_heap_property()

    def test_build_heap_one_element(self):
        self.heap.build([1])
        assert self.satisfy_max_heap_property()

    def test_build_heap_three_elements(self):
        self.heap.build([2, 1, 3])
        assert self.satisfy_max_heap_property()

    def test_build_heap_many(self):
        for i in range(100):
            arr = list(range(i))
            random.shuffle(arr)
            self.heap.build(arr)
            assert self.satisfy_max_heap_property()

    def satisfy_max_heap_property(self):
        arr = self.heap.to_array()

        i = 0
        while 2 * i + 1 < len(arr):
            child_1 = 2 * i + 1
            if arr[child_1] > arr[i]:
                print("Child %s > Node %s" % (child_1, i))
                return False

            child_2 = 2 * i + 2
            if child_2 < len(arr) and arr[child_2] > arr[i]:
                print("Child %s > Node %s" % (child_2, i))
                return False

            i = i + 1

        return True
