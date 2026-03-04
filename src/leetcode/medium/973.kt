package leetcode.medium

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
 * return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)² + (y1 - y2)²).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 * Constraints:
 * - 1 <= k <= points.length <= 10^4
 * - -10^4 <= xi, yi <= 10^4
 *
 * 시간 복잡도: O(n * log n)
 * 공간 복잡도: O(k)
 */
class `973` {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        points.sortBy { getDistance(it) }
        return points.take(k).toTypedArray()
    }

    private fun getDistance(point: IntArray): Int {
        return point.first() * point.first() + point.last() * point.last()
    }
}

fun main() {
    val solution = `973`()
    println(solution.kClosest(arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2)), 1).contentDeepToString()) // [[-2,2]]
    println(solution.kClosest(arrayOf(intArrayOf(3, 3), intArrayOf(5, -1), intArrayOf(-2, 4)), 2).contentDeepToString()) // [[3,3],[-2,4]]
}
