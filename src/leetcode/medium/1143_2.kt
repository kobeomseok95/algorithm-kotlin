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
 * 시간 복잡도 : O(n * m)
 * 공간 복잡도 : O(min(n, m))
 */
class `1143_2` {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val texts = classifyTexts(text1, text2)
        val dp = IntArray(texts.short.length + 1)
        texts.long.forEach { longChar ->
            var prev = 0
            texts.short.forEachIndexed { i, shortChar ->
                val temp = dp[i + 1]
                if (longChar == shortChar) {
                    dp[i + 1] = prev + 1
                } else {
                    dp[i + 1] = maxOf(dp[i], dp[i + 1])
                }
                prev = temp
            }
        }
        return dp[texts.short.length]
    }

    private fun classifyTexts(text1: String, text2: String): Texts {
        return if (text1.length >= text2.length) {
            Texts(
                long = text1,
                short = text2,
            )
        } else {
            Texts(
                long = text2,
                short = text1,
            )
        }
    }

    private data class Texts(
        val long: String,
        val short: String,
    )
}

fun main() {
    val solution = `1143_2`()
    println(solution.longestCommonSubsequence(text1 = "ab", text2 = "ab")) // 2
    println(solution.longestCommonSubsequence(text1 = "ace", text2 = "abcde")) // 3
    println(solution.longestCommonSubsequence(text1 = "abc", text2 = "abc")) // 3
    println(solution.longestCommonSubsequence(text1 = "abc", text2 = "def")) // 0
    println(solution.longestCommonSubsequence(text1 = "bsbininm", text2 = "jmjkbkjkv")) // 1
    println(solution.longestCommonSubsequence(text1 = "bb", text2 = "bbb")) // 2
    println(solution.longestCommonSubsequence(text1 = "bsb", text2 = "aab")) // 1
}
