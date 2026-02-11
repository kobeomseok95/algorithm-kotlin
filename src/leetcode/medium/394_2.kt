package leetcode.medium

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 10^5.
 *
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 *
 * n: s.length / d: 괄호 세트 갯수 / output: 10^5
 * 시간 복잡도: O(output)
 * 공간 복잡도: O(output)
 */
class `394_2` {
    fun decodeString(s: String): String {
        return dfs(s).toString()
    }

    private fun dfs(
        s: String,
        index: Int = 0,
    ): IndexString {
        val digit = StringBuilder()
        val string = StringBuilder()
        var currentIndex = index
        while (currentIndex < s.length) {
            val currentChar = s[currentIndex]
            when {
                currentChar.isDigit() -> {
                    digit.append(currentChar)
                }
                currentChar == OPEN_BRACKET -> {
                    val newStr = dfs(s, index = currentIndex + 1)
                    string.append(newStr.string.toString().repeat(digit.toString().toInt()))
                    digit.clear()
                    currentIndex = newStr.index
                }
                currentChar == CLOSE_BRACKET -> {
                    return IndexString(
                        index = currentIndex,
                        string = string,
                    )
                }
                else -> {
                    string.append(currentChar)
                }
            }
            currentIndex += 1
        }
        return IndexString(
            index = currentIndex,
            string = string,
        )
    }

    private data class IndexString(
        val index: Int,
        val string: StringBuilder,
    ) {
        override fun toString(): String {
            return string.toString()
        }
    }

    companion object {
        private const val OPEN_BRACKET = '['
        private const val CLOSE_BRACKET = ']'
    }
}

fun main() {
    val solution = `394_2`()
    println(solution.decodeString("3[a]2[bc]")) // "aaabcbc"
    println(solution.decodeString("3[a]2[bc]") == "aaabcbc")
    println(solution.decodeString("3[a2[c]]")) // "accaccacc"
    println(solution.decodeString("3[a2[c]]") == "accaccacc")
    println(solution.decodeString("3[a2[cd]c]")) // "acdcdcacdcdcacdcdc"
    println(solution.decodeString("3[a2[cd]c]") == "acdcdcacdcdcacdcdc")
    println(solution.decodeString("2[abc]3[cd]ef")) // "abcabccdcdcdef"
    println(solution.decodeString("2[abc]3[cd]ef") == "abcabccdcdcdef")
}
