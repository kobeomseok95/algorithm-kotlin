package leetcode.medium

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * Constraints:
 *  1 <= s1.length, s2.length <= 10^4
 *  s1 and s2 consist of lowercase English letters.
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(26 -> 1)
 */
class `567` {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) {
            return false
        }
        val s1Frequency = IntArray(26) { 0 }
        s1.forEach { c -> s1Frequency[c - 'a'] += 1 }
        val s2Frequency = IntArray(26) { 0 }
        s1.indices.forEach { i -> s2Frequency[s2[i] - 'a'] += 1 }

        (0 until s2.length - s1.length + 1).forEach { i ->
            if (s1Frequency.contentEquals(s2Frequency)) {
                return true
            }
            if (i + s1.length < s2.length) {
                s2Frequency[s2[i] - 'a'] -= 1
                s2Frequency[s2[i + s1.length] - 'a'] += 1
            }
        }
        return false
    }
}

fun main() {
    val solution = `567`()
    println(solution.checkInclusion("ab", "eidbaooo")) // true
    println(solution.checkInclusion("ab", "eidboaoo")) // false
}
