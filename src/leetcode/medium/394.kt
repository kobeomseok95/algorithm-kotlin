package leetcode.medium

import java.util.Stack

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n)
 */
class `394` {
    fun decodeString(s: String): String {
        val stack = Stack<StringCount>()
        var currentString = StringBuilder()
        val currentDigit = StringBuilder()
        for (c in s) {
            when {
                c == START_BRACKET -> {
                    stack.push(StringCount.of(currentString.toString(), currentDigit.toString().toInt()))
                    currentString.clear()
                    currentDigit.clear()
                }
                c == END_BRACKET -> {
                    currentString = getCurrentString(stack.pop(), currentString)
                }
                c.isDigit() -> currentDigit.append(c)
                else -> currentString.append(c)
            }
        }
        return currentString.toString()
    }

    private fun getCurrentString(
        stringCount: StringCount,
        currentString: StringBuilder,
    ): StringBuilder {
        val stringBuilder = StringBuilder()
        stringBuilder.append(stringCount.string)
        stringBuilder.append(getCountAppliedString(stringCount.count, currentString.toString()))
        return stringBuilder
    }

    private fun getCountAppliedString(
        count: Int,
        string: String,
    ): StringBuilder {
        val stringBuilder = StringBuilder()
        for (i in 0 until count) {
            stringBuilder.append(string)
        }
        return stringBuilder
    }

    private data class StringCount(
        val string: String,
        val count: Int,
    ) {
        companion object {
            fun of(string: String, count: Int): StringCount {
                return StringCount(
                    string = string,
                    count = count,
                )
            }
        }
    }

    companion object {
        private const val START_BRACKET = '['
        private const val END_BRACKET = ']'
    }
}

fun main() {
    val solution = `394`()
    println(solution.decodeString("3[a]2[bc]")) // "aaabcbc"
    println(solution.decodeString("3[a]2[bc]") == "aaabcbc")
    println(solution.decodeString("3[a2[c]]")) // "accaccacc"
    println(solution.decodeString("3[a2[c]]") == "accaccacc")
    println(solution.decodeString("3[a2[cd]c]")) // "acdcdcacdcdcacdcdc"
    println(solution.decodeString("3[a2[cd]c]") == "acdcdcacdcdcacdcdc")
    println(solution.decodeString("2[abc]3[cd]ef")) // "abcabccdcdcdef"
    println(solution.decodeString("2[abc]3[cd]ef") == "abcabccdcdcdef")
}
