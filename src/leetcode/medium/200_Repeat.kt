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
class `200_Repeat` {
    fun numIslands(grid: Array<CharArray>): Int {
        var answer = 0
        val visits = Array(grid.size) { BooleanArray(grid[0].size) }
        val queue = ArrayDeque<Point>()
        grid.forEachIndexed { y, _ ->
            grid[y].forEachIndexed { x, _ ->
                if (!visits[y][x] && grid[y][x] == LAND) {
                    bfs(grid, visits, queue, Point.of(y, x))
                    answer++
                }
            }
        }
        return answer
    }

    private fun bfs(
        grid: Array<CharArray>,
        visits: Array<BooleanArray>,
        queue: ArrayDeque<Point>,
        point: Point,
    ) {
        queue.add(point)
        visits[point.y][point.x] = true

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            directions.forEach { direction ->
                val newY = current.y + direction.first
                val newX = current.x + direction.second
                if (
                    0 <= newY && newY < grid.size &&
                    0 <= newX && newX < grid[0].size &&
                    !visits[newY][newX] &&
                    grid[newY][newX] == LAND
                ) {
                    queue.add(Point.of(newY, newX))
                    visits[newY][newX] = true
                }
            }
        }
    }

    private data class Point(
        val y: Int,
        val x: Int,
    ) {
        companion object {
            fun of(y: Int, x: Int): Point {
                return Point(y, x)
            }
        }
    }

    companion object {
        private val directions = setOf(
            1 to 0,
            -1 to 0,
            0 to -1,
            0 to 1,
        )
        private const val LAND = '1'
    }
}

fun main() {
    val solution = `200_Repeat`()
    // 1
    println(solution.numIslands(arrayOf(charArrayOf('1','1','1','1','0'), charArrayOf('1','1','0','1','0'), charArrayOf('1','1','0','0','0'), charArrayOf('0','0','0','0','0'))))
    // 3
    println(solution.numIslands(arrayOf(charArrayOf('1','1','0','0','0'), charArrayOf('1','1','0','0','0'), charArrayOf('0','0','1','0','0'), charArrayOf('0','0','0','1','1'))))
    // 0
    println(solution.numIslands(arrayOf(charArrayOf('0'))))
}
