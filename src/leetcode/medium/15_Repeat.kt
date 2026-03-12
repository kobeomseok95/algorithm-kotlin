package leetcode.medium

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Constraints:
 * - 3 <= nums.length <= 3000
 * - -10^5 <= nums[i] <= 10^5
 *
 * 시간 복잡도: O(n^2)
 * 공간 복잡도: O(1)
 */
class `15_Repeat` {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<List<Int>>()
        var previousNumber = -100_001
        val endInclusive = nums.lastIndex
        (0..endInclusive).forEach { i ->
            if (previousNumber == nums[i]) {
                return@forEach
            }
            previousNumber = nums[i]

            var j = i + 1
            var k = endInclusive
            while (j < k) {
                when {
                    nums[i] + nums[j] + nums[k] < 0 -> {
                        j += 1
                    }
                    nums[i] + nums[j] + nums[k] > 0 -> {
                        k -= 1
                    }
                    nums[i] + nums[j] + nums[k] == 0 -> {
                        result.add(listOf(nums[i], nums[j], nums[k]))
                        while (j < k && nums[j] == nums[j + 1]) {
                            j += 1
                        }
                        while (j < k && nums[k - 1] == nums[k]) {
                            k -= 1
                        }
                        j += 1
                        k -= 1
                    }
                }
            }
        }

        return result
    }
}

fun main() {
    val solution = `15_Repeat`()
    println(solution.threeSum(intArrayOf(-1, 0, 1, 2, -1, -4))) // [[-1,-1,2],[-1,0,1]]
    println(solution.threeSum(intArrayOf(0, 1, 1))) // []
    println(solution.threeSum(intArrayOf(0, 0, 0))) // [[0,0,0]]
    println(solution.threeSum(intArrayOf(1,2,0,1,0,0,0,0))) // [[0,0,0]]
}
