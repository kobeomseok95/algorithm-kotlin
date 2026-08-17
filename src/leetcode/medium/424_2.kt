package leetcode.medium

/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of only uppercase English letters.
 * - 0 <= k <= s.length
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1)
 */
class `424_2` {
    fun characterReplacement(s: String, k: Int): Int {
        var replacement = 0
        var left = 0
        val characterMap = mutableMapOf<Char, Int>()
        for (right in s.indices) {
            characterMap[s[right]] = (characterMap[s[right]] ?: 0) + 1
            if ((right - left + 1) - characterMap.getMax() > k) {
                characterMap[s[left]] = (characterMap[s[left]] ?: 0) - 1
                left += 1
            }

            replacement = maxOf(replacement, right - left + 1)
        }
        return replacement
    }

    private fun Map<Char, Int>.getMax(): Int {
        return this.values.max()
    }
}

fun main() {
    val solution = `424_2`()
    println(solution.characterReplacement("EBBCD", 1)) // 3
    println(solution.characterReplacement("ABC", 1)) // 2
    println(solution.characterReplacement("ABAB", 2)) // 4
    println(solution.characterReplacement("AABABBA", 1)) // 4
}
