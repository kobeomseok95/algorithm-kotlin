package leetcode.hard

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring
 * of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * Constraints:
 * - m == s.length
 * - n == t.length
 * - 1 <= m, n <= 10^5
 * - s and t consist of uppercase and lowercase English letters.
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `76` {
    fun minWindow(s: String, t: String): String {
        val tMap = getCharMapBy(t)
        var left = 0
        var right = 0
        var window = 0 to MAX_LENGTH
        while (right < s.length && left <= right) {
            if (tMap.isAllUsed()) {
                if (window.second - window.first > (right - left)) {
                    window = left to right
                }
                tMap.inc(s[left])
                left++
            } else {
                tMap.dec(s[right])
                right++
            }
        }
        while (left < right) {
            if (tMap.isAllUsed()) {
                if (window.second - window.first > (right - left)) {
                    window = left to right
                }
                tMap.inc(s[left])
                left++
            } else {
                break
            }
        }
        return if (window.second - window.first == MAX_LENGTH) {
            ""
        } else {
            s.substring(window.first, window.second)
        }
    }

    private fun getCharMapBy(t: String): MutableMap<Char, Int> {
        val map = mutableMapOf<Char, Int>()
        t.forEach { char -> map[char] = map.getOrDefault(char, 0) + 1 }
        return map
    }

    private fun MutableMap<Char, Int>.isAllUsed(): Boolean {
        return this.entries.all { (_, v) -> v <= 0 }
    }

    private fun MutableMap<Char, Int>.inc(key: Char) {
        this[key]?.let { this[key] = it + 1 }
    }

    private fun MutableMap<Char, Int>.dec(key: Char) {
        this[key]?.let { this[key] = it - 1 }
    }

    companion object {
        private const val MAX_LENGTH = 100_001
    }
}

fun main() {
    val solution = `76`()
    println(solution.minWindow("ab", "b")) // "b"
    println(solution.minWindow("ADOBECODEBANC", "ABC")) // "BANC"
    println(solution.minWindow("a", "a")) // "a"
    println(solution.minWindow("a", "aa")) // ""
    println(solution.minWindow("ab", "aa")) // ""
}
