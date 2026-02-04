package leetcode.medium

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Constraints:
 *  - 1 <= s.length <= 1000
 *  - s consist of only digits and English letters.
 *
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(n)
 */
class `5` {
    fun longestPalindrome(s: String): String {
        var range = IntRange(start = 0, endInclusive = 0)
        for (i in s.indices) {
            val odd = getIndexRange(s, i, i)
            val even = getIndexRange(s, i, i + 1)
            val longest = if (odd.getLength() >= even.getLength()) {
                odd
            } else {
                even
            }
            range = longest.takeIf { it.getLength() > range.getLength() } ?: range
        }
        return s.substring(range)
    }

    private fun getIndexRange(
        s: String,
        start: Int,
        endInclusive: Int,
    ): IntRange {
        var left = start
        var right = endInclusive
        while (left >= 0 && right < s.length && s[left] == s[right]) {
            left -= 1
            right += 1
        }
        return IntRange(start = left + 1, endInclusive = right - 1)
    }

    private fun IntRange.getLength(): Int {
        return endInclusive - start + 1
    }
}

fun main() {
    val solution = `5`()
    println(solution.longestPalindrome("babad")) // "bab" or "aba"
    println(solution.longestPalindrome("cbbd")) // "bb"
    println(solution.longestPalindrome("a")) // "a"
    println(solution.longestPalindrome("ac")) // "a"
    println(solution.longestPalindrome("bb")) // "bb"
}
