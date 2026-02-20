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
 * 공간 복잡도 : O(min(n, m))
 */
class `72_2` {
    fun minDistance(word1: String, word2: String): Int {
        val wordSet = getWordSet(word1, word2)
        val dp = IntArray(wordSet.short.length + 1) { it }
        wordSet.long.forEachIndexed { i, longChar ->
            val prevTemp = dp[0]
            dp[0] = i + 1
            var prev = prevTemp
            wordSet.short.forEachIndexed { j, shortChar ->
                val temp = dp[j + 1]
                if (longChar == shortChar) {
                    dp[j + 1] = prev
                } else {
                    dp[j + 1] = minOf(prev, dp[j], dp[j + 1]) + 1
                }
                prev = temp
            }
        }
        return dp[wordSet.short.length]
    }

    private fun getWordSet(word1: String, word2: String): WordSet {
        return if (word1.length > word2.length) {
            WordSet(word2, word1)
        } else {
            WordSet(word1, word2)
        }
    }

    private data class WordSet(
        val short: String,
        val long: String,
    )
}

fun main() {
    val solution = `72_2`()
    println(solution.minDistance(word1 = "horse", word2 = "ros")) // 3
    println(solution.minDistance(word1 = "intention", word2 = "execution")) // 5
}
