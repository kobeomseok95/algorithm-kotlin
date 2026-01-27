package leetcode.medium

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Constraints:
 *  0 <= s.length <= 5 * 10^4
 *  s consists of English letters, digits, symbols and spaces.
 *
 * 기존 풀이에서 중복된 문자가 발생할 경우 이전에 발견한 문자의 인덱스 + 1 위치로 이동시켜서 시간 효율성 증대
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(min(n, m)) -> n: 문자열 최대 길이 / m: 최대 가능 문자 수
 */
class `3_2` {
    fun lengthOfLongestSubstring(s: String): Int {
        var answer = 0
        var left = 0
        val characterMap = mutableMapOf<Char, Int>()
        for (right in s.indices) {
            val character = s[right]
            if (character in characterMap) {
                left = maxOf(left, characterMap[character]!! + 1)
            }
            characterMap[character] = right
            answer = maxOf(answer, right - left + 1)
        }
        return answer
    }
}

fun main() {
    val solution = `3_2`()
    println( // 3
        solution.lengthOfLongestSubstring("abcabcbb")
    )
    println( // 4
        solution.lengthOfLongestSubstring("abcabcd")
    )
    println( // 1
        solution.lengthOfLongestSubstring("bbbbb")
    )
    println( // 3
        solution.lengthOfLongestSubstring("pwwkew")
    )
    println( // 5
        solution.lengthOfLongestSubstring("abcdecfg")
    )
    println( // 2
        solution.lengthOfLongestSubstring("abba")
    )
}
