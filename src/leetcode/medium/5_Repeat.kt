package leetcode.medium

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s consist of only digits and English letters.
 *
 * 시간 복잡도: O(N^2)
 * 공간 복잡도: O(1)
 */
class `5_Repeat` {
    fun longestPalindrome(s: String): String {
        var range = Range.of(0, 0)
        (0 until s.length).forEach { mid ->
            val oddCase = getLengthCase(s, mid, mid)
            val evenCase = getLengthCase(s, mid, mid + 1)
            val largestCase = evenCase.takeIf { it.length > oddCase.length } ?: oddCase
            range = largestCase.takeIf { it.length > range.length } ?: range
        }
        return s.substring(range.start, range.endInclusive + 1)
    }

    private fun getLengthCase(s: String, start: Int, end: Int): Range {
        var left = start
        var right = end
        while (left >= 0 && right < s.length && s[left] == s[right]) {
            left--
            right++
        }
        return Range(left + 1, right - 1)
    }

    private data class Range(
        val start: Int = 0,
        val endInclusive: Int = 0,
    ) {
        val length
            get() = endInclusive - start + 1

        companion object {
            fun of(start: Int, endInclusive: Int): Range {
                return Range(start, endInclusive)
            }
        }
    }
}

fun main() {
    val solution = `5_Repeat`()
    println(solution.longestPalindrome("abcdebba")) // "bb"
    println(solution.longestPalindrome("bbccc")) // "ccc"
    println(solution.longestPalindrome("babad")) // "bab" (or "aba")
    println(solution.longestPalindrome("cbbd")) // "bb"
    println(solution.longestPalindrome("cbbabbc")) // "cbbabbc"
    println(solution.longestPalindrome("ba")) // "b"
}
