from src.common.key_value import Key, KeyValue
from src.common.has_same_items import has_same_items
from src.data_structures.sequential_search_symbol_table import SequentialSearchSymbolTable


class TestSequentialSearchSymbolTable:

    def setup_method(self):
        self.table = SequentialSearchSymbolTable()
        self.items = []

    def test_get_from_empty_table(self):
        assert self.table.get(Key(1)) is None

    def test_get_not_exist(self):
        self.table.put(Key(1), 1)
        assert self.table.get(Key(2)) is None

    def test_put_replace_existing(self):
        self.put_default_items()

        key = self.key(1)
        new_value = 1000000
        assert self.table.get(key) != new_value
        size_before = self.table.size()

        self.table.put(key, new_value)

        assert self.table.get(key) == new_value
        assert self.table.size() == size_before

    def test_put_and_get_sequential(self):
        self.put_default_items()

        for i in range(len(self.items)):
            assert self.table.get(self.key(i)) == self.value(i)

    def put_default_items(self):
        self.items = self.default_items()
        for i in range(len(self.items)):
            self.table.put(self.key(i), self.value(i))

    @staticmethod
    def default_items():
        keys = [Key(1), Key(4), Key(2), Key(8), Key(12)]
        values = [100, 400, 200, 800, 1200]

        items = []
        for i in range(len(keys)):
            items.append(KeyValue(keys[i], values[i]))
        return items

    def key(self, i):
        return self.items[i].key

    def value(self, i):
        return self.items[i].value

    def test_put_get_intermixed(self):
        for i in range(len(self.items)):
            self.table.put(self.key(i), self.value(i))
            assert self.table.get(self.key(i)) == self.value(i)

    def test_put_get_delete_intermixed(self):
        for i in range(len(self.items)):
            self.table.put(self.key(i), self.value(i))
            assert self.table.get(self.key(i)) == self.value(i)

            self.table.delete(self.key(i))
            assert self.table.get(self.key(i)) is None

    def test_contains(self):
        assert not self.table.contains(Key(1))

        self.put_default_items()

        for i in range(len(self.items)):
            assert self.table.contains(self.key(i))

    def test_check_empty(self):
        assert self.table.is_empty()

        self.put_default_items()
        assert not self.table.is_empty()

    def test_get_min_key(self):
        assert self.table.min() is None

        self.put_default_items()
        assert self.table.min().equals(Key(1))

        self.table.put(Key(0), 0)
        assert self.table.min().equals(Key(0))

    def test_get_max_key(self):
        assert self.table.max() is None

        self.put_default_items()
        assert self.table.max().equals(Key(12))

        self.table.put(Key(13), 1300)
        assert self.table.max().equals(Key(13))

    def test_floor(self):
        assert self.table.floor(Key(3)) is None

        self.put_default_items()

        assert self.table.floor(Key(3)).equals(Key(2))
        assert self.table.floor(Key(4)).equals(Key(4))
        assert self.table.floor(Key(0)) is None

    def test_ceiling(self):
        assert self.table.ceiling(Key(3)) is None

        self.put_default_items()

        assert self.table.ceiling(Key(5)).equals(Key(8))
        assert self.table.ceiling(Key(2)).equals(Key(2))
        assert self.table.ceiling(Key(13)) is None

    def test_rank(self):
        assert self.table.rank(Key(2)) == 0

        self.put_default_items()

        assert self.table.rank(Key(1)) == 0
        assert self.table.rank(Key(3)) == 2
        assert self.table.rank(Key(12)) == 4
        assert self.table.rank(Key(14)) == 5

        self.table.put(Key(13), 1600)
        assert self.table.rank(Key(14)) == 6

        self.table.put(Key(9), 2200)
        assert self.table.rank(Key(12)) == 5
        assert self.table.rank(Key(14)) == 7

    def test_select(self):
        assert self.table.select(Key(2)) is None

        self.put_default_items()

        assert self.table.select(0).equals(Key(1))
        assert self.table.select(1).equals(Key(2))
        assert self.table.select(2).equals(Key(4))
        assert self.table.select(3).equals(Key(8))
        assert self.table.select(4).equals(Key(12))
        assert self.table.select(5) is None
        assert self.table.select(6) is None

        self.table.put(Key(13), 1600)
        assert self.table.select(5).equals(Key(13))

        self.table.put(Key(9), 2200)
        assert self.table.select(4).equals(Key(9))
        assert self.table.select(5).equals(Key(12))
        assert self.table.select(6).equals(Key(13))
        assert self.table.select(7) is None

    def test_size_without_bounds(self):
        assert self.table.size() == 0

        self.put_default_items()

        assert self.table.size() == 5

        self.table.put(Key(13), 1300)
        assert self.table.size() == 6

        self.table.delete(Key(12))
        assert self.table.size() == 5

    def test_size_with_bounds(self):
        assert self.table.size(low=Key(0), high=Key(10)) == 0

        self.put_default_items()

        assert self.table.size(low=Key(0), high=Key(10)) == 4
        assert self.table.size(low=Key(1), high=Key(8)) == 4
        assert self.table.size(low=Key(1), high=Key(13)) == 5
        assert self.table.size(low=Key(5), high=Key(13)) == 2

        assert self.table.size(low=Key(7), high=Key(7)) == 0
        assert self.table.size(low=Key(7), high=Key(8)) == 1
        assert self.table.size(low=Key(8), high=Key(7)) == 0

        assert self.table.size(high=Key(12)) == 5
        assert self.table.size(high=Key(1)) == 1
        assert self.table.size(high=Key(8)) == 4

        assert self.table.size(low=Key(12)) == 1
        assert self.table.size(low=Key(2)) == 4
        assert self.table.size(low=Key(1)) == 5

        self.table.put(Key(13), 1300)
        assert self.table.size(low=Key(5), high=Key(13)) == 3

        self.table.delete(Key(12))
        assert self.table.size(low=Key(5), high=Key(13)) == 2

    def test_range_query(self):
        assert self.table.range(low=Key(0), high=Key(10)) == []

        self.put_default_items()

        assert has_same_items(
            self.table.range(low=Key(0), high=Key(10)),
            [Key(1), Key(2), Key(4), Key(8)])
        assert has_same_items(
            self.table.range(low=Key(1), high=Key(8)),
            [Key(1), Key(2), Key(4), Key(8)])
        assert has_same_items(
            self.table.range(low=Key(1), high=Key(13)),
            [Key(1), Key(2), Key(4), Key(8), Key(12)])
        assert has_same_items(
            self.table.range(low=Key(5), high=Key(13)),
            [Key(8), Key(12)])

        assert self.table.range(low=Key(7), high=Key(7)) == []
        assert has_same_items(
            self.table.range(low=Key(7), high=Key(8)),
            [Key(8)])
        assert self.table.range(low=Key(8), high=Key(7)) == []

        assert has_same_items(
            self.table.range(high=Key(12)),
            [Key(1), Key(2), Key(4), Key(8), Key(12)])
        assert has_same_items(
            self.table.range(high=Key(1)),
            [Key(1)])
        assert has_same_items(
            self.table.range(high=Key(8)),
            [Key(1), Key(2), Key(4), Key(8)])

        assert has_same_items(
            self.table.range(low=Key(12)),
            [Key(12)])
        assert has_same_items(
            self.table.range(low=Key(2)),
            [Key(2), Key(4), Key(8), Key(12)])
        assert has_same_items(
            self.table.range(low=Key(1)),
            [Key(1), Key(2), Key(4), Key(8), Key(12)])

        self.table.put(Key(13), 1300)
        assert has_same_items(
            self.table.range(low=Key(5), high=Key(13)),
            [Key(8), Key(12), Key(13)])

        self.table.delete(Key(12))
        assert has_same_items(
            self.table.range(low=Key(5), high=Key(13)),
            [Key(8), Key(13)])
