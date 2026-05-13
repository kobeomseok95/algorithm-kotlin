package leetcode.medium

import java.util.PriorityQueue

/**
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.
 *
 * Return the minimum number of CPU intervals required to complete all tasks.
 *
 * Constraints:
 * - 1 <= tasks.length <= 10^4
 * - tasks[i] is an uppercase English letter.
 * - 0 <= n <= 100
 *
 * tasks.length = T / n = N
 * 시간 복잡도: O(T * N)
 * 공간 복잡도: O(1)
 */
class `621` {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        if (n == 0) {
            return tasks.size
        }
        val frequencies = IntArray(26)
        tasks.forEach { task -> frequencies[task - 'A']++ }
        val priorityQueue = PriorityQueue<Int> { o1, o2 -> o2 - o1 }
        frequencies.forEach { frequency ->
            if (frequency > 0) {
                priorityQueue.add(frequency)
            }
        }

        val tempQueue = ArrayDeque<Task>()
        var totalTime = 0
        while (priorityQueue.isNotEmpty() || tempQueue.isNotEmpty()) {
            totalTime++
            if (priorityQueue.isNotEmpty()) {
                val currentFrequency = priorityQueue.poll() - 1
                if (currentFrequency > 0) {
                    tempQueue.add(Task(frequency = currentFrequency, idle = n + totalTime))
                }
            }

            if (tempQueue.isNotEmpty() && tempQueue.first().idle == totalTime) {
                priorityQueue.add(tempQueue.removeFirst().frequency)
            }
        }

        return totalTime
    }

    private data class Task(
        val frequency: Int,
        val idle: Int,
    )
}

fun main() {
    val solution = `621`()
    println(solution.leastInterval(charArrayOf('A','A','A','B','B','B'), 2)) // 8
    println(solution.leastInterval(charArrayOf('A','C','A','B','D','B'), 1)) // 6
    println(solution.leastInterval(charArrayOf('A','A','A','B','B','B'), 3)) // 10
}
