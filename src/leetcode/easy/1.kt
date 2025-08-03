package leetcode.easy

/**
 *  정렬 후 target 에 맞는 인덱스를 찾아서 리턴
 *  시간 복잡도 : O(n2)
 *  공간 복잡도 : O(n)
 */
class `1` {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val sortedNums = nums.mapIndexed { index, i -> Pair(num = i, index = index) }
            .sortedBy { it.num }
        (0 until sortedNums.lastIndex).forEach { i ->
            (i + 1 until sortedNums.size).forEach { j ->
                if (sortedNums[i].num + sortedNums[j].num == target) {
                    return intArrayOf(sortedNums[i].index, sortedNums[j].index)
                }
            }
        }
        return intArrayOf(0, 0)
    }

    data class Pair(val num: Int, val index: Int)
}
