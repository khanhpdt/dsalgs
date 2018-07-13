from sorting.bubble_sort import bubble_sort
from sorting.test.sort_test_common import SortTestCommon


class TestBubbleSort(SortTestCommon):

    def sort_func(self, items):
        return bubble_sort(items)
