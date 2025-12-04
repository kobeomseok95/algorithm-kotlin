package leetcode.easy

/**
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(n)
 *
 * intArray 를 미리 할당한다.
 *
 */
class `1313_2` {
    fun decompressRLElist(nums: IntArray): IntArray {
        val totalSize = (nums.indices step 2).sumOf { nums[it] }
        val answer = IntArray(totalSize)
        var index = 0
        (nums.indices step 2).forEach { i ->
            val freq = nums[i]
            val value = nums[i + 1]
            repeat(freq) { answer[index++] = value }
        }
        return answer
    }
}
