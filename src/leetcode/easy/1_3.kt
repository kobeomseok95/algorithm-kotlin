package leetcode.easy

/**
 *  정렬 자체를 하지 않고 map 에 결과를 두어서 확인하는 방법
 *  map 설계
 *  - key = nums 배열 값에서 인덱스 별 찾아야할 값 (target - nums[i])
 *  - value = 해당 위치에서의 인덱스
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n)
 */
class `1_3` {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var left = 0
        var right = nums.lastIndex
        val map = mutableMapOf<Int, Int>()
        while (left <= right) {
            map[nums[left]]?.let { return intArrayOf(it, left) }
            if (nums[left] + nums[right] == target) {
                return intArrayOf(left, right)
            }
            map[nums[right]]?.let { return intArrayOf(it, right) }

            map[target - nums[left]] = left
            map[target - nums[right]] = right
            left += 1
            right -= 1
        }
        return intArrayOf(0, 0)
    }
}
