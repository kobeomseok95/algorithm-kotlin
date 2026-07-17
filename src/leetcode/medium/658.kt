package leetcode.medium

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 * - |a - x| < |b - x|, or
 * - |a - x| == |b - x| and a < b
 *
 * Constraints:
 * - 1 <= k <= arr.length
 * - 1 <= arr.length <= 10^4
 * - arr is sorted in ascending order.
 * - -10^4 <= arr[i], x <= 10^4
 *
 * 시간 복잡도: O(log (N - K) + K)
 * 공간 복잡도: O(1)
 */
class `658` {
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        var left = 0
        var right = arr.size - k

        while (left < right) {
            val mid = left + (right - left) / 2
            if (x - arr[mid] <= arr[mid + k] - x) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return (left until left + k).map { arr[it] }
    }
}

fun main() {
    val solution = `658`()
    println(solution.findClosestElements(intArrayOf(1, 2, 3, 4, 5), 2, 3)) // [1, 2, 3, 4]
    println(solution.findClosestElements(intArrayOf(0, 0, 1, 2, 3, 3, 4, 7, 7, 8), 3, 5)) // [3, 3, 4]
    println(solution.findClosestElements(intArrayOf(5, 6, 7), 2, 7)) // [6, 7]
    println(solution.findClosestElements(intArrayOf(1, 1, 1, 10, 10, 10), 1, 9)) // [10]
    println(solution.findClosestElements(intArrayOf(1, 1, 1, 10, 10, 10), 2, 9)) // [10, 10]
    println(solution.findClosestElements(intArrayOf(1), 1, 1)) // [1]
    println(solution.findClosestElements(intArrayOf(1, 2), 1, 1)) // [1]
    println(solution.findClosestElements(intArrayOf(1, 2, 3, 4, 5), 4, 3)) // [1, 2, 3, 4]
    println(solution.findClosestElements(intArrayOf(1, 1, 2, 3, 4, 5), 4, -1)) // [1, 1, 2, 3]
    println(solution.findClosestElements(intArrayOf(1, 1, 2, 3, 4, 5), 4, 6)) // [2, 3, 4, 5]
}
