package leetcode.easy

class `344` {
    fun reverseString(s: CharArray): CharArray {
        (0 until s.size / 2).forEach { i ->
            val temp = s[i]
            s[i] = s[s.size - 1 - i]
            s[s.size - 1 - i] = temp
        }

        return s
    }
}
