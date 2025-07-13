package leetcode.medium

class `11` {
    fun maxArea(height: IntArray): Int {
        val n = height.size
        var low = 0
        var high = n - 1
        var amount = 0

        while (low < high) {
            val lowerHeight = height[low].coerceAtMost(height[high])
            amount = amount.coerceAtLeast((high - low) * lowerHeight)

            while (low < high && lowerHeight >= height[low]) {
                low += 1
            }
            while (low < high && lowerHeight >= height[high]) {
                high -= 1
            }
        }
        return amount
    }
}
