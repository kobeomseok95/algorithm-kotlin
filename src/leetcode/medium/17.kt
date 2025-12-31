package leetcode.medium

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 1 -> []
 * 2 -> [a,b,c]
 * 3 -> [d,e,f]
 * 4 -> [g,h,i]
 * 5 -> [j,k,l]
 * 6 -> [m,n,o]
 * 7 -> [p,q,r,s]
 * 8 -> [t,u,v]
 * 9 -> [w,x,y,z]
 *
 * Constraints:
 * 1 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 *
 * DFS 방식으로 풀이
 *
 * 시간 복잡도 : O(n * 4^n) -> 하나의 digit 당 최대 4개임을 고려하면 4^n 번 만큼 반복 * 최대 재귀 스택 n
 * 공간 복잡도 : O(n * 4^n) -> 하나의 digit 당 최대 4개임을 고려하면 4^n 번 만큼 반복 * 최대 재귀 스택 n
 */
class `17` {
    fun letterCombinations(digits: String): List<String> {
        val digitsMap = createDigitsMap()
        val answer = mutableListOf<String>()
        dfs(answer, digits, digitsMap)
        return answer
    }

    private fun dfs(
        answer: MutableList<String>,
        digits: String,
        digitsMap: Map<Char, List<String>>,
        digitsHolder: DigitsHolder = DigitsHolder.empty(),
    ) {
        if (digitsHolder.index >= digits.length) {
            answer.add(digitsHolder.digits)
            return
        }

        val letters = digitsMap.getOrElse(digits[digitsHolder.index]) { listOf() }
        letters.forEach { letter ->
            dfs(
                answer = answer,
                digits = digits,
                digitsMap = digitsMap,
                digitsHolder = DigitsHolder.of(
                    digits = digitsHolder.digits + letter,
                    index = digitsHolder.index + 1,
                ),
            )
        }
    }

    private fun createDigitsMap(): Map<Char, List<String>> {
        return mapOf(
            '2' to listOf("a","b","c"),
            '3' to listOf("d","e","f"),
            '4' to listOf("g","h","i"),
            '5' to listOf("j","k","l"),
            '6' to listOf("m","n","o"),
            '7' to listOf("p","q","r","s"),
            '8' to listOf("t","u","v"),
            '9' to listOf("w","x","y","z"),
        )
    }

    private data class DigitsHolder(
        val digits: String,
        val index: Int,
    ) {
        companion object {
            fun empty() = DigitsHolder("", 0)

            fun of(
                digits: String,
                index: Int,
            ) = DigitsHolder(
                digits = digits,
                index = index,
            )
        }
    }
}

fun main() {
    val solution = `17`()
    println(solution.letterCombinations("234"))
    println(solution.letterCombinations("79"))
    println(solution.letterCombinations("23"))
    println(solution.letterCombinations("2"))
}
