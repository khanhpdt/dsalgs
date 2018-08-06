from src.data_structures.graph import Graph, Vertex


class DepthFirstSearch:

    def __init__(self, graph: Graph, source: Vertex) -> None:
        self.graph = graph
        self.source = source
        self._marked = {}

        self._search(self.source)

    def _search(self, v: Vertex):
        self._marked[v.key] = True

        for adj in v.adjacent:
            if adj.key not in self._marked:
                self._search(adj)

    def marked(self, v: Vertex):
        """
        Check if v is connected with the source.
        :return: True if v is connected. False otherwise.
        """
        return v.key in self._marked

    def count(self):
        """
        :return: the number of vertices connected with the source.
        """
        return len(self._marked)
