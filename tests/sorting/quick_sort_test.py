import random

from src.sorting.quick_sort import quick_sort, partition
from tests.sorting.sort_test_common import SortTestCommon


class TestPartition:

    def test_partition(self):
        arr = [3, 9, 2, 12, 5, 6, 4, 20, 28]
        partition(arr, 1, 7)
        assert arr == [3, 6, 2, 4, 5, 9, 12, 20, 28]

    def test_partition_many(self):
        for i in range(100):
            arr = list(range(i + 1))
            random.shuffle(arr)

            low = 0
            high = len(arr) - 1
            partitioning_item = arr[low]

            partition(arr, low, high)

            self.assert_partition(arr, low, high, partitioning_item)

    @staticmethod
    def assert_partition(arr, low, high, partitioning_item):
        mid_index = arr.index(partitioning_item)

        for j in range(low, mid_index):
            assert arr[j] <= partitioning_item

        for j in range(mid_index + 1, high):
            assert arr[j] >= partitioning_item


class TestPartition3Way:
    pass


# class TestQuickSort(SortTestCommon):
#
#     def sort_func(self, items):
#         return quick_sort(items)
