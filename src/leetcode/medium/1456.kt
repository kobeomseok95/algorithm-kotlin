package leetcode.medium

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(1)
 */
class `1456` {
    fun maxVowels(s: String, k: Int): Int {
        var answer = 0
        for (i in 0 until k) {
            if (isVowel(s[i])) {
                answer += 1
            }
        }

        var current = answer
        for (i in k until s.length) {
            if (isVowel(s[i - k])) {
                current -= 1
            }
            if (isVowel(s[i])) {
                current += 1
            }
            answer = maxOf(answer, current)
        }
        return answer
    }

    private fun isVowel(c: Char): Boolean {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
    }
}
