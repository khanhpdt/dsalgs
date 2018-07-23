from abc import ABC, abstractmethod

from src.common.key_value import Key


class SymbolTable(ABC):

    @abstractmethod
    def put(self, key: Key, value):
        raise NotImplementedError("Implement this")

    @abstractmethod
    def get(self, key: Key):
        """
        :return: Get the value associated with the given key. None if no key found.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def delete(self, key: Key):
        """
        Remove from the table the record associated with the given key.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def contains(self, key: Key):
        """
        :return: True if the table contains a record associated with the given key. False otherwise.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def min(self):
        """
        :return: the minimum key in the table.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def max(self):
        """
        :return: the maximum key in the table.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def floor(self, key: Key):
        """
        :return: the largest key in the table that is less than or equal to the given key.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def ceiling(self, key: Key):
        """
        :return: the smallest key in the table that is greater than or equal to the given key.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def rank(self, key: Key):
        """
        :return: the number of keys in the table less than the given key
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def select(self, rank):
        """
        :return: the key of the given rank
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def size(self, low: Key, high: Key):
        """
        :return: the number of keys in range [low, high] inclusive.
        If low or high is not given, the corresponding side is unbounded.
        """
        raise NotImplementedError("Implement this")

    @abstractmethod
    def range(self, low: Key, high: Key):
        """
        :return: the list of keys in range [low, high].
        If low or high is not given, the corresponding side is unbounded.
        """
        raise NotImplementedError("Implement this")
