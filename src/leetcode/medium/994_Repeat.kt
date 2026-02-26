package leetcode.medium

import java.util.LinkedList

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * - 0 representing an empty cell,
 * - 1 representing a fresh orange, or
 * - 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 10
 * - grid[i][j] is 0, 1, or 2.
 *
 * 시간 복잡도: O(m * n)
 * 공간 복잡도: O(m * n)
 */
class `994_Repeat` {
    fun orangesRotting(grid: Array<IntArray>): Int {
        var freshCounts = grid.getOrangePointsByStatus(FRESH).count().takeIf { it > 0 } ?: return 0
        val queue = LinkedList<Pair<Int, Int>>()
            .apply { addAll(grid.getOrangePointsByStatus(ROTTEN)) }
        var answer = 0
        while (queue.isNotEmpty() && freshCounts > 0) {
            var changedCounts = 0
            repeat(queue.size) {
                val current = queue.poll()
                DIRECTIONS.forEach { direction ->
                    current.getValidNextByStatusOrNull(direction, grid, FRESH)?.let { next ->
                        grid[next.first][next.second] = ROTTEN
                        queue.add(next)
                        changedCounts += 1
                        freshCounts -= 1
                    }
                }
            }
            if (changedCounts > 0) {
                answer += 1
            }
        }
        return if (freshCounts > 0) {
            -1
        } else {
            answer
        }
    }

    private fun Array<IntArray>.getOrangePointsByStatus(status: Int): Set<Pair<Int, Int>> {
        val points = mutableSetOf<Pair<Int, Int>>()
        this.forEachIndexed { row, columns ->
            columns.forEachIndexed { column, _ ->
                if (this[row][column] == status) {
                    points.add(Pair(row, column))
                }
            }
        }
        return points
    }

    private fun Pair<Int, Int>.getNextPoint(other: Pair<Int, Int>): Pair<Int, Int> {
        return (this.first + other.first) to (this.second + other.second)
    }

    private fun Pair<Int, Int>.getValidNextByStatusOrNull(
        direction: Pair<Int, Int>,
        grid: Array<IntArray>,
        status: Int,
    ): Pair<Int, Int>? {
        return this.getNextPoint(direction).takeIf { next ->
            next.isVisitable(grid) && grid[next.first][next.second] == status
        }
    }

    private fun Pair<Int, Int>.isVisitable(grid: Array<IntArray>): Boolean {
        return 0 <= this.first && this.first < grid.size && 0 <= this.second && this.second < grid[0].size
    }

    companion object {
        private const val FRESH = 1
        private const val ROTTEN = 2
        private val DIRECTIONS = setOf(
            1 to 0,
            0 to 1,
            -1 to 0,
            0 to -1,
        )
    }
}

fun main() {
    val solution = `994_Repeat`()
    println(solution.orangesRotting(arrayOf(intArrayOf(2, 1, 1), intArrayOf(1, 1, 0), intArrayOf(0, 1, 1)))) // 4
    println(solution.orangesRotting(arrayOf(intArrayOf(2, 1, 1), intArrayOf(0, 1, 1), intArrayOf(1, 0, 1)))) // -1
    println(solution.orangesRotting(arrayOf(intArrayOf(0, 2)))) // 0
    println(solution.orangesRotting(arrayOf(intArrayOf(2, 0, 2), intArrayOf(0, 1, 0), intArrayOf(2, 0, 2)))) // -1
    println(solution.orangesRotting(arrayOf(intArrayOf(2), intArrayOf(2), intArrayOf(1), intArrayOf(0), intArrayOf(1), intArrayOf(1)))) // -1
    println(solution.orangesRotting(arrayOf(intArrayOf(0)))) // 0
}
