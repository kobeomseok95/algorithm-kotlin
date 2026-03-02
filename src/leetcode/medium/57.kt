package leetcode.medium

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 *
 * Constraints:
 * - 0 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti <= endi <= 10^5
 * - intervals is sorted by starti in ascending order.
 * - newInterval.length == 2
 * - 0 <= start <= end <= 10^5
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n)
 */
class `57` {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val list = mutableListOf<IntArray>()
        var index = 0
        while (index < intervals.size && intervals[index].last() < newInterval.first()) {
            list.add(intervals[index])
            index++
        }

        var temp = newInterval
        while (index < intervals.size && temp.isOverlap(intervals[index])) {
            temp = intArrayOf(
                minOf(temp.first(), intervals[index].first()),
                maxOf(temp.last(), intervals[index].last()),
            )
            index++
        }
        list.add(temp)

        while (index < intervals.size) {
            list.add(intervals[index])
            index++
        }
        return list.toTypedArray()
    }

    private fun IntArray.isOverlap(interval: IntArray): Boolean {
        return this.first() <= interval.last() && this.last() >= interval.first()
    }
}

fun main() {
    val solution = `57`()
    // [[1,5],[6,9]]
    println(solution.insert(arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)), intArrayOf(2, 5)).map { it.toList() }) // [[1,5],[6,9]]
    // [[1,2],[3,10],[12,16]]
    println(solution.insert(arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7), intArrayOf(8, 10), intArrayOf(12, 16)), intArrayOf(4, 8)).map { it.toList() })
    // [[1,7],[8,9],[20, 25]]
    println(solution.insert(arrayOf(intArrayOf(1, 3), intArrayOf(4, 5), intArrayOf(6, 7), intArrayOf(8, 9), intArrayOf(20, 25)), intArrayOf(1, 6)).map { it.toList() })
    // [[5,7]]
    println(solution.insert(arrayOf(), intArrayOf(5, 7)).map { it.toList() })
    // [[1,5], [6,8]]
    println(solution.insert(arrayOf(intArrayOf(1, 5)), intArrayOf(6, 8)).map { it.toList() })
    // [[1,5], [6,8], [100, 200]]
    println(solution.insert(arrayOf(intArrayOf(1, 5), intArrayOf(100, 200)), intArrayOf(6, 8)).map { it.toList() })
    // [[1,5]]
    println(solution.insert(arrayOf(intArrayOf(1, 5)), intArrayOf(2, 3)).map { it.toList() })
}
