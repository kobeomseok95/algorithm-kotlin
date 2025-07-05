package leetcode.medium

class `38` {
    fun countAndSay(n: Int): String {
        return (1..n).fold("1") { acc, i ->
            if (i == 1) {
                acc
            } else {
                acc.getRunLengthEncoding()
            }
        }
    }

    private fun String.getRunLengthEncoding(): String {
        val stringBuilder = StringBuilder()
        var index = 0
        var count = 0
        while (index < this.length) {
            val current = this[index]
            while (index < this.length && this[index] == current) {
                index += 1
                count += 1
            }
            count.toString().forEach { stringBuilder.append(it) }
            stringBuilder.append(current)
            count = 0
        }
        return stringBuilder.toString()
    }
}
