package leetcode.medium

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * 1. Whitespace: Ignore any leading whitespace (" ").
 * 2. Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
 * 3. Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
 * 4. Rounding: If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], then round the integer to remain in the range. Specifically, integers less than -2^31 should be rounded to -2^31, and integers greater than 2^31 - 1 should be rounded to 2^31 - 1.
 *
 * Return the integer as the final result.
 *
 * Constraints:
 * - 0 <= s.length <= 200
 * - s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `8` {
    fun myAtoi(s: String): Int {
        var index = 0
        while (index < s.length && s[index] == WHITE_SPACE) {
            index++
        }
        val sign = if (index < s.length && s[index].isSign()) {
            s[index].also { index++ }
        } else {
            PLUS
        }
        val (maxAbs, signValue) = if (sign == PLUS) {
            PLUS_MAX to 1
        } else {
            MINUS_MAX to -1
        }

        var positiveResult = 0L
        while (index < s.length && s[index].isDigit() && positiveResult < maxAbs) {
            positiveResult = positiveResult * 10 + s[index].digitToInt()
            index++
        }

        return (positiveResult.coerceAtMost(maxAbs)).times(signValue).toInt()
    }

    private fun Char.isSign(): Boolean {
        return this == PLUS || this == MINUS
    }

    companion object {
        private const val PLUS = '+'
        private const val MINUS = '-'
        private const val WHITE_SPACE = ' '

        private const val PLUS_MAX = Int.MAX_VALUE.toLong()
        private const val MINUS_MAX = Int.MAX_VALUE.toLong() + 1L
    }
}

fun main() {
    val solution = `8`()
    println(solution.myAtoi("  ")) // 0
    println(solution.myAtoi("  +  413")) // 0
    println(solution.myAtoi("-2147483647")) // -2147483647
    println(solution.myAtoi("-2147483649")) // -2147483648
    println(solution.myAtoi("-2147483648")) // -2147483648
    println(solution.myAtoi("2147483648")) // 2147483647
    println(solution.myAtoi("2147483647")) // 2147483647
    println(solution.myAtoi("54-1")) // 54
    println(solution.myAtoi("+-+3")) // 0
    println(solution.myAtoi("42")) // 42
    println(solution.myAtoi(" -042")) // -42
    println(solution.myAtoi("1337c0d3")) // 1337
    println(solution.myAtoi("0-1")) // 0
    println(solution.myAtoi("words and 987")) // 0
}
