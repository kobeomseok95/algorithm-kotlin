package leetcode.medium

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Constraints:
 * - 1 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti <= endi <= 10^4
 *
 * N: intervals.length
 * 시간 복잡도: O(N log N)
 * 공간 복잡도: O(N)
 */
class `56` {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }
        return intervals.fold(mutableListOf<IntArray>()) { acc, value ->
            val last = acc.removeLastOrNull() ?: value
            if (isOverlap(last, value)) {
                acc.add(
                    intArrayOf(
                        last[0],
                        maxOf(last[1], value[1]),
                    ),
                )
            } else {
                acc.add(last)
                acc.add(value)
            }
            acc
        }.toTypedArray()
    }

    private fun isOverlap(
        current: IntArray,
        rightInterval: IntArray,
    ): Boolean {
        return current[0] <= rightInterval[0] && rightInterval[0] <= current[1]
    }
}

fun main() {
    val solution = `56`()
    println(solution.merge(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3))).map { it.toList() }) // [[1,4]]
    println(solution.merge(arrayOf(intArrayOf(1, 3))).map { it.toList() }) // [[1,3]]
    println(solution.merge(arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))).map { it.toList() }) // [[1,6],[8,10],[15,18]]
    println(solution.merge(arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))).map { it.toList() }) // [[1,5]]
    println(solution.merge(arrayOf(intArrayOf(4, 7), intArrayOf(1, 4))).map { it.toList() }) // [[1,7]]
}
