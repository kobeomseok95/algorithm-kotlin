package leetcode.medium

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Constraints:
 *  0 <= s.length <= 5 * 10^4
 *  s consists of English letters, digits, symbols and spaces.
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n) -> 최대 English letters, digits, symbols and spaces 갯수
 */
class `3_Repeat` {
    fun lengthOfLongestSubstring(s: String): Int {
        var answer = 0
        var left = 0
        val characterIndexMap = mutableMapOf<Char, Int>()
        s.forEachIndexed { right, c ->
            characterIndexMap[c]?.let {
                left = maxOf(left, it + 1)
            }
            characterIndexMap[c] = right
            answer = maxOf(answer, right - left + 1)
        }
        return answer
    }
}

fun main() {
    val solution = `3_Repeat`()
    println(solution.lengthOfLongestSubstring("abcabcbb")) // 3
    println(solution.lengthOfLongestSubstring("abcabcd")) // 4
    println(solution.lengthOfLongestSubstring("bbbbb")) // 1
    println(solution.lengthOfLongestSubstring("pwwkew")) // 3
    println(solution.lengthOfLongestSubstring("abcdecfg")) // 5
    println(solution.lengthOfLongestSubstring("abba")) // 2
}
