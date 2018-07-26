from src.data_structures.binary_search_tree_symbol_table import BinarySearchTreeSymbolTable
from tests.data_structures.symbol_table_test_common import SymbolTableTestCommon


class TestBinarySearchTreeSymbolTable(SymbolTableTestCommon):

    def setup_method(self):
        super().setup_method()
        self.table = BinarySearchTreeSymbolTable()
