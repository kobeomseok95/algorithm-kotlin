package leetcode.medium

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 * Constraints:
 * - 1 <= candidates.length <= 30
 * - 2 <= candidates[i] <= 40
 * - All elements of candidates are distinct.
 * - 1 <= target <= 40
 *
 * N: candidates.length, T: target, C: candidates[i]
 * 시간 복잡도: O(N^(T/C))
 * 공간 복잡도: O(T/C)
 */
class `39` {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val combinations = mutableListOf<List<Int>>()
        getCombination(candidates, target, combinations)
        return combinations
    }

    private fun getCombination(
        candidates: IntArray,
        target: Int,
        combinations: MutableList<List<Int>>,
        currentCombination: MutableList<Int> = mutableListOf(),
        index: Int = 0,
        currentSum: Int = 0,
    ) {
        if (currentSum == target) {
            combinations.add(currentCombination.toList())
            return
        } else if (currentSum > target) {
            return
        }

        (index..candidates.lastIndex).forEach { i ->
            currentCombination.add(candidates[i])
            getCombination(candidates, target, combinations, currentCombination, i, currentSum + candidates[i])
            currentCombination.removeLast()
        }
    }
}

fun main() {
    val solution = `39`()
    println(solution.combinationSum(intArrayOf(2, 3, 6, 7), 7)) // [[2,2,3],[7]]
    println(solution.combinationSum(intArrayOf(2, 3, 5), 8)) // [[2,2,2,2],[2,3,3],[3,5]]
    println(solution.combinationSum(intArrayOf(2), 1)) // []
    println(solution.combinationSum(intArrayOf(5, 6), 1)) // []
    println(solution.combinationSum(intArrayOf(2, 3), 1)) // []
}
