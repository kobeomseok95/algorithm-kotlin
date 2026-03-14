package leetcode.medium

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 * - The valid operators are '+', '-', '*', and '/'.
 * - Each operand may be an integer or another expression.
 * - The division between two integers always truncates toward zero.
 * - There will not be any division by zero.
 * - The input represents a valid arithmetic expression in a reverse polish notation.
 * - The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Constraints:
 * - 1 <= tokens.length <= 10^4
 * - tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 *
 * N: tokens.length
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `150_2` {
    fun evalRPN(tokens: Array<String>): Int {
        return dfs(tokens).value
    }

    private fun dfs(
        tokens: Array<String>,
        currentIndex: Int = tokens.lastIndex,
    ): IndexValue {
        if (tokens[currentIndex].toIntOrNull() != null) {
            return IndexValue(
                index = currentIndex,
                value = tokens[currentIndex].toInt(),
            )
        }

        val b = dfs(tokens, currentIndex - 1)
        val a = dfs(tokens, b.index - 1)
        val result = Operator.getValidOperator(tokens[currentIndex]).calculate(a.value, b.value)

        return IndexValue(
            index = a.index,
            value = result,
        )
    }

    private data class IndexValue(
        val index: Int,
        val value: Int,
    )

    private enum class Operator(val operatorString: String) {
        PLUS("+") {
            override fun calculate(a: Int, b: Int): Int {
                return a + b
            }
        },
        MINUS("-") {
            override fun calculate(a: Int, b: Int): Int {
                return a - b
            }
        },
        TIMES("*") {
            override fun calculate(a: Int, b: Int): Int {
                return a * b
            }
        },
        DIVIDE("/") {
            override fun calculate(a: Int, b: Int): Int {
                return a / b
            }
        },
        ;

        abstract fun calculate(a: Int, b: Int): Int

        companion object {
            fun getValidOperator(operatorString: String): Operator {
                return Operator.entries.firstOrNull { it.operatorString == operatorString }
                    ?: throw IllegalArgumentException("Unknown operator: $operatorString")
            }
        }
    }
}

fun main() {
    val solution = `150_2`()
    println(solution.evalRPN(arrayOf("2", "1", "+", "3", "*"))) // 9
    println(solution.evalRPN(arrayOf("4", "13", "5", "/", "+"))) // 6
    println(solution.evalRPN(arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"))) // 22
}
