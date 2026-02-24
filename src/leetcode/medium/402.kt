package leetcode.medium

/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 *
 * Constraints:
 * - 1 <= k <= num.length <= 10^5
 * - num consists of only digits.
 * - num does not have any leading zeros except for the zero itself.
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n)
 */
class `402` {
    fun removeKdigits(num: String, k: Int): String {
        val stringBuilder = StringBuilder()
        var count = 0
        num.forEach { n ->
            while (
                stringBuilder.isNotEmpty() &&
                count < k &&
                stringBuilder.last() > n
            ) {
                stringBuilder.deleteAt(stringBuilder.lastIndex)
                count += 1
            }
            stringBuilder.append(n)
        }
        while (count < k) {
            stringBuilder.deleteAt(stringBuilder.lastIndex)
            count += 1
        }
        return stringBuilder.toString().trimStart('0').ifEmpty { "0" }
    }
}

fun main() {
    val solution = `402`()
    println(solution.removeKdigits("1432219", 3)) // "1219"
    println(solution.removeKdigits("10200", 1)) // "200"
    println(solution.removeKdigits("10", 2)) // "0"
    println(solution.removeKdigits("472865", 2)) // "2865"
    println(solution.removeKdigits("54321", 2)) // "321"
    println(solution.removeKdigits("12345", 2)) // "123"
    println(solution.removeKdigits("9", 1)) // "0"
}
