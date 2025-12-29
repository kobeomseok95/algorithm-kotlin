package leetcode.medium

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Constraints:
 * - 1 <= n <= 8
 *
 * well-formed parentheses 란?
 *  - 시작은 항상 열린 괄호
 *  - 끝은 항상 닫힌 괄호
 *  - 주어진 n개 만큼의 열린, 닫힌 괄호를 모두 사용
 *
 * 시간 복잡도 : O(4^n) -> 카탈란 수 4^n
 * 공간 복잡도 : O(n * 4^n) -> n 번의 dfs depth * 카탈란 수 4^n
 */
class `22` {
    fun generateParenthesis(n: Int): List<String> {
        val answer = mutableListOf<String>()
        dfs(n, answer)
        return answer
    }

    private fun dfs(
        n: Int,
        answer: MutableList<String>,
        current: StringBuilder = StringBuilder(),
        countLeft: Int = 0,
        countRight: Int = 0,
    ) {
        if (countLeft + countRight == 2 * n) {
            answer.add(current.toString())
            return
        }

        if (countLeft < n) {
            current.append(OPEN)
            dfs(n, answer, current, countLeft + 1, countRight)
            current.deleteCharAt(current.lastIndex)
        }

        if (countRight < countLeft) {
            current.append(CLOSE)
            dfs(n, answer, current, countLeft, countRight + 1)
            current.deleteCharAt(current.length - 1)
        }
    }

    companion object {
        private const val OPEN = '('
        private const val CLOSE = ')'
    }
}

fun main() {
    val solution = `22`()
    println(solution.generateParenthesis(n = 3))
    println(solution.generateParenthesis(n = 2))
    println(solution.generateParenthesis(n = 1))
}
