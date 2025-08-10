package leetcode.medium

/**
 *  DFS 방식
 *  n = 방 갯수, E = 간선 수
 *  시간 복잡도 : O(n + E)
 *  공간 복잡도 : O(n)
 */
class `841_2` {
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val visited = BooleanArray(rooms.size) { false }
        visited[0] = true
        dfs(rooms, 0, visited)
        return visited.all { it }
    }

    private fun dfs(
        rooms: List<List<Int>>,
        visit: Int,
        visited: BooleanArray,
    ) {
        rooms[visit].forEach { notVisitRoom ->
            if (!visited[notVisitRoom]) {
                visited[notVisitRoom] = true
                dfs(rooms, notVisitRoom, visited)
            }
        }
    }
}
