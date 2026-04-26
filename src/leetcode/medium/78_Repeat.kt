package leetcode.medium

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * - 1 <= nums.length <= 10
 * - -10 <= nums[i] <= 10
 * - All the numbers of nums are unique.
 *
 * 시간 복잡도: O(n * 2^n)
 * 공간 복잡도: O(n * 2^n)
 */
class `78_Repeat` {
    fun subsets(nums: IntArray): List<List<Int>> {
        val answer = mutableListOf<List<Int>>()
        dfs(nums, answer)
        return answer
    }

    private fun dfs(
        nums: IntArray,
        answer: MutableList<List<Int>>,
        currentList: MutableList<Int> = mutableListOf(),
        index: Int = 0,
    ) {
        if (index >= nums.size) {
            answer.add(currentList.toList())
            return
        }
        dfs(nums, answer, currentList, index + 1)
        currentList.add(nums[index])
        dfs(nums, answer, currentList, index + 1)
        currentList.removeLast()
    }
}

fun main() {
    val solution = `78_Repeat`()
    println(solution.subsets(intArrayOf(1, 2, 3))) // [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    println(solution.subsets(intArrayOf(0))) // [[],[0]]
}
