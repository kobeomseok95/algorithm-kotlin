package leetcode.medium

/**
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of lowercase English letters.
 * - 1 <= k <= s.length
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(1)
 */
class `1456_2` {
    fun maxVowels(s: String, k: Int): Int {
        var counts = s.substring(0, k).count { it in VOWELS }
        var answer = counts
        (1..s.length - k).forEach { left ->
            val right = left + k - 1
            val removeCount = s[left - 1].vowelCount()
            val addCount = s[right].vowelCount()
            counts += addCount - removeCount
            answer = maxOf(answer, counts)
        }
        return answer
    }

    private fun Char.vowelCount(): Int {
        return if (this in VOWELS) {
            1
        } else {
            0
        }
    }

    companion object {
        private val VOWELS = setOf('a', 'e', 'i', 'o', 'u')
    }
}

fun main() {
    val solution = `1456_2`()
    println(solution.maxVowels("abciiidef", 3)) // 3
    println(solution.maxVowels("aeiou", 2)) // 2
    println(solution.maxVowels("leetcode", 3)) // 2
}
