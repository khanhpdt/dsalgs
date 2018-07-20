def exchange(items, i1, i2):
    tmp = items[i1]
    items[i1] = items[i2]
    items[i2] = tmp


def list_to_str(items):
    return "[%s]" % ", ".join([str(item) for item in items])


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


def has_same_items(l1, l2):
    """
    This implementation takes O(n*log(n)) time mainly for sorting the two lists before comparing their items.

    Another implementation would be to loop over the first list, build a hash table from that list, and then
    loop over the second list to check if any item not in the hash table. Note that we also need to keep track of
    the item count to handle duplicated items. This implementation takes O(n) time.
    """

    if len(l1) != len(l2):
        return False

    sorted_l1 = sorted(l1)
    sorted_l2 = sorted(l2)

    for i in range(len(sorted_l1)):
        if sorted_l1[i] != sorted_l2[i]:
            return False
    return True


def index_of_min(items):
    start_index = None
    for i in range(len(items)):
        if items[i] is not None:
            start_index = i
            break

    if start_index is None:
        return None

    current_min_index = start_index
    current_min = items[current_min_index]
    for i in range(len(items)):
        if items[i] is not None and items[i] < current_min:
            current_min_index = i
            current_min = items[i]

    return current_min_index
