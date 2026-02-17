package leetcode.medium

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Constraints:
 * - 1 <= m, n <= 100
 *
 * 시간 복잡도 : O(m * n)
 * 공간 복잡도 : O(m * n)
 */
class `62` {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) }
        (0 until m).forEach { i ->
            dp[i][0] = 1
        }
        (0 until n).forEach { j ->
            dp[0][j] = 1
        }
        (1 until m).forEach { i ->
            (1 until n).forEach { j ->
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        return dp[m - 1][n - 1]
    }
}

fun main() {
    val solution = `62`()
    println(solution.uniquePaths(m = 3, n = 7)) // 28
    println(solution.uniquePaths(m = 3, n = 2)) // 3
}
