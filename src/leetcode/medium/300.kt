package leetcode.medium

class `300` {
    fun lengthOfLIS(nums: IntArray): Int {
        val list = mutableListOf<Int>()
        for (num in nums) {
            val index = list.binarySearch(num).let {
                if (it < 0) {
                    -it - 1
                } else {
                    it
                }
            }
            if (list.size == index) {
                list.add(num)
            } else {
                list[index] = num
            }
        }
        return list.size
    }
}
