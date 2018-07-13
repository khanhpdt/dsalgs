from sorting.selection_sort import selection_sort
from sorting.test.sort_test_common import SortTestCommon


class SelectionSortTest(SortTestCommon):

    def sort_func(self, items):
        return selection_sort(items)
