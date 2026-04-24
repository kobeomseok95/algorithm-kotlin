package leetcode.medium

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Constraints:
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 10
 * - -100 <= matrix[i][j] <= 100
 *
 * 시간 복잡도: O(I * J)
 * 공간 복잡도: O(1)
 */
class `54` {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var top = 0
        var bottom = matrix.size - 1
        var left = 0
        var right = matrix[0].size - 1
        val spiralOrderedPoints = mutableListOf<Int>()
        while (top <= bottom && left <= right) {
            for (i in left..right)
                spiralOrderedPoints.add(matrix[top][i])
            top++

            for (j in top..bottom)
                spiralOrderedPoints.add(matrix[j][right])
            right--

            if (top <= bottom) {
                for (k in right downTo left)
                    spiralOrderedPoints.add(matrix[bottom][k])
                bottom--
            }

            if (left <= right) {
                for (l in bottom downTo top)
                    spiralOrderedPoints.add(matrix[l][left])
                left++
            }
        }
        return spiralOrderedPoints
    }
}

fun main() {
    val solution = `54`()
    println(solution.spiralOrder(arrayOf(intArrayOf(1,2,3), intArrayOf(4,5,6), intArrayOf(7,8,9)))) // [1,2,3,6,9,8,7,4,5]
    println(solution.spiralOrder(arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6,7,8), intArrayOf(9,10,11,12)))) // [1,2,3,4,8,12,11,10,9,5,6,7]
}
