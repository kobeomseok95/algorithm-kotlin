package leetcode.medium

/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.
 *
 * Constraints:
 * - n == gas.length == cost.length
 * - 1 <= n <= 10^5
 * - 0 <= gas[i], cost[i] <= 10^4
 * - The input is generated such that the answer is unique.
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `134` {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var total = 0
        var currentGas = 0
        var startStation = 0
        for (i in gas.indices) {
            val totalCost = gas[i] - cost[i]
            total += totalCost
            currentGas += totalCost
            if (currentGas < 0) {
                currentGas = 0
                startStation = i + 1
            }
        }

        return if (total >= 0) {
            startStation
        } else {
            -1
        }
    }
}

fun main() {
    val solution = `134`()
    println(solution.canCompleteCircuit(intArrayOf(5,1,2,3,4), intArrayOf(4,4,1,5,1))) // 4
    println(solution.canCompleteCircuit(intArrayOf(1,2,3,4,5), intArrayOf(3,4,5,1,2))) // 3
    println(solution.canCompleteCircuit(intArrayOf(2,3,4), intArrayOf(3,4,3))) // -1
}
