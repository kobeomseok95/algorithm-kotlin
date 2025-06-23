package leetcode.easy

class `345` {
    fun reverseVowels(s: String): String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        val stringBuilder = StringBuilder()
        var vowelsIndex = s.length - 1
        (0 until s.length).forEach { index ->
            if (s[index] !in vowels) {
                stringBuilder.append(s[index])
            } else {
                while (vowelsIndex >= 0 && s[vowelsIndex] !in vowels) {
                    vowelsIndex -= 1
                }
                s.getOrNull(vowelsIndex)?.let {
                    stringBuilder.append(it)
                    vowelsIndex -= 1
                }
            }
        }
        return stringBuilder.toString()
    }
}
