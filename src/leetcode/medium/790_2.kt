package leetcode.medium

/**
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 * <img src="https://assets.leetcode.com/uploads/2021/07/15/lc-domino.jpg">
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 *
 * Constraints:
 * - 1 <= n <= 1000
 *
 * 필요한 변수는 현재 / 1칸 전 / 3칸 전이므로 세 개의 변수로 풀이할 수 있다.
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(1)
 */
class `790_2` {
    fun numTilings(n: Int): Int {
        if (n <= 2) {
            return n
        }
        var beforeThree = 1L
        var beforeTwo = 1L
        var beforeOne = 2L
        var current = 0L
        (3..n).forEach { _ ->
            current = (2 * beforeOne + beforeThree) % MODULO
            beforeThree = beforeTwo
            beforeTwo = beforeOne
            beforeOne = current
        }
        return current.toInt()
    }

    companion object {
        private const val MODULO = 1_000_000_000 + 7
    }
}

fun main() {
    val solution = `790_2`()
    println(solution.numTilings(1)) // 1
    println(solution.numTilings(2)) // 2
    println(solution.numTilings(3)) // 5
    println(solution.numTilings(4)) // 11
    println(solution.numTilings(5)) // 24
    println(solution.numTilings(30)) // 312342182
}
