package leetcode.easy

/**
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(n)
 *
 * 규칙
 *  - i = 0, 1, 2,...
 *  - nums[2*i], nums[2*i+1] = freq, val
 *  - val 값을 freq 만큼 배열에 추가해야한다.
 */
class `1313` {
    fun decompressRLElist(nums: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        (nums.indices step 2).forEach { i->
            val freq = nums[i]
            val value = nums[i+1]
            answer.addAll(List(freq) { value })
        }
        return answer.toIntArray()
    }
}
