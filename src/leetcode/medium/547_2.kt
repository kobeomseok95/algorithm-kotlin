package leetcode.medium

import java.util.LinkedList

/**
 *  BFS 방식
 *  시간 복잡도 : O(n^2)
 *  공간 복잡도 : O(n)
 */
class `547_2` {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        var count = 0
        val visited = BooleanArray(isConnected.size) { false }
        val queue = LinkedList<Int>()
        isConnected.indices.forEach { i ->
            if (!visited[i]) {
                bfs(isConnected, queue, visited, i)
                count += 1
            }
        }
        return count
    }

    private fun bfs(
        isConnected: Array<IntArray>,
        queue: LinkedList<Int>,
        visited: BooleanArray,
        city: Int,
    ) {
        queue.offer(city)
        visited[city] = true
        while (queue.isNotEmpty()) {
            val city = queue.poll()
            isConnected[city].forEachIndexed { city, connected ->
                if (connected == 1 && !visited[city]) {
                    queue.offer(city)
                    visited[city] = true
                }
            }
        }
    }
}
