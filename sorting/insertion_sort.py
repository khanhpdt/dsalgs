from sorting.test_sorting import SortTestBase
from sorting.utils import move, list_to_str


def insertion_sort(items):
    print("Sorting [%s]..." % list_to_str(items))

    items_copy = items.copy()

    for i in range(len(items_copy)):
        # find the position to insert items[i] without breaking the order in the sorted area
        j = i - 1
        while j >= 0 and items_copy[j] > items_copy[i]:
            j = j - 1

        insertion_index = 0 if j < 0 else j + 1

        if insertion_index != i:
            items_copy = move(items_copy, i, insertion_index)

    return items_copy


class InsertionSortTest(SortTestBase):
    def sorting_func(self, items):
        return insertion_sort(items)
