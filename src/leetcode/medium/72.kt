package leetcode.medium

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * You have the following three operations permitted on a word:
 * - Insert a character
 * - Delete a character
 * - Replace a character
 *
 * Constraints:
 * - 0 <= word1.length, word2.length <= 500
 * - word1 and word2 consist of lowercase English letters.
 *
 * n: word1.length, m: word2.length
 * 시간 복잡도 : O(n * m)
 * 공간 복잡도 : O(n * m)
 */
class `72` {
    fun minDistance(word1: String, word2: String): Int {
        val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }
        (1..word1.length).forEach { i ->
            dp[i][0] = i
        }
        (1..word2.length).forEach { i ->
            dp[0][i] = i
        }
        (1..word1.length).forEach { i ->
            (1..word2.length).forEach { j ->
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = minOf(
                        dp[i - 1][j],
                        dp[i][j - 1],
                        dp[i - 1][j - 1],
                    ) + 1
                }
            }
        }
        return dp[word1.length][word2.length]
    }
}

fun main() {
    val solution = `72`()
    println(solution.minDistance(word1 = "horse", word2 = "ros")) // 3
    println(solution.minDistance(word1 = "intention", word2 = "execution")) // 5
}
