package leetcode.medium

/**
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 * Return the length longest chain which can be formed.
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 * Constraints:
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= lefti < righti <= 1000
 *
 * pairs[i][1] 을 기준으로 오름차순 정렬한다. a < b 를 항상 만족하고, b < c 를 만족하는 최대 길이를 구하는 것이기 때문에
 * pairs[i][1] 오름차순을 하면 더 긴 길이를 구할 수 있다.
 *
 *  시간 복잡도 : O(n log n)
 *  공간 복잡도 : O(1)
 */
class `646_2` {
    fun findLongestChain(pairs: Array<IntArray>): Int {
        pairs.sortBy { it[1] }
        var answer = 0
        var maxValue = Int.MIN_VALUE

        for (pair in pairs) {
            if (maxValue < pair[0]) {
                maxValue = pair[1]
                answer += 1
            }
        }
        return answer
    }
}

fun main() {
    val solution = `646_2`()
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(3,4)))) // 2
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(7,8), intArrayOf(4,5)))) // 3
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(1,2), intArrayOf(1,2)))) // 1
    println(solution.findLongestChain(arrayOf(intArrayOf(1,2), intArrayOf(2,3), intArrayOf(2,3)))) // 1
}
