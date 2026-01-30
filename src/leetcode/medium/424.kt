package leetcode.medium

/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Constraints:
 *  1 <= s.length <= 10^5
 *  s consists of only uppercase English letters.
 *  0 <= k <= s.length
 *
 * 특정 구간에서 가장 많은 빈도가 나오는 알파벳을 제외한 나머지 알파벳 수가 k 를 넘지 않는다면 참인 조건으로 풀이한다.
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(26) = O(1)
 */
class `424` {
    fun characterReplacement(s: String, k: Int): Int {
        var left = 0
        var answer = 0
        val frequency = mutableMapOf<Char, Int>()
        for (right in s.indices) {
            frequency[s[right]] = (frequency[s[right]] ?: 0) + 1
            if (right - left + 1 - frequency.getMax() > k) {
                frequency[s[left]]?.takeIf { it > 0 }?.let {
                    frequency[s[left]] = it - 1
                    left += 1
                }
            }

            answer = maxOf(answer, right - left + 1)
        }
        return answer
    }

    private fun Map<Char, Int>.getMax(): Int {
        return this.values.maxOrNull() ?: 0
    }
}

fun main() {
    val solution = `424`()
    println(solution.characterReplacement("ABAB", 2)) // 4
    println(solution.characterReplacement("AABABBA", 1)) // 4
}
