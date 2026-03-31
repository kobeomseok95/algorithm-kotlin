package leetcode.medium

import java.util.SortedSet
import java.util.TreeSet

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Constraints:
 * - 1 <= nums.length <= 6
 * - -10 <= nums[i] <= 10
 * - All the integers of nums are unique.
 *
 * 시간 복잡도: O(N * N!)
 * 공간 복잡도: O(N)
 */
class `46` {
    fun permute(nums: IntArray): List<List<Int>> {
        val permutations = mutableListOf<List<Int>>()
        permute(nums.toSet(), permutations)
        return permutations
    }

    private fun permute(
        nums: Set<Int>,
        permutations: MutableList<List<Int>>,
        currentPermutation: MutableList<Int> = mutableListOf(),
        used: BooleanArray = BooleanArray(nums.size),
    ) {
        if (currentPermutation.size >= nums.size) {
            permutations.add(currentPermutation.toList())
            return
        }

        nums.forEachIndexed { index, num ->
            if (!used[index]) {
                used[index] = true
                currentPermutation.add(num)
                permute(nums, permutations, currentPermutation, used)
                used[index] = false
                currentPermutation.removeLast()
            }
        }
    }
}

fun main() {
    val solution = `46`()
    println(solution.permute(intArrayOf(1))) // [[1]]
    println(solution.permute(intArrayOf(0, 1))) // [[0,1],[1,0]]
    println(solution.permute(intArrayOf(1, 2, 3))) // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
}
