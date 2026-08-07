package leetcode.medium

/**
 * A digit string is good if the digits (0-indexed) at even indices are even and the digits at odd
 * indices are prime (2, 3, 5, or 7).
 *
 * For example, "2582" is good because the digits (2 and 8) at even positions are even and the digits
 * (5 and 2) at odd positions are prime. However, "3245" is not good because 3 is at an even index
 * but is not even.
 *
 * Given an integer n, return the total number of good digit strings of length n. Since the answer
 * may be large, return it modulo 10^9 + 7.
 *
 * A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
 *
 * Constraints:
 * - 1 <= n <= 10^15
 *
 * 시간 복잡도: O(log N)
 * 공간 복잡도: O(1)
 */
class `1922` {
    fun countGoodNumbers(n: Long): Int {
        val oddLength = n / 2
        val evenLength = (n + 1) / 2
        val a = 5.power(evenLength)
        val b = 4.power(oddLength)
        return (a * b % MOD).toInt()
    }

    private fun Int.power(requestExponent: Long): Long {
        var value = 1L
        var base = this.toLong()
        var exponent = requestExponent
        while (exponent > 0) {
            if (exponent % 2 == 1L) {
                value = (value * base) % MOD
            }
            base = (base * base) % MOD
            exponent /= 2
        }
        return value
    }

    companion object {
        private const val MOD = 1_000_000_007
    }
}

fun main() {
    val solution = `1922`()
    println(solution.countGoodNumbers(1L)) // 5
    println(solution.countGoodNumbers(4L)) // 400
    println(solution.countGoodNumbers(50L)) // 564908303
}
