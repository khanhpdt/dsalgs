from math import floor

from common.utils import exchange


class BinaryHeap:

    def __init__(self) -> None:
        self.items = []

    def build(self, arr):
        self.items = []
        for item in arr:
            self.items.append(item)
            self.ensure_heap_property(len(self.items) - 1)

    def ensure_heap_property(self, item_idx):
        parent_idx = self._get_parent_of(item_idx)
        while parent_idx >= 0 and self.items[item_idx] > self.items[parent_idx]:
            exchange(self.items, item_idx, parent_idx)
            item_idx = parent_idx
            parent_idx = self._get_parent_of(item_idx)

    @staticmethod
    def _get_parent_of(i):
        return int(floor((i - 1) / 2))

    def to_array(self):
        return self.items.copy()
