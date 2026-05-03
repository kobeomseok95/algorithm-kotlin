package leetcode.medium

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Constraints:
 * - m == board.length
 * - n = board[i].length
 * - 1 <= m, n <= 6
 * - 1 <= word.length <= 15
 * - board and word consists of only lowercase and uppercase English letters.
 *
 * L: word.length
 * 시간 복잡도: O((m * n * L) + (m * n * 4^L))
 * 공간 복잡도: O((m * n) + L)
 */
class `79` {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (!canContain(board, word)) {
            return false
        }
        val visits = Array(board.size) { BooleanArray(board[0].size) }
        val starts = board.getStarts(word[0])
        starts.forEach { point ->
            visits[point.first][point.second] = true
            if (backtrack(board, visits, word, point)) {
                return true
            }
            visits[point.first][point.second] = false
        }
        return false
    }

    private fun canContain(board: Array<CharArray>, word: String): Boolean {
        if (board.size * board[0].size < word.length) {
            return false
        }
        val charMap = mutableMapOf<Char, Int>()
        board.forEach { column ->
            column.forEach { char ->
                charMap[char] = charMap.getOrPut(char) { 0 } + 1
            }
        }
        word.forEach { char ->
            val count = charMap[char] ?: return false
            if (count < 0) {
                return false
            }
            charMap[char] = count - 1
        }
        return true
    }

    private fun Array<CharArray>.getStarts(word: Char): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        (0 until this.size).forEach { i ->
            (0 until this[i].size).forEach { j ->
                if (this[i][j] == word) {
                    list.add(i to j)
                }
            }
        }
        return list
    }

    private fun backtrack(
        board: Array<CharArray>,
        visits: Array<BooleanArray>,
        word: String,
        point: Pair<Int, Int>,
        index: Int = 1,
    ): Boolean {
        if (index >= word.length) {
            return true
        }

        val nextPoints = DIRECTIONS.mapNotNull { dir ->
            (dir + point).takeIf { next ->
                board.isInner(next) &&
                    !visits[next.first][next.second] &&
                    board[next.first][next.second] == word[index]
            }
        }

        return nextPoints.any { nextPoint ->
            visits[nextPoint.first][nextPoint.second] = true
            backtrack(board, visits, word, nextPoint, index + 1).also {
                visits[nextPoint.first][nextPoint.second] = false
            }
        }
    }

    private fun Array<CharArray>.isInner(point: Pair<Int, Int>): Boolean {
        return point.first in (0 until this.size) && point.second in (0 until this[0].size)
    }

    private operator fun Pair<Int, Int>.plus(that: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(this.first + that.first, this.second + that.second)
    }

    companion object {
        private val DIRECTIONS = setOf(
            -1 to 0,
            1 to 0,
            0 to 1,
            0 to -1,
        )
    }
}

fun main() {
    val solution = `79`()
    println(solution.exist(arrayOf(charArrayOf('a','a')), "aa")) // true
    println(solution.exist(arrayOf(charArrayOf('A','B','C','E'), charArrayOf('S','F','C','S'), charArrayOf('A','D','E','E')), "ABCCED")) // true
    println(solution.exist(arrayOf(charArrayOf('A','B','C','E'), charArrayOf('S','F','C','S'), charArrayOf('A','D','E','E')), "SEE")) // true
    println(solution.exist(arrayOf(charArrayOf('A','B','C','E'), charArrayOf('S','F','C','S'), charArrayOf('A','D','E','E')), "ABCB")) // false
}
