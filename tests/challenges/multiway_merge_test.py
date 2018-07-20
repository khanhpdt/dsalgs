from src.challenges.multiway_merge import multiway_merge_simple


class TestMultiwayMerge:

    def test_multiway_merge_simple_small_arrays(self):
        s1 = (i for i in [3, 20, 41])
        s2 = (i for i in [12, 14, 20, 29])
        s3 = (i for i in [21, 29, 51, 89, 199])

        s = multiway_merge_simple([s1, s2, s3])

        assert s == [3, 12, 14, 20, 20, 21, 29, 29, 41, 51, 89, 199]

    def test_multiway_merge_simple_big_arrays(self):
        s1 = (i * 2 for i in range(100))
        s2 = (i * 3 for i in range(100))
        s3 = (i * 4 for i in range(100))

        s = multiway_merge_simple([s1, s2, s3])

        expected = sorted([i * 2 for i in range(100)] + [i * 3 for i in range(100)] + [i * 4 for i in range(100)])
        assert s == expected
