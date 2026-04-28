package leetcode.medium

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 *
 * Constraints:
 * - 1 <= m, n <= 100
 *
 * 시간 복잡도: O(M * N)
 * 공간 복잡도: O(N)
 */
class `62_4` {
    fun uniquePaths(m: Int, n: Int): Int {
        val line = IntArray(n) { 1 }
        for (i in 0 until m - 1) {
            for (x in 1 until n) {
                line[x] += line[x - 1]
            }
        }
        return line[n - 1]
    }
}

fun main() {
    val solution = `62_4`()
    println(solution.uniquePaths(3, 7)) // 28
    println(solution.uniquePaths(3, 2)) // 3
}
