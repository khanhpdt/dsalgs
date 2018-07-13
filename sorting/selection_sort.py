from sorting.test_sorting import SortTestBase
from sorting.utils import exchange


def selection_sort(items):
    print("Sorting [%s]..." % ",".join([str(item) for item in items]))
    for i in range(0, len(items) - 1):
        # select the minimum item
        min_index = i
        for j in range(i + 1, len(items)):
            if items[j] < items[min_index]:
                min_index = j
        if i != min_index:
            exchange(items, i, min_index)
    return items


class SelectionSortTest(SortTestBase):

    def sorting_func(self, items):
        return selection_sort(items)

