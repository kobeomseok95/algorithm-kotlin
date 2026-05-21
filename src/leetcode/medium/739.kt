package leetcode.medium

import java.util.Stack

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Constraints:
 * - 1 <= temperatures.length <= 10^5
 * - 30 <= temperatures[i] <= 100
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `739` {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val answer = IntArray(temperatures.size)
        val dayStack = Stack<Int>().apply { push(0) }
        for (today in 1 until temperatures.size) {
            val todayTemperature = temperatures[today]
            while (dayStack.isNotEmpty() && temperatures[dayStack.peek()] < todayTemperature) {
                val beforeDay = dayStack.pop()
                answer[beforeDay] = today - beforeDay
            }
            dayStack.push(today)
        }
        return answer
    }
}

fun main() {
    val solution = `739`()
    println(solution.dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)).toList()) // [1, 1, 4, 2, 1, 1, 0, 0]
    println(solution.dailyTemperatures(intArrayOf(30, 40, 50, 60)).toList()) // [1, 1, 1, 0]
    println(solution.dailyTemperatures(intArrayOf(30, 60, 90)).toList()) // [1, 1, 0]
}
