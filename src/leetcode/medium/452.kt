package leetcode.medium

/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 *
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 * Constraints:
 * - 1 <= points.length <= 10^5
 * - points[i].length == 2
 * - -2^31 <= xstart < xend <= 2^31 - 1
 *
 * 시간 복잡도: O(n * logn)
 * 공간 복잡도: O(1)
 */
class `452` {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        var shots = 1
        points.sortWith(compareBy({ it[0] }, { it[1] }))
        var right = points[0][1]
        (1 until points.size).forEach { i ->
            val balloon = points[i]
            if (right < balloon[0]) {
                shots += 1
                right = balloon[1]
            } else {
                right = minOf(right, balloon[1])
            }
        }
        return shots
    }
}

fun main() {
    val solution = `452`()
    println(solution.findMinArrowShots(arrayOf(intArrayOf(10, 16), intArrayOf(2, 8), intArrayOf(1, 6), intArrayOf(7, 12)))) // 2
    println(solution.findMinArrowShots(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6), intArrayOf(7, 8)))) // 4
    println(solution.findMinArrowShots(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5)))) // 2
    println(solution.findMinArrowShots(arrayOf(intArrayOf(2, 4), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(4, 5)))) // 2
    println(solution.findMinArrowShots(arrayOf(intArrayOf(-2147483648,2147483647)))) // 1
    println(solution.findMinArrowShots(arrayOf(intArrayOf(-2147483648,0)))) // 1
    println(solution.findMinArrowShots(arrayOf(intArrayOf(0,2147483647)))) // 1
    println(solution.findMinArrowShots(arrayOf(intArrayOf(1, 1)))) // 1
    println(solution.findMinArrowShots(arrayOf(intArrayOf(1, 100), intArrayOf(4, 54), intArrayOf(2, 3), intArrayOf(62, 71)))) // 3
}
