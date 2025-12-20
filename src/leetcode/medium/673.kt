package leetcode.medium

/**
 * Given an integer array nums, return the number of longest increasing subsequences.
 *
 * Notice that the sequence has to be strictly increasing.
 *
 * Example 1:
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 *
 * Constraints:
 * 1 <= nums.length <= 2000
 * -10^6 <= nums[i] <= 10^6
 * The answer is guaranteed to fit inside a 32-bit integer.
 *
 * Longest Increasing Subsequence: 원소가 n개인 배열의 일부 원소를 골라내서 만든 부분 수열 중, 각 원소가 이전 보다 크다는 조건을 만족하고 그 길이가 최대인 부분 수열
 *
 * 접근법: Brute Force 로 접근
 *
 * 시간 복잡도 : O(2^n * n)
 * 공간 복잡도 : O(2^n * n)
 */
class `673` {
    fun findNumberOfLIS(nums: IntArray): Int {
        val subsequences = mutableListOf<List<Int>>().apply { setAllSubsequences(nums) }
        val lis = subsequences.filter { it.isIncreasing() }
        val maxLength = lis.maxOf { it.size }
        return lis.count { it.size == maxLength }
    }

    private fun MutableList<List<Int>>.setAllSubsequences(
        nums: IntArray,
        index: Int = 0,
        current: MutableList<Int> = mutableListOf(),
    ) {
        if (index == nums.size) {
            this.add(current.toList())
            return
        }
        setAllSubsequences(nums, index + 1, current)
        current.add(nums[index])
        setAllSubsequences(nums, index + 1, current)
        current.removeLast()
    }

    private fun List<Int>.isIncreasing(): Boolean {
        (0 until size - 1).forEach { i ->
            if (this[i] >= this[i + 1]) {
                return false
            }
        }
        return true
    }
}
