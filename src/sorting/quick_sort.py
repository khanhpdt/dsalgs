import random

from src.common.utils import exchange, list_to_str


def quick_sort(arr):
    print("Sorting %s..." % list_to_str(arr))

    items = arr.copy()
    random.shuffle(items)

    _quick_sort_recursive(items, 0, len(items) - 1)

    return items


def _quick_sort_recursive(items, start, end):
    partitioning_item_idx = partition(items, start, end)
    if partitioning_item_idx is not None:
        _quick_sort_recursive(items, start, partitioning_item_idx - 1)
        _quick_sort_recursive(items, partitioning_item_idx + 1, end)


def partition(items, start, end):
    if start >= end:
        return None

    partitioning_item = items[start]

    left = start
    right = end + 1
    while True:
        # scan from the left to find the item not in correct position, i.e., item > partitioning item
        left = left + 1
        while left <= end and items[left] <= partitioning_item:
            left = left + 1

        # scan from the right to find the item not in correct position, i.e., item < partitioning item
        right = right - 1
        while right > start and items[right] >= partitioning_item:
            right = right - 1

        # partitioning done. what's left is to move the partitioning item to the right position.
        if left >= right:
            break

        exchange(items, left, right)

    exchange(items, start, right)

    return right


def partition_3way():
    pass
