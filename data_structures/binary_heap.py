from abc import ABC, abstractmethod
from math import floor

from common.utils import exchange


class BinaryHeapProperty(ABC):

    @abstractmethod
    def hold_for_item(self, item, parent):
        raise NotImplementedError("Not supported")

    def hold_for_array(self, arr):
        i = 0
        while 2 * i + 1 < len(arr):
            child_1 = 2 * i + 1
            if not self.hold_for_item(arr[child_1], arr[i]):
                print("Child %s > Node %s" % (child_1, i))
                return False

            child_2 = 2 * i + 2
            if child_2 < len(arr) and not self.hold_for_item(arr[child_2], arr[i]):
                print("Child %s > Node %s" % (child_2, i))
                return False

            i = i + 1

        return True


class MinHeapProperty(BinaryHeapProperty):

    def hold_for_item(self, item, parent):
        return parent <= item


class MaxHeapProperty(BinaryHeapProperty):

    def hold_for_item(self, item, parent):
        return parent >= item


class BinaryHeap:

    def __init__(self, heap_property: BinaryHeapProperty) -> None:
        self._heap_property = heap_property
        self._items = []

    def build(self, arr):
        self._items = []
        for item in arr:
            self._items.append(item)
            self._ensure_heap_property(len(self._items) - 1)

    def _ensure_heap_property(self, item_idx):
        parent_idx = self._get_parent_of(item_idx)
        while parent_idx >= 0 and not self._heap_property.hold_for_item(self.get(item_idx), self.get(parent_idx)):
            exchange(self._items, item_idx, parent_idx)
            item_idx = parent_idx
            parent_idx = self._get_parent_of(item_idx)

    def get(self, item_idx):
        return self._items[item_idx]

    def size(self):
        return len(self._items)

    @staticmethod
    def _get_parent_of(i):
        return int(floor((i - 1) / 2))

    def _to_array(self):
        return self._items.copy()

    def check_heap_property(self):
        return self._heap_property.hold_for_array(self._to_array())
