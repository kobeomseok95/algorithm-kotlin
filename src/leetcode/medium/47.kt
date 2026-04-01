package leetcode.medium

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * Constraints:
 * - 1 <= nums.length <= 8
 * - -10 <= nums[i] <= 10
 *
 * 시간 복잡도: O(N * N!)
 * 공간 복잡도: O()
 */
class `47` {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val permutations = mutableListOf<List<Int>>()
        nums.getPermutations(permutations)
        return permutations
    }

    private fun IntArray.getPermutations(
        permutations: MutableList<List<Int>>,
        usedIndex: BooleanArray = BooleanArray(this.size),
        currentPermutation: MutableList<Int> = mutableListOf(),
    ) {
        if (currentPermutation.size >= this.size) {
            permutations.add(currentPermutation.toList())
            return
        }

        val usedValues = mutableSetOf<Int>()
        this.forEachIndexed { index, value ->
            if (usedIndex[index]) {
                return@forEachIndexed
            }
            if (usedValues.contains(value)) {
                return@forEachIndexed
            }

            usedIndex[index] = true
            usedValues.add(value)
            currentPermutation.add(value)
            this.getPermutations(
                permutations,
                usedIndex,
                currentPermutation,
            )
            currentPermutation.removeLast()
            usedIndex[index] = false
        }
    }
}

fun main() {
    val solution = `47`()
    println(solution.permuteUnique(intArrayOf(1, 1, 2))) // [[1,1,2],[1,2,1],[2,1,1]]
    println(solution.permuteUnique(intArrayOf(1, 2, 3))) // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
}
