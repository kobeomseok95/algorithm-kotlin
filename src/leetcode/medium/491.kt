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
 * nums[i] 값이 있을 때, 없을 때 케이스를 만들어서 재귀 탐색을 진행한다.
 * 현재 배열을 담는 current 에 마지막 값보다 탐색 중 현재 인덱스에 해당하는 배열 값이 같거나 크다면 조건이 성립하므로 current 에 추가 및 제거한다.
 *
 * 시간 복잡도 : O(2^n)
 * 공간 복잡도 : O(n^2)
 */
class `491` {
    fun findSubsequences(nums: IntArray): List<List<Int>> {
        val answer = mutableListOf<List<Int>>()
        getSubsequences(nums, answer, mutableListOf())
        return answer.toList()
    }

    private fun getSubsequences(
        nums: IntArray,
        answer: MutableList<List<Int>>,
        current: MutableList<Int>,
        currentIndex: Int = 0,
    ) {
        if (currentIndex >= nums.size) {
            if (current.size >= MIN_SIZE) {
                answer.add(current.toList())
            }
            return
        }
        getSubsequences(nums, answer, current, currentIndex + 1)
        if ((current.lastOrNull() ?: DEFAULT_MIN_VALUE) <= nums[currentIndex]) {
            current.add(nums[currentIndex])
            getSubsequences(nums, answer, current, currentIndex + 1)
            current.removeLast()
        }
    }

    companion object {
        private const val MIN_SIZE = 2
        private const val DEFAULT_MIN_VALUE = -101
    }
}

fun main() {
    val solution = `491`()
    println(solution.findSubsequences(intArrayOf(4, 6, 7, 7))) // [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
    println(solution.findSubsequences(intArrayOf(4, 4, 3, 2, 1))) // [[4,4]]
    println(solution.findSubsequences(intArrayOf(-100,-99,-98,-97,-96,-96))) // [[-100,-99],[-100,-99,-98],[-100,-99,-98,-97],[-100,-99,-98,-97,-96],[-100,-99,-98,-97,-96,-96],[-100,-99,-98,-96],[-100,-99,-98,-96,-96],[-100,-99,-97],[-100,-99,-97,-96],[-100,-99,-97,-96,-96],[-100,-99,-96],[-100,-99,-96,-96],[-100,-98],[-100,-98,-97],[-100,-98,-97,-96],[-100,-98,-97,-96,-96],[-100,-98,-96],[-100,-98,-96,-96],[-100,-97],[-100,-97,-96],[-100,-97,-96,-96],[-100,-96],[-100,-96,-96],[-99,-98],[-99,-98,-97],[-99,-98,-97,-96],[-99,-98,-97,-96,-96],[-99,-98,-96],[-99,-98,-96,-96],[-99,-97],[-99,-97,-96],[-99,-97,-96,-96],[-99,-96],[-99,-96,-96],[-98,-97],[-98,-97,-96],[-98,-97,-96,-96],[-98,-96],[-98,-96,-96],[-97,-96],[-97,-96,-96],[-96,-96]]
}
