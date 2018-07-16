from src.sorting.heap_sort import heap_sort
from tests.sorting.sort_test_common import SortTestCommon


class TestHeapSort(SortTestCommon):

    def sort_func(self, items):
        return heap_sort(items)
