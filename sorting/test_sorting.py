import random
from unittest import TestCase


class SortTestBase(TestCase):

    def sorting_func(self, items):
        raise RuntimeError("Not supported")

    def test_simple(self):
        self.assertEqual([], self.sorting_func([]))
        self.assertEqual([1], self.sorting_func([1]))
        self.assertEqual([1, 2, 3, 4], self.sorting_func([1, 2, 3, 4]))
        self.assertEqual([1, 2, 3, 4], self.sorting_func([4, 3, 2, 1]))
        self.assertEqual([4, 4, 4, 4], self.sorting_func([4, 4, 4, 4]))

    def test_many(self):
        for i in range(1, 100):
            arr = list(range(1, i + 1))
            random.shuffle(arr)
            self.assertEqual(sorted(arr), self.sorting_func(arr))
