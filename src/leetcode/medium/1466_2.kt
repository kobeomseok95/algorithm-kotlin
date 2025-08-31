package leetcode.medium


/**
 *  dfs 로 풀어보기
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n - 1)
 */
class `1466_2` {
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        val connectionsMap = createConnectionsMap(connections)
        val visited = BooleanArray(n) { false }
        return dfs(0, visited, connectionsMap)
    }

    private fun createConnectionsMap(connections: Array<IntArray>): Map<Int, Set<Edge>> {
        val map = mutableMapOf<Int, MutableSet<Edge>>()
        connections.forEach { (from, to) ->
            map.getOrPut(from) { mutableSetOf() }.add(Edge(to, false))
            map.getOrPut(to) { mutableSetOf() }.add(Edge(from, true))
        }
        return map
    }

    private fun dfs(
        city: Int,
        visited: BooleanArray,
        connectionsMap: Map<Int, Set<Edge>>,
    ): Int {
        visited[city] = true
        var count = 0
        connectionsMap[city]?.forEach { edge ->
            if (!visited[edge.to]) {
                if (!edge.isReversed) {
                    count += 1
                }
                count += dfs(edge.to, visited, connectionsMap)
            }
        }
        return count
    }

    private data class Edge(
        val to: Int,
        val isReversed: Boolean,
    )
}
