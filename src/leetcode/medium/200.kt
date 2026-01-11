package leetcode.medium

import java.util.LinkedList

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Constraints:
 *  m == grid.length
 *  n == grid[i].length
 *  1 <= m, n <= 300
 *  grid[i][j] is '0' or '1'.
 *
 * 수직 / 수평으로 인접한 land를 탐색하여 섬의 갯수를 출력하는 문제
 *
 * 시간 복잡도 : O(m * n) -> 큐에 삽입 시 방문처리하기 때문에 같은 노드에 대해 탐색하지 않음. 최대 O(m * n)
 * 공간 복잡도 : O(m * n) -> visited 저장 공간 * 큐 최대 사이즈는 노드 갯수
 */
class `200` {
    fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val visited = Array(m) { BooleanArray(n) { false } }
        var answer = 0
        (0 until m).forEach { y ->
            (0 until n).forEach { x ->
                if (!visited[y][x] && grid[y][x] == LAND) {
                    bfs(y, x, m, n, grid, visited)
                    answer += 1
                }
            }
        }
        return answer
    }

    private fun bfs(
        y: Int,
        x: Int,
        m: Int,
        n: Int,
        grid: Array<CharArray>,
        visited: Array<BooleanArray>,
    ) {
        val queue = LinkedList<Coordinate>().apply { add(Coordinate(y, x)) }
        visited[y][x] = true
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            DIRECTIONS.forEach { direction ->
                val moved = current.move(direction)
                if (
                    moved.y in (0 until m) &&
                    moved.x in (0 until n) &&
                    grid[moved.y][moved.x] == LAND &&
                    !visited[moved.y][moved.x]
                ) {
                    queue.add(moved)
                    visited[moved.y][moved.x] = true
                }
            }
        }
    }

    private data class Coordinate(
        val y: Int,
        val x: Int,
    ) {
        fun move(coordinate: Coordinate): Coordinate {
            return Coordinate(y + coordinate.y, x + coordinate.x)
        }
    }

    companion object {
        private const val LAND = '1'
        private val DIRECTIONS = listOf(
            Coordinate(y = 1, x = 0),
            Coordinate(y = -1, x = 0),
            Coordinate(y = 0, x = 1),
            Coordinate(y = 0, x = -1),
        )
    }
}

fun main() {
    val solution = `200`()
    println(
        solution.numIslands(
            arrayOf(
                charArrayOf('1','1','1','1','0'),
                charArrayOf('1','1','0','1','0'),
                charArrayOf('1','1','0','0','0'),
                charArrayOf('0','0','0','0','0'),
            )
        )
    ) // 1
    println(
        solution.numIslands(
            arrayOf(
                charArrayOf('1','1','0','0','0'),
                charArrayOf('1','1','0','0','0'),
                charArrayOf('0','0','1','0','0'),
                charArrayOf('0','0','0','1','1'),
            )
        )
    ) // 3
    println(
        solution.numIslands(
            arrayOf(
                charArrayOf('1','1'),
                charArrayOf('1','1'),
            )
        )
    ) // 1
}
