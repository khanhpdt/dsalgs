from src.data_structures.binary_heap import BinaryHeap

MAX_PRIORITY_QUEUE = "MAX_PRIORITY_QUEUE"
MIN_PRIORITY_QUEUE = "MIN_PRIORITY_QUEUE"


class PriorityQueue:

    def __init__(self, type):
        if type == MAX_PRIORITY_QUEUE:
            self.heap = BinaryHeap.max_heap()
        elif type == MIN_PRIORITY_QUEUE:
            self.heap = BinaryHeap.min_heap()
        else:
            raise ValueError(f"Type {type} not supported")

    @staticmethod
    def max_priority_queue():
        return PriorityQueue(MAX_PRIORITY_QUEUE)

    @staticmethod
    def min_priority_queue():
        return PriorityQueue(MIN_PRIORITY_QUEUE)

    def insert_multi(self, items):
        for item in items:
            self.insert(item)

    def insert(self, item):
        self.heap.insert(item)

    def remove(self):
        if self._is_empty():
            raise ValueError("Queue is empty")
        return self.heap.remove()

    def _is_empty(self):
        return self.heap.size() == 0
