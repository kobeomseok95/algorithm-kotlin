package leetcode.easy

/**
 *  정렬하게 되면 대소비교가 가능하여 합이 target 보다 크고 작음에 따라 포인터를 이동하는 방법
 *  시간 복잡도 : O(n log n)
 *  공간 복잡도 : O(n)
 */
class `1_2` {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val sortedNums = nums.mapIndexed { index, i -> Pair(num = i, index = index) }
            .sortedBy { it.num }
        var left = 0
        var right = sortedNums.lastIndex
        while (left < right) {
            val sum = sortedNums[left].num + sortedNums[right].num
            if (sum > target) {
                right -= 1
            } else if (sum < target) {
                left += 1
            } else {
                return intArrayOf(sortedNums[left].index, sortedNums[right].index)
            }
        }
        return intArrayOf(0, 0)
    }

    data class Pair(val num: Int, val index: Int)
}
