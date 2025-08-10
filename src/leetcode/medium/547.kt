package leetcode.medium

/**
 *  시간 복잡도 : O(n^2)
 *  공간 복잡도 : O(n)
 */
class `547` {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val visited = BooleanArray(isConnected.size) { false }
        var count = 0
        isConnected.indices.forEach { i ->
            if (!visited[i]) {
                dfs(i, visited, isConnected)
                count += 1
            }
        }
        return count
    }

    private fun dfs(
        i: Int,
        visited: BooleanArray,
        isConnected: Array<IntArray>,
    ) {
        visited[i] = true
        isConnected[i].forEachIndexed { city, connected ->
            if (connected == 1 && !visited[city]) {
                dfs(city, visited, isConnected)
            }
        }
    }
}
