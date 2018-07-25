from typing import Optional, List

from src.common.comparable import Comparable
from src.common.utils import gt_or_eq, lt_or_eq, compare, eq, lt


class Node(Comparable):

    def __init__(self, key, value) -> None:
        self.key = key
        self.value = value
        # the total number of nodes (including this node) in the tree rooted at this node
        self.node_count = 1
        self.left: Node = None
        self.right: Node = None

    def compare_to(self, other):
        return compare(self.key, other.key)

    def equals(self, other):
        return eq(self.key, other.key)


class BinarySearchTree:

    def __init__(self):
        self._root: Node = None

    def put(self, key, value=None) -> None:
        if self.is_empty():
            self._root = Node(key, value)
            return

        current = self._root
        while current is not None:
            parent = current

            parent.node_count = parent.node_count + 1

            if lt_or_eq(key, current.key):
                current = current.left
                if current is None:
                    parent.left = Node(key, value)
            else:
                current = current.right
                if current is None:
                    parent.right = Node(key, value)

    def size(self):
        return self._root.node_count if self._root is not None else 0

    def is_empty(self):
        return self._root is None

    def get(self, key) -> Optional[Node]:
        if self.is_empty():
            return None

        current = self._root
        while current is not None:
            if eq(current.key, key):
                return current
            current = current.left if lt(key, current.key) else current.right
        return None

    def delete(self, key):
        # no node with the given key exists in the tree
        if self.get(key) is None:
            return

        parent = None
        is_node_left_child = False
        node = self._root
        while node is not None:
            if eq(key, node.key):
                break

            parent = node
            # this is why we want to make sure that the key must exist in the tree.
            parent.node_count = parent.node_count - 1

            if lt(key, node.key):
                node = node.left
                is_node_left_child = True
            else:
                node = node.right
                is_node_left_child = False

        successor, parent_of_successor = self._find_successor_and_parent(node)

        if successor is None:  # the deleted node has no right subtree
            if is_node_left_child:
                parent.left = node.left
            else:
                parent.right = node.left
        else:
            # replace successor by its right child
            # Note: Because the successor is the left-most node, it can either have no child or only one right child,
            # and it must be its parent's left child.
            if parent_of_successor is not None:
                parent_of_successor.left = successor.right

            # replace the deleted node by its successor
            if parent is None:  # the deleted node is the current root
                self._root = successor
            elif is_node_left_child:
                parent.left = successor
            else:
                parent.right = successor

            successor.left = node.left
            # the successor is the node's right child if it is the only node in the node's right subtree,
            # and we want to avoid cycle here.
            if not eq(successor, node.right):
                successor.right = node.right

        if successor is not None:
            successor.node_count = node.node_count - 1
        if parent_of_successor is not None:
            parent_of_successor.node_count = parent_of_successor.node_count - 1

    @staticmethod
    def _find_successor_and_parent(node):
        if node.right is None:
            return None, None

        parent = None
        current = node.right
        while current is not None:
            if current.left is None:
                return current, parent
            parent = current
            current = current.left

    def in_order_traversal(self) -> List[Node]:
        return self._in_order_traversal(self._root)

    def _in_order_traversal(self, node: Node) -> List[Node]:
        if node is None:
            return []
        return self._in_order_traversal(node.left) + [node] + self._in_order_traversal(node.right)

    def pre_order_traversal(self):
        return self._pre_order_traversal(self._root)

    def _pre_order_traversal(self, node: Node) -> List[Node]:
        if node is None:
            return []
        return [node] + self._pre_order_traversal(node.left) + self._pre_order_traversal(node.right)

    def post_order_traversal(self):
        return self._post_order_traversal(self._root)

    def _post_order_traversal(self, node: Node) -> List[Node]:
        if node is None:
            return []
        return self._post_order_traversal(node.left) + self._post_order_traversal(node.right) + [node]

    def satisfy_binary_search_tree_property(self):
        return self._satisfy_binary_search_tree_property(self._root)

    def _satisfy_binary_search_tree_property(self, node: Node):
        if node is None:
            return True

        node_satisfy = (node.left is None or gt_or_eq(node, node.left)) \
            and (node.right is None or lt_or_eq(node, node.right))

        return node_satisfy \
            and self._satisfy_binary_search_tree_property(node.left) \
            and self._satisfy_binary_search_tree_property(node.right)
