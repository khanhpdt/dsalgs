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
