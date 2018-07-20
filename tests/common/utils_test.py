import random

from src.common import utils
from src.common.utils import has_same_items


class TestUtils(object):

    def test_circular_shift_right(self):
        assert utils.circular_shift_right([1, 2, 3, 4, 5], 1, 3) == [1, 4, 2, 3, 5]
        assert utils.circular_shift_right([1, 2, 3, 4, 5], 0, 4) == [5, 1, 2, 3, 4]

    def test_circular_shift_left(self):
        assert utils.circular_shift_left([1, 2, 3, 4, 5], 1, 3) == [1, 3, 4, 2, 5]
        assert utils.circular_shift_left([1, 2, 3, 4, 5], 0, 4) == [2, 3, 4, 5, 1]

    def test_move_from_left_to_right(self):
        assert utils.move([1, 2, 3, 4, 5], 0, 4) == [2, 3, 4, 5, 1]
        assert utils.move([1, 2, 3, 4, 5], 1, 3) == [1, 3, 4, 2, 5]

    def test_move_same(self):
        assert utils.move([1, 2, 3, 4, 5], 3, 3) == [1, 2, 3, 4, 5]

    def test_move_from_right_to_left(self):
        assert utils.move([1, 2, 3, 4, 5], 2, 0) == [3, 1, 2, 4, 5]
        assert utils.move([1, 2, 3, 4, 5], 3, 1) == [1, 4, 2, 3, 5]

    def test_list_to_str(self):
        assert utils.list_to_str([]) == "[]"
        assert utils.list_to_str([1, 2, 3]) == "[1, 2, 3]"

    def test_check_lists_contain_same_items(self):
        assert has_same_items([], [])
        assert has_same_items([1, 2, 3], [3, 1, 2])
        assert not has_same_items([], [1])
        assert not has_same_items([1, 2], [3, 1, 2])

        items = list(range(100))
        list1 = items.copy()
        random.shuffle(list1)
        list2 = items.copy()
        random.shuffle(list2)
        assert has_same_items(list1, list2)
