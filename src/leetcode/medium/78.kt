package leetcode.medium

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * * 1 <= nums.length <= 10
 * * -10 <= nums[i] <= 10
 * * All the numbers of nums are unique.
 *
 * subset의 의미: 순서에 상관 없는 부분집합 ([1,2]와 [2,1]은 같은 Subset)
 *
 * 백트래킹 + 재귀 방식으로 접근한다.
 * 따라서 백트래킹 알고리즘으로 중복이 발생하는 경우를 제외하여 만족하는 subset 인 경우만 추가적으로 탐색하도록 접근한다.
 *
 * 시간 복잡도 : O(n * 2^n) -> current 배열 toList() 비용 최대 n * 모든 subset 갯수 2^n
 * 공간 복잡도 : O(n * 2^n) -> current 배열 toList() 비용 최대 n * 모든 subset 갯수 2^n
 */
class `78` {
    fun subsets(nums: IntArray): List<List<Int>> {
        val answer = mutableListOf<List<Int>>()
        backtrack(nums, answer)
        return answer
    }

    private fun backtrack(
        nums: IntArray,
        answer: MutableList<List<Int>>,
        current: MutableList<Int> = mutableListOf(),
        index: Int = 0,
    ) {
        answer.add(current.toList())

        if (index > nums.lastIndex) {
            return
        }

        (index..nums.lastIndex).forEach { i ->
            current.add(nums[i])
            backtrack(nums, answer, current, i + 1)
            current.removeLast()
        }
    }
}

fun main() {
    val solution = `78`()
    println(solution.subsets(intArrayOf(1, 2, 3)))
    println(solution.subsets(intArrayOf(0)))
}
