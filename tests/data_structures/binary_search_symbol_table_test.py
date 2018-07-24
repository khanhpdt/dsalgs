from src.data_structures.binary_search_symbol_table import BinarySearchSymbolTable
from tests.data_structures.symbol_table_test_common import SymbolTableTestCommon


class TestBinarySearchSymbolTable(SymbolTableTestCommon):

    def setup_method(self):
        super().setup_method()
        self.table = BinarySearchSymbolTable()
