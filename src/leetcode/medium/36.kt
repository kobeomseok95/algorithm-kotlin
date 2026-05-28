package leetcode.medium

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 *
 * Constraints:
 * - board.length == 9
 * - board[i].length == 9
 * - board[i][j] is a digit 1-9 or '.'.
 *
 * N: board.length
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(3N^2)
 */
class `36` {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val set = mutableSetOf<String>()

        (0 until 9).forEach { i ->
            (0 until 9).forEach { j ->
                val number = board[i][j]
                if (number != '.') {
                    val row = "row $i - number $number"
                    val column = "column $j - number $number"
                    val square = "square ${i / 3}${j / 3} - number $number"
                    if (
                        set.contains(row) ||
                        set.contains(column) ||
                        set.contains(square)
                    ) {
                        return false
                    }

                    set.addAll(setOf(row, column, square))
                }
            }
        }

        return true
    }
}

fun main() {
    val solution = `36`()
    println(solution.isValidSudoku(arrayOf(
        charArrayOf('5','3','.','.','7','.','.','.','.'),
        charArrayOf('6','.','.','1','9','5','.','.','.'),
        charArrayOf('.','9','8','.','.','.','.','6','.'),
        charArrayOf('8','.','.','.','6','.','.','.','3'),
        charArrayOf('4','.','.','8','.','3','.','.','1'),
        charArrayOf('7','.','.','.','2','.','.','.','6'),
        charArrayOf('.','6','.','.','.','.','2','8','.'),
        charArrayOf('.','.','.','4','1','9','.','.','5'),
        charArrayOf('.','.','.','.','8','.','.','7','9')
    ))) // true
    println(solution.isValidSudoku(arrayOf(
        charArrayOf('8','3','.','.','7','.','.','.','.'),
        charArrayOf('6','.','.','1','9','5','.','.','.'),
        charArrayOf('.','9','8','.','.','.','.','6','.'),
        charArrayOf('8','.','.','.','6','.','.','.','3'),
        charArrayOf('4','.','.','8','.','3','.','.','1'),
        charArrayOf('7','.','.','.','2','.','.','.','6'),
        charArrayOf('.','6','.','.','.','.','2','8','.'),
        charArrayOf('.','.','.','4','1','9','.','.','5'),
        charArrayOf('.','.','.','.','8','.','.','7','9')
    ))) // false
}
