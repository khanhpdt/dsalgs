import random
from unittest import TestCase


def exchange(items, i1, i2):
    tmp = items[i1]
    items[i1] = items[i2]
    items[i2] = tmp


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


class SelectionSortTest(TestCase):

    def test_simple(self):
        self.assertEqual(selection_sort([]), [])
        self.assertEqual(selection_sort([1]), [1])
        self.assertEqual(selection_sort([4, 1, 2, 3]), [1, 2, 3, 4])

    def test_many(self):
        for i in range(1, 100):
            arr = list(range(1, i + 1))
            random.shuffle(arr)
            self.assertEqual(selection_sort(arr), sorted(arr))
