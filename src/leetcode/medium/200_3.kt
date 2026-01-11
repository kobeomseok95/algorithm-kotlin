package leetcode.medium

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
 * 시간 복잡도 : O(m * n) -> 모든 노드 방문
 * 공간 복잡도 : O(m * n) -> 최대 콜 스택 깊이 m * n (대각선으로 탐색하지 않음)
 */
class `200_3` {
    fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var answer = 0
        (0 until m).forEach { y ->
            (0 until n).forEach { x ->
                if (grid[y][x] == LAND) {
                    dfs(y, x, m, n, grid)
                    answer += 1
                }
            }
        }
        return answer
    }

    private fun dfs(
        y: Int,
        x: Int,
        m: Int,
        n: Int,
        grid: Array<CharArray>,
    ) {
        grid[y][x] = VISITED
        DIRECTIONS.forEach { direction ->
            val moved = Coordinate(y, x).move(direction)
            if (
                moved.y in (0 until m) &&
                moved.x in (0 until n) &&
                grid[moved.y][moved.x] == LAND
            ) {
                dfs(moved.y, moved.x, m, n, grid)
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
        private const val VISITED = '2'
        private val DIRECTIONS = listOf(
            Coordinate(y = 1, x = 0),
            Coordinate(y = -1, x = 0),
            Coordinate(y = 0, x = 1),
            Coordinate(y = 0, x = -1),
        )
    }
}

fun main() {
    val solution = `200_3`()
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
