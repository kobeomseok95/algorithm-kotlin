package leetcode.medium

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * Constraints:
 *  1 <= s.length, p.length <= 3 * 10^4
 *  s and p consist of lowercase English letters.
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(26) -> O(1)
 */
class `438` {
    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.length < p.length) {
            return listOf()
        }
        val answer = mutableListOf<Int>()
        val counts = IntArray(26) { 0 }
        p.toCharArray().forEach { char -> counts[char - 'a'] += 1 }
        p.indices.forEach { counts[s[it] - 'a'] -= 1 }
        if (counts.all { it == 0 }) {
            answer.add(0)
        }
        for (right in p.length until s.length) {
            val left = right - p.length
            counts[s[left] - 'a'] += 1
            counts[s[right] - 'a'] -= 1
            if (counts.all { it == 0 }) {
                answer.add(left + 1)
            }
        }
        return answer
    }
}

fun main() {
    val solution = `438`()
    println(solution.findAnagrams("cbaebabacd", "abc")) // [0, 6]
    println(solution.findAnagrams("abab", "ab")) // [0, 1, 2]
}
