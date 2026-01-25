package leetcode.medium

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Constraints:
 *  0 <= s.length <= 5 * 10^4
 *  s consists of English letters, digits, symbols and spaces.
 *
 * 1. 슬라이딩 윈도우를 0 ~ 0 으로 설정하여 시작한다.
 * 2. 중복이 없을 때 까지 오른쪽 인덱스 증가
 * 3. 중복이 있으면 왼쪽 인덱스 증가 (중복 해소될 때까지)
 * 4. 새 위치에서 2번 과정 반복
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(min(n, m))
 */
class `3` {
    fun lengthOfLongestSubstring(s: String): Int {
        var answer = 0
        var left = 0
        var right = 0
        val characterSet = mutableSetOf<Char>()
        while (left < s.length && right < s.length) {
            while (right < s.length && s[right] !in characterSet) {
                characterSet.add(s[right])
                right += 1
            }
            answer = maxOf(answer, right - left)
            characterSet.remove(s[left])
            left += 1
        }

        return answer
    }
}

fun main() {
    val solution = `3`()
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
}
