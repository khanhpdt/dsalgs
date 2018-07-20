from src.common.utils import index_of_min

"""
Inputs:
    - m sorted streams
Output:
    - A sorted array containing all the items in the given streams.

Constraints:
    - The number of items in each stream is much larger than the number of streams.
    - The algorithm supports online processing, i.e., not all data is available
    before the processing but they will come in time.
"""


def multiway_merge(ordered_streams):
    """
    This implementation uses a priority queue to find the smallest item
    among m smallest items from m streams.

    Complexity: O(n*log(m)), where n is the total number of items in all streams
    and m is the number of streams.
    """
    pass


def multiway_merge_simple(ordered_streams):
    """
    This implementation keeps m smallest items from m streams in a list
    and loop over that list to find the smallest item in the list.

    Complexity: O(n*m)
    """

    current_min_items = []
    for s in ordered_streams:
        item = next(s, None)
        current_min_items.append(item)

    result = []
    while True:
        # the smallest item among the unmerged items from all the streams
        min_item_idx = index_of_min(current_min_items)
        # no more item
        if min_item_idx is None:
            return result

        result.append(current_min_items[min_item_idx])
        # the min_item_idx is also the index of the stream containing the min item
        current_min_items[min_item_idx] = next(ordered_streams[min_item_idx], None)
