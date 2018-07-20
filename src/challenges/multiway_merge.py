from src.common.utils import index_of_min


def multiway_merge(ordered_streams):
    """
    Given m sorted streams, merge them to result in a new sorted stream containing
    all items in the given streams.

    Constraints:
        - The total number of items in all streams can be a huge number.
        - The algorithm supports online processing, i.e., not all data is available
        before the processing but they will come in time

    Computational complexity: O(n*log(m)), where n is the total number
    of items in all streams and m is the number of streams.
    """
    pass


def multiway_merge_simple(ordered_streams):
    """
    Simple implementation.

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
