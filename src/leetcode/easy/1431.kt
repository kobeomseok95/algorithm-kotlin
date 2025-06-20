package leetcode.easy

class `1431` {
    fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
        val max = candies.max()
        return candies.map { candyCount -> candyCount + extraCandies >= max }
    }
}
