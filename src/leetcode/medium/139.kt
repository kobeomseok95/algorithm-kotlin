package leetcode.medium

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Constraints:
 * - 1 <= s.length <= 300
 * - 1 <= wordDict.length <= 1000
 * - 1 <= wordDict[i].length <= 20
 * - s and wordDict[i] consist of only lowercase English letters.
 * - All the strings of wordDict are unique.
 *
 * N: s.length, M: wordDict[i].length, L: wordDict.length
 * 시간 복잡도: O(N * M^2)
 * 공간 복잡도: O(N + L)
 */
class `139` {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dictionary = wordDict.toSet()
        val dp = BooleanArray(s.length + 1).apply { this[0] = true }
        for (i in 1..s.length) {
            var result = false
            for (j in 0 until i) {
                val substring = s.substring(j, i)
                result = dp[j] && substring in dictionary
                if (result) {
                    break
                }
            }
            dp[i] = result
        }
        return dp[s.length]
    }
}

fun main() {
    val solution = `139`()
    println(solution.wordBreak("aaaaaaa", listOf("aaaa","aaa"))) // true
    println(solution.wordBreak("leetcode", listOf("leet", "code"))) // true
    println(solution.wordBreak("applepenapple", listOf("apple", "pen"))) // true
    println(solution.wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat"))) // false
}
