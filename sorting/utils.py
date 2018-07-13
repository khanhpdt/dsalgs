from unittest import TestCase


def exchange(items, i1, i2):
    tmp = items[i1]
    items[i1] = items[i2]
    items[i2] = tmp


def list_to_str(items):
    return ",".join([str(item) for item in items])


def circular_shift_right(items, start, end):
    if start > end:
        raise ValueError("Expected start <= end")

    items_copy = items.copy()
    end_item = items_copy[end]

    for i in reversed(range(start + 1, end + 1)):
        items_copy[i] = items_copy[i - 1]
    items_copy[start] = end_item

    return items_copy


def circular_shift_left(items, start, end):
    if start > end:
        raise ValueError("Expected start <= end")

    items_copy = items.copy()
    start_item = items_copy[start]

    for i in range(start, end):
        items_copy[i] = items_copy[i + 1]
    items_copy[end] = start_item

    return items_copy


def move(items, from_pos, to_pos):
    move_from_left_to_right = from_pos < to_pos
    if move_from_left_to_right:
        return circular_shift_left(items, from_pos, to_pos)
    return circular_shift_right(items, to_pos, from_pos)


class UtilsTest(TestCase):

    def test_circular_shift_right(self):
        self.assertEqual(circular_shift_right([1, 2, 3, 4, 5], 1, 3), [1, 4, 2, 3, 5])
        self.assertEqual(circular_shift_right([1, 2, 3, 4, 5], 0, 4), [5, 1, 2, 3, 4])

    def test_circular_shift_left(self):
        self.assertEqual(circular_shift_left([1, 2, 3, 4, 5], 1, 3), [1, 3, 4, 2, 5])
        self.assertEqual(circular_shift_left([1, 2, 3, 4, 5], 0, 4), [2, 3, 4, 5, 1])

    def test_move_from_left_to_right(self):
        self.assertEqual(move([1, 2, 3, 4, 5], 0, 4), [2, 3, 4, 5, 1])
        self.assertEqual(move([1, 2, 3, 4, 5], 1, 3), [1, 3, 4, 2, 5])

    def test_move_same(self):
        self.assertEqual(move([1, 2, 3, 4, 5], 3, 3), [1, 2, 3, 4, 5])

    def test_move_from_right_to_left(self):
        self.assertEqual(move([1, 2, 3, 4, 5], 2, 0), [3, 1, 2, 4, 5])
        self.assertEqual(move([1, 2, 3, 4, 5], 3, 1), [1, 4, 2, 3, 5])
