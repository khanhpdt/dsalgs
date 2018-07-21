from src.common.comparable import Comparable
from src.common.utils import compare


class KeyValue(Comparable):

    def __init__(self, key, value=None):
        self.key = key
        self.value = value

    def compare_to(self, other):
        return compare(self.key, other.key)
