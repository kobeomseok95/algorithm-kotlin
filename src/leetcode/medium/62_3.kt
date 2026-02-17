package leetcode.medium

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Constraints:
 * - 1 <= m, n <= 100
 *
 * 시간 복잡도 : O(2 * min(m, n))
 * 공간 복잡도 : O(1)
 */
class `62_3` {
    fun uniquePaths(m: Int, n: Int): Int {
        val rowMoveCount = m - 1
        val columnMoveCount = n - 1
        return combination(rowMoveCount, columnMoveCount)
    }

    private fun combination(
        rowMoveCount: Int,
        columnMoveCount: Int,
    ): Int {
        var combination = 1L
        var numerator = rowMoveCount + columnMoveCount
        val repeatCount = minOf(rowMoveCount, columnMoveCount)
        (1..repeatCount).forEach { denominator ->
            combination *= numerator
            combination /= denominator
            numerator -= 1
        }
        return combination.toInt()
    }
}

fun main() {
    val solution = `62_3`()
    println(solution.uniquePaths(m = 51, n = 9)) // 1916797311
    println(solution.uniquePaths(m = 5, n = 5)) // 70
    println(solution.uniquePaths(m = 4, n = 4)) // 20
    println(solution.uniquePaths(m = 3, n = 7)) // 28
    println(solution.uniquePaths(m = 3, n = 2)) // 3
}
