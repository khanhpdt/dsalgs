import random
from unittest import TestCase


def selection_sort(numbers):
    return numbers


class SelectionSortTest(TestCase):

    def test_simple(self):
        self.assertEqual(selection_sort([]), [])
        self.assertEqual(selection_sort([1]), [1])
        self.assertEqual(selection_sort([4, 1, 2, 3]), [1, 2, 3, 4])

    def test_many(self):
        for i in range(100):
            arr = random.shuffle(range(1, i + 1))
            self.assertEqual(selection_sort(arr), sorted(arr))
