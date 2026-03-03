package leetcode.medium

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two cells sharing a common edge is 1
 *
 * Constraints:
 * - m == mat.length
 * - n == mat[i].length
 * - 1 <= m, n <= 10^4
 * - 1 <= m * n <= 10^4
 * - mat[i][j] is either 0 or 1.
 * - There is at least one 0 in mat.
 *
 * 시간 복잡도 : O(m * n)
 * 공간 복잡도 : O(m * n)
 */
class `542` {
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val updatedMatrix = Array(mat.size) { IntArray(mat[it].size) { Int.MAX_VALUE } }
        mat.forEachIndexed { y, arr ->
            arr.forEachIndexed { x, value ->
                if (value == 0) {
                    queue.add(y to x)
                    updatedMatrix[y][x] = 0
                }
            }
        }
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            nexts.forEach { next ->
                val nextY = next.first + current.first
                val nextX = next.second + current.second
                if (
                    0 <= nextY && nextY < mat.size &&
                    0 <= nextX && nextX < mat[0].size &&
                    updatedMatrix[nextY][nextX] > updatedMatrix[current.first][current.second] + 1
                ) {
                    updatedMatrix[nextY][nextX] = updatedMatrix[current.first][current.second] + 1
                    queue.add(nextY to nextX)
                }
            }
        }

        return updatedMatrix
    }

    companion object {
        private val nexts = setOf(
            (0 to 1),
            (1 to 0),
            (0 to -1),
            (-1 to 0),
        )
    }
}

fun main() {
    val solution = `542`()
    // [[0,0,0],[0,1,0],[0,0,0]]
    println(solution.updateMatrix(arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(0,0,0))).map { it.toList() })
    // [[0,0,0],[0,1,0],[1,2,1]]
    println(solution.updateMatrix(arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(1,1,1))).map { it.toList() })
}
