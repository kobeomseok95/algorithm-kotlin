package leetcode.easy

class `605` {
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var remaining = n
        val newFlowerBed = BooleanArray(flowerbed.size + 2) {
            if (it == 0 || it == flowerbed.size + 1) {
                false
            } else {
                flowerbed[it - 1] == 1
            }
        }

        (1..flowerbed.size).forEach { i ->
            if (
                newFlowerBed.canPlace(i - 1, i, i + 1)
            ) {
                newFlowerBed.place(i)
                remaining -= 1
            }
            if (remaining <= 0) {
                return true
            }
        }
        return remaining <= 0
    }

    private fun BooleanArray.canPlace(
        left: Int,
        here: Int,
        right: Int,
    ): Boolean {
        return !this[left] && !this[here] && !this[right]
    }

    private fun BooleanArray.place(here: Int) {
        this[here] = true
    }
}
