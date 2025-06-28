package leetcode.easy

class `541` {
    fun reverseStr(s: String, k: Int): String {
        val answer = s.toCharArray()
        val step = 2 * k
        for (i in s.indices step step) {
            val endInclusive = minOf((i + k - 1), s.lastIndex)
            var left = i
            var right = endInclusive
            while (left < right) {
                val temp = answer[left]
                answer[left] = answer[right]
                left += 1
                answer[right] = temp
                right -= 1
            }
        }
        return String(answer)
    }
}
