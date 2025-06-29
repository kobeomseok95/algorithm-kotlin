package leetcode.medium

/**
 * 시간 복잡도 : O(n * log n)
 * 공간 복잡도 : O(n)
 */
class `334_3` {
    fun increasingTriplet(nums: IntArray): Boolean {
        val lis = mutableListOf<Int>()
        for (num in nums) {
            val index = lis.binarySearch(num)
                .let {
                    if (it < 0) {
                        -it - 1
                    } else {
                        it
                    }
                }
            if (index == lis.size) {
                lis.add(num)
            } else {
                lis[index] = num
            }
            if (lis.size >= 3) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val solution = `334_3`()
    println(solution.increasingTriplet(intArrayOf(2, 1, 5, 0, 4, 6))) // true
}
