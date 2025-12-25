package leetcode.medium

/**
 * Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 *
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * All the integers in nums are unique.
 *
 * 시간 복잡도 : O(n^2)
 * 공간 복잡도 : O(n)
 */
class `368` {
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        val n = nums.size
        val counts = IntArray(n) { 1 }
        val parentIndices = IntArray(n) { -1 }
        nums.sort()
        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[i].isDivisibleBy(nums[j])) {
                    when {
                        counts[j] + 1 > counts[i] -> {
                            counts[i] = counts[j] + 1
                            parentIndices[i] = j
                        }
                    }
                }
            }
        }
        var index = counts.indices.maxBy { counts[it] }
        val answer = mutableListOf<Int>()
        while (index != -1) {
            answer.add(nums[index])
            index = parentIndices[index]
        }
        return answer
    }

    private fun Int.isDivisibleBy(other: Int): Boolean {
        return this % other == 0
    }
}

fun main() {
    val solution = `368`()
    println(solution.largestDivisibleSubset(intArrayOf(1,2,3)))         // [1,2]
    println(solution.largestDivisibleSubset(intArrayOf(1,2,4,8)))       // [1,2,4,8]
    println(solution.largestDivisibleSubset(intArrayOf(1,2,3,4,5)))     // [1,2,4]
    println(solution.largestDivisibleSubset(intArrayOf(3,4,16,8)))      // [4,8,16]
    println(solution.largestDivisibleSubset(intArrayOf(27,8,3,1)))      // [1,3,27]
}
