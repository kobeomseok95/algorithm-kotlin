package leetcode.medium

/**
 * You are given an array of binary strings strs and two integers m and n.
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Constraints:
 * - 1 <= strs.length <= 600
 * - 1 <= strs[i].length <= 100
 * - strs[i] consists only of digits '0' and '1'.
 * - 1 <= m, n <= 100
 *
 * l: strs.length
 * i: strs[i].length
 * 시간 복잡도 : O(i * (l + m * n))
 * 공간 복잡도 : O(m * n)
 */
class `474_Repeat` {
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val dp = Array(m + 1) { IntArray(n + 1) }
        strs.forEach { str ->
            val (zeros, ones) = str.getCounts()
            (m downTo zeros).forEach { i ->
                (n downTo ones).forEach { j ->
                    dp[i][j] = maxOf(dp[i][j], dp[i - zeros][j - ones] + 1)
                }
            }
        }
        return dp[m][n]
    }

    private fun String.getCounts(): Pair<Int, Int> {
        var m = 0
        var n = 0
        this.forEach { c ->
            if (c == '0') {
                m++
            } else if (c == '1') {
                n++
            }
        }
        return m to n
    }
}

fun main() {
    val solution = `474_Repeat`()
    // 4
    println(
        solution.findMaxForm(
            strs = arrayOf("10","0001","111001","1","0"),
            m = 5,
            n = 3,
        )
    )
    // 2
    println(
        solution.findMaxForm(
            strs = arrayOf("10","0","1"),
            m = 1,
            n = 1,
        )
    )
    // 0
    println(
        solution.findMaxForm(
            strs = arrayOf("111111100011111111111110000001111"),
            m = 1,
            n = 1,
        )
    )
}
