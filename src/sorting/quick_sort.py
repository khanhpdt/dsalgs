from src.common.utils import exchange


def quick_sort(arr):
    pass


def partition(items, low, high):
    if low >= high:
        return

    partitioning_item = items[low]

    left = low
    right = high + 1
    while True:
        # scan from the left to find the item not in correct position, i.e., item > partitioning item
        left = left + 1
        while left <= high and items[left] <= partitioning_item:
            left = left + 1

        # scan from the right to find the item not in correct position, i.e., item < partitioning item
        right = right - 1
        while right > low and items[right] >= partitioning_item:
            right = right - 1

        # partitioning done. what's left is to move the partitioning item to the right position.
        if left >= right:
            break

        exchange(items, left, right)

    exchange(items, low, right)


def partition_3way():
    pass
