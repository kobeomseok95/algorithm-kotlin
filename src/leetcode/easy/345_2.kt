package leetcode.easy

class `345_2` {
    fun reverseVowels(s: String): String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        var start = 0
        var end = s.length - 1
        var answer = s.toCharArray()
        while (start < end) {
            while (start < end && s[start] !in vowels) {
                start += 1
            }
            while (start < end && s[end] !in vowels) {
                end -= 1
            }
            val temp = answer[start]
            answer[start] = answer[end]
            answer[end] = temp

            start += 1
            end -= 1
        }
        return String(answer)
    }
}
