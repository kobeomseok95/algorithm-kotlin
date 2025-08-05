package leetcode.medium

/**
 *  시간 복잡도 : O(n log n) + O(n^2) = O(n^2)
 *  공간 복잡도 : O(n)
 *
 *  중복 스킵 최적화
 */
class `15` {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val answer = mutableListOf<List<Int>>()
        nums.sort()
        var i = 0
        while (i < nums.size - 2) {
            val first = nums[i]
            var j = i + 1
            var k = nums.lastIndex
            while (j < k) {
                val second = nums[j]
                val third = nums[k]
                val sum = first + second + third
                if (sum == 0) {
                    answer.add(listOf(first, second, third))
                    while (j < k && second >= nums[j]) {
                        j += 1
                    }
                } else if (sum < 0) {
                    while (j < k && second >= nums[j]) {
                        j += 1
                    }
                } else {
                    while (j < k && third <= nums[k]) {
                        k -= 1
                    }
                }
            }

            while (i < nums.size - 2 && first >= nums[i]) {
                i += 1
            }
        }
        return answer.toList()
    }
}
