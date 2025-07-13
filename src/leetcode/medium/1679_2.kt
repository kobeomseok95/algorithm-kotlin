package leetcode.medium

class `1679_2` {
    fun maxOperations(nums: IntArray, k: Int): Int {
        val numsMap = mutableMapOf<Int, Int>()
        var count = 0
        nums.indices.forEach { i ->
            val complement = k - nums[i]
            if (numsMap.containsKey(complement)) {
                count += 1
                if (numsMap[complement] == 1) {
                    numsMap.remove(complement)
                } else {
                    numsMap[complement] = numsMap[complement]!! - 1
                }
            } else {
                numsMap[nums[i]] = numsMap.getOrDefault(nums[i], 0) + 1
            }
        }
        return count
    }
}
