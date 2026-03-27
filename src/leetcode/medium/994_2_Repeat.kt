package leetcode.medium

import kotlin.collections.forEachIndexed

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
class `994_2_Repeat` {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val yRange = 0 until grid.size
        val xRange = 0 until grid[0].size
        val queue = ArrayDeque<Point>()
        grid.forEachIndexed { i, arr ->
            arr.forEachIndexed { j, value ->
                if (value == ROTTEN) {
                    queue.add(Point(i, j))
                }
            }
        }
        var minutes = 0
        while (queue.isNotEmpty()) {
            val currentSize = queue.size
            repeat(currentSize) {
                val current = queue.removeFirst()
                directions.forEach { direction ->
                    val next = current.moveOrNull(direction, yRange, xRange)
                    if (next != null && grid[next.y][next.x] == FRESH) {
                        grid[next.y][next.x] = ROTTEN
                        queue.add(next)
                    }
                }
            }
            if (queue.isNotEmpty()) {
                minutes++
            }
        }
        grid.forEachIndexed { _, arr ->
            arr.forEachIndexed { _, value ->
                if (value == FRESH) {
                    return -1
                }
            }
        }
        return minutes
    }

    private data class Point(
        val y: Int,
        val x: Int,
    ) {
        fun moveOrNull(
            point: Point,
            yRange: IntRange,
            xRange: IntRange,
        ): Point? {
            val newY = y + point.y
            val newX = x + point.x
            return Point(newY, newX).takeIf {
                newY in yRange &&
                newX in xRange
            }
        }
    }

    companion object {
        private val directions = setOf(
            Point(-1, 0),
            Point(1, 0),
            Point(0, 1),
            Point(0, -1),
        )

        private const val FRESH = 1
        private const val ROTTEN = 2
    }
}

fun main() {
    val solution = `994_2_Repeat`()
    println(solution.orangesRotting(arrayOf(intArrayOf(2, 1, 1), intArrayOf(1, 1, 0), intArrayOf(0, 1, 1)))) // 4
    println(solution.orangesRotting(arrayOf(intArrayOf(1, 2)))) // 1
    println(solution.orangesRotting(arrayOf(intArrayOf(2, 1, 1), intArrayOf(0, 1, 1), intArrayOf(1, 0, 1)))) // -1
    println(solution.orangesRotting(arrayOf(intArrayOf(0, 2)))) // 0
    println(solution.orangesRotting(arrayOf(intArrayOf(2, 0, 2), intArrayOf(0, 1, 0), intArrayOf(2, 0, 2)))) // -1
    println(solution.orangesRotting(arrayOf(intArrayOf(2), intArrayOf(2), intArrayOf(1), intArrayOf(0), intArrayOf(1), intArrayOf(1)))) // -1
    println(solution.orangesRotting(arrayOf(intArrayOf(0)))) // 0
}
