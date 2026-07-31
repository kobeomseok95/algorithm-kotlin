package leetcode.medium

import kotlin.math.pow

/**
 * Given an integer n, return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 *
 * Constraints:
 * - 1 <= n <= 2^31 - 1
 *
 * 시간 복잡도: O(logN)
 * 공간 복잡도: O(1)
 */
class `400` {
    fun findNthDigit(n: Int): Int {
        var digits = 0
        var from = 0L
        var to = 0L
        var tenBased = 1L
        while (n > to) {
            digits++
            from = to + 1
            tenBased = 10.0.pow(digits - 1).toLong()
            to = from + (9L * tenBased * digits) - 1
        }
        val offset = n - from
        return (tenBased + (offset / digits)).toString()[(offset % digits).toInt()].digitToInt()
    }
}

fun main() {
    val solution = `400`()
    println(solution.findNthDigit(3)) // 3
    println(solution.findNthDigit(11)) // 0
    println(solution.findNthDigit(1000)) // 3
}
