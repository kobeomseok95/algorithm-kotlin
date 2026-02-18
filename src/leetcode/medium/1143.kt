package leetcode.medium

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Constraints:
 * - 1 <= text1.length, text2.length <= 1000
 * - text1 and text2 consist of only lowercase English characters.
 *
 * n: text1.length, m: text2.length
 * 시간 복잡도 : O(n * m) -> 최대 1,000,000
 * 공간 복잡도 : O(n * m) -> 최대 1,000,000
 */
class `1143` {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dp = Array(text1.length + 1) { IntArray(text2.length + 1) }
        (1..text1.length).forEach { i ->
            (1..text2.length).forEach { j ->
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[text1.length][text2.length]
    }
}

fun main() {
    val solution = `1143`()
    println(solution.longestCommonSubsequence(text1 = "ab", text2 = "ab")) // 2
    println(solution.longestCommonSubsequence(text1 = "ace", text2 = "abcde")) // 3
    println(solution.longestCommonSubsequence(text1 = "abc", text2 = "abc")) // 3
    println(solution.longestCommonSubsequence(text1 = "abc", text2 = "def")) // 0
    println(solution.longestCommonSubsequence(text1 = "bsbininm", text2 = "jmjkbkjkv")) // 1
    println(solution.longestCommonSubsequence(text1 = "bb", text2 = "bbb")) // 2
    println(solution.longestCommonSubsequence(text1 = "bsb", text2 = "aab")) // 1
}
