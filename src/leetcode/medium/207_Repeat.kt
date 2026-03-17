package leetcode.medium

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 * take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Constraints:
 * - 1 <= numCourses <= 2000
 * - 0 <= prerequisites.length <= 5000
 * - prerequisites[i].length == 2
 * - 0 <= ai, bi < numCourses
 * - All the pairs prerequisites[i] are unique.
 *
 * N: numCourses, E: prerequisites.length
 * 시간 복잡도: O(N + E)
 * 공간 복잡도: O(N + E)
 */
class `207_Repeat` {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val indegrees = createIndegreeOrNull(numCourses, prerequisites) ?: return false
        val graph = createGraph(prerequisites)
        val queue = createQueue(indegrees)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            graph[current].orEmpty().forEach { next ->
                indegrees[next]--
                if (indegrees[next] == 0) {
                    queue.add(next)
                }
            }
        }

        return indegrees.all { it == 0 }
    }

    private fun createIndegreeOrNull(numCourses: Int, prerequisites: Array<IntArray>): IntArray? {
        val indegrees = IntArray(numCourses)
        prerequisites.forEach { (a, b) ->
            indegrees[a] += 1
        }
        return indegrees.takeIf { it.any { indegree -> indegree == 0 } }
    }

    private fun createGraph(prerequisites: Array<IntArray>): Map<Int, List<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        prerequisites.forEach { (a, b) ->
            graph.getOrPut(b) { mutableListOf() }.add(a)
        }
        return graph
    }

    private fun createQueue(indegrees: IntArray): ArrayDeque<Int> {
        val queue = ArrayDeque<Int>()
        indegrees.forEachIndexed { index, indegree ->
            if (indegree == 0) {
                queue.add(index)
            }
        }
        return queue
    }
}

fun main() {
    val solution = `207_Repeat`()
    // false
    println(solution.canFinish(4, arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(3, 0))))
    // false
    println(solution.canFinish(3, arrayOf(intArrayOf(1, 0), intArrayOf(1, 2), intArrayOf(0, 1))))
    // true
    println(solution.canFinish(1, arrayOf()))
    // true
    println(solution.canFinish(2, arrayOf(intArrayOf(1, 0))))
    // false
    println(solution.canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))))
    // false
    println(solution.canFinish(3, arrayOf(intArrayOf(1, 0), intArrayOf(2, 1), intArrayOf(0, 2))))
    // true
    println(solution.canFinish(5, arrayOf(intArrayOf(1, 4), intArrayOf(2, 4), intArrayOf(3, 1), intArrayOf(3, 2))))
    // true
    println(solution.canFinish(7, arrayOf(intArrayOf(1, 0), intArrayOf(0, 3), intArrayOf(0, 2), intArrayOf(3, 2), intArrayOf(2, 5), intArrayOf(4, 5), intArrayOf(5, 6), intArrayOf(2, 4))))
}
