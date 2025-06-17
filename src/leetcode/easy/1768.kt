package leetcode.easy

class `1768` {
    fun mergeAlternately(word1: String, word2: String): String {
        val maxLength = maxOf(word1.length, word2.length)
        val stringBuilder = StringBuilder()
        (0 until maxLength).forEach { i ->
            word1.getOrNull(i)?.let { stringBuilder.append(it) }
            word2.getOrNull(i)?.let { stringBuilder.append(it) }
        }
        return stringBuilder.toString()
    }
}
