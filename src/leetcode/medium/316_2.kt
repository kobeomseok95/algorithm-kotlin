package leetcode.medium

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s consists of lowercase English letters.
 *
 * a: 문자열에서의 중복되지 않은 알파벳 수
 * n: 문자열 길이
 * 시간 복잡도: O(2n)
 * 공간 복잡도: O(2a)
 */
class `316_2` {
    fun removeDuplicateLetters(s: String): String {
        val map = s.mapIndexed { index, c -> c to index }.toMap()
        val stringBuilder = StringBuilder()
        val set = mutableSetOf<Char>()
        s.forEachIndexed { index, c ->
            if (c in set) {
                return@forEachIndexed
            }

            if (stringBuilder.isEmpty() || stringBuilder.last() < c) {
                stringBuilder.append(c)
                set.add(c)
            } else {
                while (stringBuilder.isNotEmpty() && stringBuilder.last() > c && (map[stringBuilder.last()] ?: throw RuntimeException()) > index) {
                    set.remove(stringBuilder.last())
                    stringBuilder.deleteAt(stringBuilder.lastIndex)
                }
                stringBuilder.append(c)
                set.add(c)
            }
        }
        return stringBuilder.toString()
    }
}

fun main() {
    val solution = `316_2`()
    println(solution.removeDuplicateLetters("bcabc")) // abc
    println(solution.removeDuplicateLetters("cbacdcbc")) // acdb
    println(solution.removeDuplicateLetters("abacb")) // abc
}
