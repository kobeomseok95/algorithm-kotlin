package leetcode.medium

import java.util.LinkedList

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Constraints:
 *  m == grid.length
 *  n == grid[i].length
 *  1 <= m, n <= 10
 *  grid[i][j] is 0, 1, or 2.
 *
 * 모든 오렌지가 썩는 가장 최소 시간을 리턴
 *
 * 시간 복잡도: O(m * n) -> m * n 크기만큼 순회 및 큐 반복 횟수
 * 공간 복잡도: O(m * n) -> 큐에는 최대 m * n 만큼의 오렌지가 들어갈 수 있다.
 */
class `994` {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        var answer = 0
        val queue = LinkedList<Coordinate>()
        (0 until m).forEach { y ->
            (0 until n).forEach { x ->
                if (grid[y][x] == 2) {
                    queue.add(Coordinate(y, x))
                }
            }
        }
        while (queue.isNotEmpty()) {
            (0 until queue.size).forEach { _ ->
                val coordinate = queue.poll()
                DIRECTIONS.forEach { direction ->
                    val moved = coordinate.move(direction)
                    if (moved.y in (0 until m) && moved.x in (0 until n) && grid[moved.y][moved.x] == 1) {
                        grid[moved.y][moved.x] = 2
                        queue.add(moved)
                    }
                }
            }

            if (queue.isNotEmpty()) {
                answer += 1
            }
        }

        (0 until m).forEach { y ->
            (0 until n).forEach { x ->
                if (grid[y][x] == 1) {
                    return -1
                }
            }
        }
        return answer
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
        private val DIRECTIONS = listOf(
            Coordinate(y = 1, x = 0),
            Coordinate(y = -1, x = 0),
            Coordinate(y = 0, x = 1),
            Coordinate(y = 0, x = -1),
        )
    }
}

fun main() {
    val solution = `994`()
    println( // 4
        solution.orangesRotting(
            grid = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 1, 1),
            ),
        )
    )
    println( // -1
        solution.orangesRotting(
            grid = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(0, 1, 1),
                intArrayOf(1, 0, 1),
            ),
        )
    )
    println( // 0
        solution.orangesRotting(
            grid = arrayOf(
                intArrayOf(0, 2),
            ),
        )
    )
    println( // 0
        solution.orangesRotting(
            grid = arrayOf(
                intArrayOf(0),
            ),
        )
    )
}
