package leetcode.easy

class `605_2` {
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var remaining = n
        var i = 0
        while (i < flowerbed.size && remaining > 0) {
            if (
                flowerbed[i] == 0 &&
                flowerbed.getOrElse(i - 1) { 0 } != 1 &&
                flowerbed.getOrElse(i + 1) { 0 } != 1
            ) {
                i += 1
                remaining -= 1
            }
            i += 1
        }
        return remaining == 0
    }
}
