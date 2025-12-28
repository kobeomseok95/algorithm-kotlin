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
 * 순수 DFS 방식으로 접근
 * 1. DFS 구간에서 답변에 current 를 모두 넣게되면 중복이 발생한다.
 * 2. 문제 조건은 겹치지 않은 subset 이다.
 * 3. answer 에 배열을 넣을 때는 DFS 탐색 시 index 에 해당하는 nums[index] 값을 추가한 것만 집어넣는다.
 * 4. nums[index] 값을 추가하지 않은 배열로도 index + 1 하여 탐색하는 이유는 중복되지 않은 subset을 구하기 위함이다.
 *  -> e.g. [1,2,3] 일때 index == 1 인 경우 [2] 라는 subset 을 탐색해야하기 때문이다.
 * 5. 최종적으로 탐색이 완료된 current subset 만 answer 에 추가한다.
 *
 * 시간 복잡도 : O(n * 2^n) -> current 배열의 plus() 비용 + 2^n 탐색 비용
 * 공간 복잡도 : O(n * 2^n) -> current 배열의 plus() 비용 + 2^n 탐색 비용
 */
class `78_2` {
    fun subsets(nums: IntArray): List<List<Int>> {
        val answer = mutableListOf<List<Int>>()
        dfs(nums, answer)
        return answer
    }

    private fun dfs(
        nums: IntArray,
        answer: MutableList<List<Int>>,
        subset: List<Int> = listOf(),
        index: Int = 0,
    ) {
        if (index > nums.lastIndex) {
            answer.add(subset)
            return
        }
        dfs(nums, answer, subset, index + 1)
        dfs(nums, answer, subset.plus(nums[index]), index + 1)
    }
}

fun main() {
    val solution = `78_2`()
    println(solution.subsets(intArrayOf(1, 2, 3)))
    println(solution.subsets(intArrayOf(0)))
}
