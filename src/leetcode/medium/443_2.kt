package leetcode.medium

/**
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(1) (문제에서 언급: You must write an algorithm that uses only constant extra space.)
 *
 * two pointer 방식으로 접근
 */
class `443_2` {
    fun compress(chars: CharArray): Int {
        var index = 0
        var left = 0
        var right = 0
        while (right < chars.size) {
            while (right < chars.size && chars[left] == chars[right]) {
                right += 1
            }
            val count = right - left
            if (count == 1) {
                chars[index] = chars[left]
                left = right
                index += 1
                continue
            }
            val str = chars[left] + count.toString()
            for (s in str) {
                chars[index] = s
                index += 1
            }
            left = right
        }
        return index.also { println(chars) }
    }
}
