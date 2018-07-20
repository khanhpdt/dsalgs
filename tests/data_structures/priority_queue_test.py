import random

from pytest import fail

from src.data_structures.priority_queue import PriorityQueue


class TestPriorityQueue:

    def test_remove_when_empty(self):
        queue = PriorityQueue.max_priority_queue()
        try:
            queue.remove()
            fail()
        except ValueError:
            pass

    def test_remove_few_items_from_max_queue(self):
        queue = PriorityQueue.max_priority_queue()
        queue.insert_multi([3, 1, 5])

        assert queue.remove() == 5
        assert queue.remove() == 3
        assert queue.remove() == 1

    def test_remove_many_items_from_max_queue(self):
        items = list(range(100))
        random.shuffle(items)

        queue = PriorityQueue.max_priority_queue()
        queue.insert_multi(items)

        sorted_items = sorted(items, reverse=True)
        for sorted_item in sorted_items:
            assert queue.remove() == sorted_item

    def test_remove_items_from_min_queue(self):
        queue = PriorityQueue.min_priority_queue()
        queue.insert_multi([3, 1, 5])

        assert queue.remove() == 1
        assert queue.remove() == 3
        assert queue.remove() == 5

    def test_remove_many_items_from_min_queue(self):
        items = list(range(100))
        random.shuffle(items)

        queue = PriorityQueue.min_priority_queue()
        queue.insert_multi(items)

        sorted_items = sorted(items)
        for sorted_item in sorted_items:
            assert queue.remove() == sorted_item

    def test_create_queue_with_invalid_type(self):
        try:
            PriorityQueue("INVALID")
            fail()
        except ValueError:
            pass
