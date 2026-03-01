package leetcode.medium

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Note that intervals which only touch at a point are non-overlapping.
 * For example, [1, 2] and [2, 3] are non-overlapping.
 *
 * Constraints:
 * - 1 <= intervals.length <= 10^5
 * - intervals[i].length == 2
 * - -5 * 10^4 <= starti < endi <= 5 * 10^4
 *
 * 시간 복잡도: O(n * log n)
 * 공간 복잡도: O(1)
 */
class `435` {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        var answer = 0
        intervals.sortWith(compareBy({ it[0] }, { it[1] }))
        var currentEnd = intervals[0][1]
        (1 until intervals.size).forEach { i ->
            if (currentEnd > intervals[i][0]) {
                answer += 1
                currentEnd = minOf(currentEnd, intervals[i][1])
            } else {
                currentEnd = intervals[i][1]
            }
        }
        return answer
    }
}

fun main() {
    val solution = `435`()
    println(solution.eraseOverlapIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(1, 3)))) // 1
    println(solution.eraseOverlapIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(1, 2), intArrayOf(1, 2)))) // 2
    println(solution.eraseOverlapIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3)))) // 0
    println(solution.eraseOverlapIntervals(arrayOf(intArrayOf(1, 100), intArrayOf(11, 22), intArrayOf(1, 11), intArrayOf(2, 12)))) // 2
    println(solution.eraseOverlapIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(-100, -2), intArrayOf(5, 7)))) // 0
    println(solution.eraseOverlapIntervals(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(3, 5)))) // 1
}
