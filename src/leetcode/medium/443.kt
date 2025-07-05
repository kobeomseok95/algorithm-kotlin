package leetcode.medium

class `443` {
    fun compress(chars: CharArray): Int {
        var changedIndex = 0
        var i = 0
        var count = 0
        while (i < chars.size) {
            val current = chars[i]
            while (i < chars.size && current == chars[i]) {
                i += 1
                count += 1
            }
            chars[changedIndex++] = current
            if (count > 1) {
                for (c in count.toString()) {
                    chars[changedIndex++] = c
                }
            }
            count = 0
        }
        return changedIndex
    }
}
