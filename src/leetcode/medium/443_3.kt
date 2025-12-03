package leetcode.medium

/**
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n)
 *
 * 재귀 방식으로 풀어보기
 */
class `443_3` {
    fun compress(
        chars: CharArray,
        readIndex: Int = 0,
        writeIndex: Int = 0,
    ): Int {
        if (readIndex >= chars.size) {
            return writeIndex
        }

        val currentChar = chars[readIndex]
        var count = 0
        var nextReadIndex = readIndex
        while (nextReadIndex < chars.size && chars[nextReadIndex] == currentChar) {
            count += 1
            nextReadIndex += 1
        }
        var nextWriteIndex = writeIndex
        chars[nextWriteIndex] = currentChar
        nextWriteIndex += 1
        if (count > 1) {
            for (digit in count.toString()) {
                chars[nextWriteIndex] = digit
                nextWriteIndex += 1
            }
        }

        return compress(
            chars = chars,
            readIndex = nextReadIndex,
            writeIndex = nextWriteIndex,
        )
    }
}
