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
 * DFS + 백트래킹 방식으로 풀이
 * 17.kt 풀이에서는 객체 생성 및 매번 새로운 String 을 생성했다. 이 비용을 개선한 방식
 *
 * 시간 복잡도 : O(n * 4^n) -> 하나의 digit 당 최대 4개임을 고려하면 4^n 번 만큼 반복 * 최대 재귀 스택 n
 * 공간 복잡도 : O(n * 4^n) -> 하나의 digit 당 최대 4개임을 고려하면 4^n 번 만큼 반복 * 최대 재귀 스택 n
 */
class `17_2` {
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
        currentDigits: StringBuilder = StringBuilder(),
        currentIndex: Int = 0,
    ) {
        if (currentIndex == digits.length) {
            answer.add(currentDigits.toString())
            return
        }

        val letters = digitsMap.getOrElse(digits[currentIndex]) { listOf() }
        letters.forEach { letter ->
            val newDigits = currentDigits.append(letter)
            dfs(
                answer,
                digits,
                digitsMap,
                newDigits,
                currentIndex + 1,
            )
            newDigits.deleteCharAt(newDigits.lastIndex)
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
}

fun main() {
    val solution = `17_2`()
    println(solution.letterCombinations("234"))
    println(solution.letterCombinations("79"))
    println(solution.letterCombinations("23"))
    println(solution.letterCombinations("2"))
}
