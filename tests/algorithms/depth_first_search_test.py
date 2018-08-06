from src.algorithms.depth_first_search import DepthFirstSearch
from src.data_structures.graph import Graph


class TestDepthFirstSearch:

    def setup_method(self):
        self.graph = Graph()

    def test_search_all_connected_vertices(self):
        self.graph.add_edge(0, 1)
        self.graph.add_edge(0, 2)
        self.graph.add_edge(0, 5)
        self.graph.add_edge(1, 2)
        self.graph.add_edge(2, 3)
        self.graph.add_edge(2, 4)
        self.graph.add_edge(3, 4)
        self.graph.add_edge(3, 5)

        self.graph.add_edge(6, 7)
        self.graph.add_edge(6, 8)
        self.graph.add_edge(7, 8)

        dfs = DepthFirstSearch(self.graph, self.graph.vertex(0))
        self.assert_dfs(dfs, [0, 1, 2, 3, 4, 5])

        dfs = DepthFirstSearch(self.graph, self.graph.vertex(6))
        self.assert_dfs(dfs, [6, 7, 8])

    def assert_dfs(self, dfs, connected_vertex_keys):
        assert dfs.count() == len(connected_vertex_keys)
        for key in connected_vertex_keys:
            assert dfs.marked(self.graph.vertex(key))
