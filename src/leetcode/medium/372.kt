package leetcode.medium

/**
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large
 * positive integer given in the form of an array.
 *
 * Constraints:
 * - 1 <= a <= 2^31 - 1
 * - 1 <= b.length <= 2000
 * - 0 <= b[i] <= 9
 * - b does not contain leading zeros.
 *
 * 시간 복잡도: O(N) / N = b.length
 * 공간 복잡도: O(1)
 */
class `372` {
    fun superPow(a: Int, b: IntArray): Int {
        var powered = 1
        for (i in b.indices) {
            powered = (powered.modPow(exponent = 10) * a.modPow(exponent = b[i])) % MOD
        }
        return powered
    }

    private fun Int.modPow(exponent: Int): Int {
        val base = this % MOD
        var powered = 1
        for (i in 0 until exponent) {
            powered = (powered * base) % MOD
        }
        return powered
    }

    companion object {
        private const val MOD = 1337
    }
}

fun main() {
    val solution = `372`()
    println(solution.superPow(1337, intArrayOf(5))) // 0
    println(solution.superPow(2, intArrayOf(1, 0))) // 1024
    println(solution.superPow(2147483647, intArrayOf(2, 0, 0))) // 1198
    println(solution.superPow(2, intArrayOf(3))) // 8
    println(solution.superPow(1, intArrayOf(4, 3, 3, 8, 5, 2))) // 1
}
