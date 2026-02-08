package leetcode.medium

/**
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Constraints:
 * - 1 <= nums.length <= 200
 * - 1 <= nums[i] <= 100
 *
 * 시간 복잡도: O(n * t) -> n: 배열 길이, t: target 최대값
 * 공간 복잡도: O(t) -> t: target 최대값
 */
class `416` {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) {
            return false
        }
        val goalNumber = sum / 2
        val possibleSums = mutableSetOf<Int>().apply { add(0) }
        nums.forEach { num ->
            val currentPossibleSums = mutableSetOf<Int>()
            possibleSums.forEach { possibleSum ->
                currentPossibleSums.add(possibleSum + num)
            }
            possibleSums.addAll(currentPossibleSums)
            if (goalNumber in possibleSums) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val solution = `416`()
    println(solution.canPartition(intArrayOf(1,5,11,5))) // true
    println(solution.canPartition(intArrayOf(1,2,3,5))) // false
    println(solution.canPartition(intArrayOf(1,5,6,5))) // false
    println(solution.canPartition(intArrayOf(1,7,3,5))) // true
    println(solution.canPartition(intArrayOf(1,7,2,3,6,1))) // true
}
