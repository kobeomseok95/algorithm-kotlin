package leetcode.medium

/**
 * Given an integer array nums,
 * return all the different possible non-decreasing subsequences of the given array with at least two elements.
 * You may return the answer in any order.
 *
 * Constraints:
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 *
 * 백트래킹 + 재귀 활용
 *
 * 시간 복잡도 : O(n * 2^n) -> 백트래킹 2^n 비용과 toList() 비용
 * 공간 복잡도 : O(n^2)
 */
class `491_2` {
    fun findSubsequences(nums: IntArray): List<List<Int>> {
        val answer = mutableListOf<List<Int>>()
        backtrack(nums, answer)
        return answer
    }

    private fun backtrack(
        nums: IntArray,
        answer: MutableList<List<Int>>,
        current: MutableList<Int> = mutableListOf(),
        trackingStartIndex: Int = 0,
    ) {
        // 백트래킹 결과인 current 의 사이즈가 2 이상인 경우만 정답 배열에 넣는다.
        if (current.size >= MIN_SIZE) {
            // current 를 그대로 넣으면 참조가 넣어지기 때문에 값이 오염될 수 있어 toList() 로 값만 복사하여 answer에 넣어준다.
            // 시간복잡도 n 크기 만큼 발생
            answer.add(current.toList())
        }

        // 중복된 값 저장
        // return all the different possible non-decreasing subsequences 조건 만족을 위해 재귀 레벨에 중복된 값을 관리한다.
        val usedValues = mutableSetOf<Int>()
        (trackingStartIndex until nums.size).forEach { i ->
            // 중복된 값이 있거나, 백트래킹 결과의 마지막 값이 nums[i] 보다 크면 스킵
            if (nums[i] in usedValues || (current.isNotEmpty() && current.last() > nums[i])) {
                return@forEach
            }

            usedValues.add(nums[i])
            current.add(nums[i])
            backtrack(nums, answer, current, i + 1)
            current.removeLast()
        }
    }

    companion object {
        private const val MIN_SIZE = 2
    }
}

fun main() {
    val solution = `491_2`()
    println(solution.findSubsequences(intArrayOf(1,2,3,4)))
    println(solution.findSubsequences(intArrayOf(4,6,7,7)))
    println(solution.findSubsequences(intArrayOf(4,4,3,2,1)))
}
