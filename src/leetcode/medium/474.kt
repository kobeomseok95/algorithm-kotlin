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
 * s: strs.size / l: strs[i].length
 * 시간 복잡도 : O(s * (l + (m * n))) -> 최대 600 * 6,060,000 번 연산
 * 공간 복잡도 : O(m * n) -> 최대 10,000 크기
 */
class `474` {
    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val dp = Array(m + 1) { IntArray(n + 1) }
        strs.forEach { str ->
            val count = str.getStringCount()
            (m downTo count.zero).forEach { zeroIndex ->
                (n downTo count.one).forEach { oneIndex ->
                    dp[zeroIndex][oneIndex] = maxOf(
                        dp[zeroIndex][oneIndex],
                        dp[zeroIndex - count.zero][oneIndex - count.one] + 1,
                    )
                }
            }
        }
        return dp[m][n]
    }

    private fun String.getStringCount(): StringCount {
        var zero = 0
        var one = 0
        this.forEach { c ->
            when (c) {
                '0' -> zero += 1
                '1' -> one += 1
                else -> { /* no-op */ }
            }
        }
        return StringCount(zero, one)
    }

    private data class StringCount(
        val zero: Int,
        val one: Int,
    )
}

fun main() {
    val solution = `474`()
    println( // 4
        solution.findMaxForm(
            strs = arrayOf("10","0001","111001","1","0"),
            m = 5,
            n = 3,
        )
    )
    println( // 2
        solution.findMaxForm(
            strs = arrayOf("10","0","1"),
            m = 1,
            n = 1,
        )
    )
}
