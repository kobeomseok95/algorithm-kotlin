package leetcode.medium

/**
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 *
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 *
 * Constraints:
 * - 1 <= n <= 5 * 10^4
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `386` {
    fun lexicalOrder(n: Int): List<Int> {
        val answer = mutableListOf<Int>()
        var current = 1
        repeat(n) {
            answer.add(current)
            if (current * 10 <= n) {
                current *= 10
            } else {
                while (current % 10 == 9 || current >= n) {
                    current /= 10
                }
                current++
            }
        }
        return answer
    }
}

fun main() {
    val solution = `386`()
    println(solution.lexicalOrder(10))
    println(solution.lexicalOrder(354))
    println(solution.lexicalOrder(13)) // [1,10,11,12,13,2,3,4,5,6,7,8,9]
    println(solution.lexicalOrder(2)) // [1,2]
}
