package leetcode.medium

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 * Anagram?
 * - An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.
 *
 * Constraints:
 * - 1 <= s.length, p.length <= 3 * 10^4
 * - s and p consist of lowercase English letters.
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 */
class `438_2` {
    fun findAnagrams(s: String, p: String): List<Int> {
        val windowSize = p.length.takeIf { (s.length - p.length) >= 0 } ?: return listOf()
        val pMap = mutableMapOf<Char, Int>()
            .apply { p.forEach { c -> this[c] = this.getOrPut(c) { 0 } + 1 } }
        (0 until windowSize).forEach { i ->
            pMap[s[i]]?.let { count ->
                pMap[s[i]] = count - 1
            }
        }
        val startIndexes = mutableListOf<Int>()
        (0..(s.length - windowSize)).forEach { left ->
            if (pMap.values.all { it == 0 }) {
                startIndexes.add(left)
            }
            val right = left + windowSize
            if (right >= s.length) {
                return@forEach
            }
            pMap[s[right]]?.let { count ->
                pMap[s[right]] = count - 1
            }
            pMap[s[left]]?.let { count ->
                pMap[s[left]] = count + 1
            }
        }
        return startIndexes
    }
}

fun main() {
    val solution = `438_2`()
    println(solution.findAnagrams("cbaebabacd", "abc")) // [0, 6]
    println(solution.findAnagrams("abab", "ab")) // [0, 1, 2]
    println(solution.findAnagrams("vncba", "abc")) // [2]
}
