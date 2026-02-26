package leetcode.medium

import java.util.LinkedList

/**
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
 * You are also given the entrance of the maze, where entrance = [entranceRow, entranceCol] denotes the row and column of the cell you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze.
 * Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze.
 * The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 *
 * Constraints:
 * - maze.length == m
 * - maze[i].length == n
 * - 1 <= m, n <= 100
 * - maze[i][j] is either '.' or '+'.
 * - entrance.length == 2
 * - 0 <= entranceRow < m
 * - 0 <= entranceCol < n
 * - entrance will always be an empty cell.
 *
 * 시간 복잡도: O(m * n)
 * 공간 복잡도: O(m * n)
 */
class `2038` {
    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        val queue = LinkedList<PointCount>()
            .apply { this.add(PointCount(count = 0, point = entrance.toPoint())) }
        maze[entrance[0]][entrance[1]] = VISITED
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (ableToGoOut(maze, current.point, entrance.toPoint())) {
                return current.count
            }
            DIRECTION.forEach { direction ->
                val next = current.getNext(direction)
                if (
                    isInnerMaze(maze, next) &&
                    isEmpty(maze, next)
                ) {
                    maze[next.point.first][next.point.second] = VISITED
                    queue.add(next)
                }
            }
        }

        return -1
    }

    private fun isEmpty(maze: Array<CharArray>, next: PointCount): Boolean {
        return maze[next.point.first][next.point.second] == EMPTY
    }

    private fun isInnerMaze(maze: Array<CharArray>, next: PointCount): Boolean {
        return 0 <= next.point.first &&
                next.point.first < maze.size &&
                0 <= next.point.second &&
                next.point.second < maze[0].size
    }

    private fun IntArray.toPoint(): Pair<Int, Int> {
        return this[0] to this[1]
    }

    private fun ableToGoOut(
        maze: Array<CharArray>,
        point: Pair<Int, Int>,
        entrance: Pair<Int, Int>,
    ): Boolean {
        if (
            point != entrance &&
            isBorder(point, maze)
        ) {
            return true
        }
        return false
    }

    private fun isBorder(point: Pair<Int, Int>, maze: Array<CharArray>): Boolean {
        return point.first == 0 ||
                point.first == maze.lastIndex ||
                point.second == 0 ||
                point.second == maze[0].lastIndex
    }

    private data class PointCount(
        val point: Pair<Int, Int>,
        val count: Int,
    ) {
        fun getNext(direction: Pair<Int, Int>): PointCount {
            return PointCount(
                point = (this.point.first + direction.first) to (this.point.second + direction.second),
                count = this.count + 1,
            )
        }
    }

    companion object {
        private const val EMPTY = '.'
        private const val VISITED = '*'
        private val DIRECTION = setOf(
            1 to 0,
            0 to 1,
            -1 to 0,
            0 to -1,
        )
    }
}

fun main() {
    val solution = `2038`()
    println(solution.nearestExit(arrayOf(charArrayOf('+', '+', '.', '+'), charArrayOf('.', '.', '.', '+'), charArrayOf('+', '+', '+', '.')), intArrayOf(1, 2))) // 1
    println(solution.nearestExit(arrayOf(charArrayOf('+', '+', '+'), charArrayOf('.', '.', '.'), charArrayOf('+', '+', '+')), intArrayOf(1, 0))) // 2
    println(solution.nearestExit(arrayOf(charArrayOf('.', '+')), intArrayOf(0, 0))) // -1
}
