package leetcode.medium

/**
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 * <img src="https://assets.leetcode.com/uploads/2021/07/15/lc-domino.jpg">
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 *
 * Constraints:
 * - 1 <= n <= 1000
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(n)
 */
class `790` {
    fun numTilings(n: Int): Int {
        if (n <= 2) {
            return n
        }
        val dp = LongArray(n + 1).apply {
            this[0] = 1
            this[1] = 1
            this[2] = 2
        }
        (3..n).forEach { i ->
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % MODULO
        }
        return dp[n].toInt()
    }

    companion object {
        private const val MODULO = 1_000_000_000 + 7
    }
}

fun main() {
    val solution = `790`()
    println(solution.numTilings(1)) // 1
    println(solution.numTilings(2)) // 2
    println(solution.numTilings(3)) // 5
    println(solution.numTilings(4)) // 11
    println(solution.numTilings(5)) // 24
    println(solution.numTilings(30)) // 312342182
}
