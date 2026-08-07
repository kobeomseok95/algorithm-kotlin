package leetcode.medium

import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.pow

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 *
 * Constraints:
 * - -100.0 < x < 100.0
 * - -2^31 <= n <= 2^31-1
 * - n is an integer.
 * - Either x is not zero or n > 0.
 * - -10^4 <= x^n <= 10^4
 *
 * 시간 복잡도: O(log N)
 * 공간 복잡도: O(1)
 */
class `50` {
    fun myPow(x: Double, n: Int): Double {
        var base = x
        var exponent = n
        var answer = 1.0
        while (exponent != 0) {
            if (exponent % 2 != 0) {
                answer *= base
            }
            exponent /= 2
            base *= base
        }
        return answer.takeIf { n >= 0 } ?: (1 / answer)
    }
}

fun main() {
    val solution = `50`()
    println(solution.myPow(2.00000, 13)) // 8192.00000
    println(solution.myPow(3.00000, 3)) // 27.00000
    println(solution.myPow(2.00000, 6)) // 64.00000
    println(solution.myPow(2.10000, 3)) // 9.26100
    println(solution.myPow(2.00000, 2)) // 4.00000
    println(solution.myPow(2.00000, 10)) // 1024.00000
    println(solution.myPow(2.00000, -2)) // 0.25000
}
