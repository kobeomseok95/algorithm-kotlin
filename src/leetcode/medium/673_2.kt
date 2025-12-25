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
 * dp로 접근하여 시간 / 공간 복잡도를 개선한다.
 * 시간 복잡도 : O(n^2)
 * 공간 복잡도 : O(n)
 */
class `673_2` {
    fun findNumberOfLIS(nums: IntArray): Int {
        val n = nums.size
        val lengthDp = IntArray(n) { 1 }
        val countDp = IntArray(n) { 1 }

        for (i in 0 until n) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    when {
                        // 이전 길이보다 크다면
                        lengthDp[j] + 1 > lengthDp[i] -> {
                            lengthDp[i] = lengthDp[j] + 1
                            countDp[i] = countDp[j]
                        }
                        // 이전 길이와 같다면
                        lengthDp[j] + 1 == lengthDp[i] -> {
                            countDp[i] += countDp[j]
                        }
                    }
                }
            }
        }

        val maxLength = lengthDp.max()
        return lengthDp.indices
            .filter { lengthDp[it] == maxLength }
            .sumOf { countDp[it] }
    }
}

fun main() {
    val solution = `673_2`()
    println(solution.findNumberOfLIS(intArrayOf(1,2,3)))        // length: 3 이 1개
    println(solution.findNumberOfLIS(intArrayOf(1,3,5,4,7)))    // length: 4 가 2개
    println(solution.findNumberOfLIS(intArrayOf(2,2,2,2,2)))    // length: 1 이 5개
    println(solution.findNumberOfLIS(intArrayOf(4,3,2,1,0)))    // length: 1 이 5개
    println(solution.findNumberOfLIS(intArrayOf(0,5,4,6,7)))    // length: 4 가 2개
    println(solution.findNumberOfLIS(intArrayOf(3,7,1,2,5)))    // length: 3 이 1개
    println(solution.findNumberOfLIS(intArrayOf(3,7,1,2)))      // length: 2 가 2개
    println(solution.findNumberOfLIS(intArrayOf(0,5,4)))        // length: 2 가 2개
}
