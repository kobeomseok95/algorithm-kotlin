package leetcode.medium

import java.util.Stack

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
 * 시간 복잡도: O(n + (n * a))
 * 공간 복잡도: O(2a)
 */
class `316` {
    fun removeDuplicateLetters(s: String): String {
        val map = s.mapIndexed { index, c -> c to index }.toMap()
        val stack = Stack<Char>()
        s.forEachIndexed { index, c ->
            if (c in stack) {
                return@forEachIndexed
            }

            if (stack.isEmpty() || stack.peek() < c) {
                stack.push(c)
            } else {
                while (stack.isNotEmpty() && stack.peek() > c && (map[stack.peek()] ?: throw RuntimeException()) > index) {
                    stack.pop()
                }
                stack.push(c)
            }
        }
        return stack.joinToString("")
    }
}

fun main() {
    val solution = `316`()
    println(solution.removeDuplicateLetters("bcabc")) // abc
    println(solution.removeDuplicateLetters("cbacdcbc")) // acdb
    println(solution.removeDuplicateLetters("abacb")) // abc
}
