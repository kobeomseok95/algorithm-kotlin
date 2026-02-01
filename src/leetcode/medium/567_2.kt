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
 * 공간 복잡도 : O(1)
 */
class `567_2` {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s1.length > s2.length) {
            return false
        }
        val frequency = IntArray(ALPHABET_SIZE) { 0 }
        var zeroCounts = ALPHABET_SIZE
        s1.toCharArray().forEach { char ->
            zeroCounts = frequency.updateFrequencyAtIndexByDelta(
                index = char - 'a',
                delta = 1,
                zeroCounts = zeroCounts,
            )
        }
        s1.indices.forEach { i ->
            zeroCounts = frequency.updateFrequencyAtIndexByDelta(
                index = s2[i] - 'a',
                delta = -1,
                zeroCounts = zeroCounts,
            )
        }
        if (zeroCounts == ALPHABET_SIZE) {
            return true
        }

        for (i in s1.length until s2.length) {
            val left = i - s1.length
            zeroCounts = frequency.updateFrequencyAtIndexByDelta(
                index = s2[left] - 'a',
                delta = 1,
                zeroCounts = zeroCounts,
            )
            zeroCounts = frequency.updateFrequencyAtIndexByDelta(
                index = s2[i] - 'a',
                delta = -1,
                zeroCounts = zeroCounts,
            )

            if (zeroCounts == ALPHABET_SIZE) {
                return true
            }
        }
        return false
    }

    private fun IntArray.updateFrequencyAtIndexByDelta(
        index: Int,
        delta: Int,
        zeroCounts: Int,
    ): Int {
        var count = zeroCounts
        if (this[index] == 0) {
            count -= 1
        }
        this[index] += delta
        if (this[index] == 0) {
            count += 1
        }
        return count
    }

    companion object {
        private const val ALPHABET_SIZE = 26
    }
}

fun main() {
    val solution = `567_2`()
    println(solution.checkInclusion("abc", "bbbca")) // true
    println(solution.checkInclusion("adc", "dcda")) // true
    println(solution.checkInclusion("ab", "eidbaooo")) // true
    println(solution.checkInclusion("ab", "eidboaoo")) // false
}
