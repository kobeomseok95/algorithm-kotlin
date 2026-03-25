package leetcode.medium

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 300
 * - grid[i][j] is '0' or '1'.
 *
 * 시간 복잡도: O(M * N)
 * 공간 복잡도: O(M * N)
 */
class `200_2_Repeat` {
    fun numIslands(grid: Array<CharArray>): Int {
        val visits = Array(grid.size) { BooleanArray(grid[0].size) }
        var answer = 0
        grid.forEachIndexed { i, _ ->
            grid[i].forEachIndexed { j, c ->
                if (!visits[i][j] && c == LAND) {
                    dfs(Point.of(i, j), visits, grid)
                    answer++
                }
            }
        }
        return answer
    }

    private fun dfs(point: Point, visits: Array<BooleanArray>, grid: Array<CharArray>) {
        visits[point.y][point.x] = true

        directions.forEach { direction ->
            val newPoint = point.move(direction).takeIf {
                isInner(it, grid) && !visits[it.y][it.x] && grid[it.y][it.x] == LAND
            }
            newPoint?.let { dfs(it, visits, grid) }
        }
    }

    private fun isInner(point: Point, grid: Array<CharArray>): Boolean =
        (point.y in (0 until grid.size) && point.x in (0 until grid[0].size))

    private data class Point(
        val y: Int,
        val x: Int,
    ) {
        fun move(direction: Point): Point {
            return Point(y + direction.y, x + direction.x)
        }

        companion object {
            fun of(y: Int, x: Int): Point {
                return Point(y, x)
            }
        }
    }

    companion object {
        private val directions = setOf(
            Point.of(1, 0),
            Point.of(-1, 0),
            Point.of(0, -1),
            Point.of(0, 1),
        )
        private const val LAND = '1'
    }
}

fun main() {
    val solution = `200_2_Repeat`()
//     1
    println(solution.numIslands(arrayOf(charArrayOf('1','1','1','1','0'), charArrayOf('1','1','0','1','0'), charArrayOf('1','1','0','0','0'), charArrayOf('0','0','0','0','0'))))
    // 3
    println(solution.numIslands(arrayOf(charArrayOf('1','1','0','0','0'), charArrayOf('1','1','0','0','0'), charArrayOf('0','0','1','0','0'), charArrayOf('0','0','0','1','1'))))
    // 0
    println(solution.numIslands(arrayOf(charArrayOf('0'))))
}
