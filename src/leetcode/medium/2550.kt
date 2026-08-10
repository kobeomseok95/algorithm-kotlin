package leetcode.medium

/**
 * There is a regular convex polygon with n vertices. The vertices are labeled from 0 to n - 1 in a
 * clockwise direction, and each vertex has exactly one monkey. The following figure shows a convex
 * polygon of 6 vertices.
 *
 * Simultaneously, each monkey moves to a neighboring vertex. A collision happens if at least two
 * monkeys reside on the same vertex after the movement or intersect on an edge.
 *
 * Return the number of ways the monkeys can move so that at least one collision happens. Since the
 * answer may be very large, return it modulo 10^9 + 7.
 *
 * Constraints:
 * - 3 <= n <= 10^9
 *
 * 시간 복잡도: O(log N)
 * 공간 복잡도: O(1)
 */
class `2550` {
    fun monkeyMove(n: Int): Int {
        var base = 2L
        var exponent = n
        var counts = 1L
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                counts = (counts * base) % MOD
            }
            exponent /= 2
            base = (base * base) % MOD
        }
        return ((counts - 2).takeIf { it >= 0 } ?: (MOD - counts)).toInt()
    }

    companion object {
        private const val MOD = 1_000_000_007
    }
}

fun main() {
    val solution = `2550`()
    println(solution.monkeyMove(500000003)) // 1000000006
    println(solution.monkeyMove(55)) // 766762394
    println(solution.monkeyMove(3)) // 6
    println(solution.monkeyMove(4)) // 14
}
