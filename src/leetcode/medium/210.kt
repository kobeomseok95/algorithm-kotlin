package leetcode.medium

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Constraints:
 * - 1 <= numCourses <= 2000
 * - 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * - prerequisites[i].length == 2
 * - 0 <= ai, bi < numCourses
 * - ai != bi
 * - All the pairs [ai, bi] are distinct.
 *
 * V: 노드 수, E: 간선
 * 시간 복잡도: O(V + E)
 * 공간 복잡도: O(V + E)
 */
class `210` {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val degrees = IntArray(numCourses)
        val graph = mutableMapOf<Int, MutableList<Int>>()
        prerequisites.forEach { (next, prev) ->
            degrees[next]++
            graph.getOrPut(prev) { mutableListOf() }.add(next)
        }

        val queue = ArrayDeque<Int>()
        degrees.forEachIndexed { course, degree ->
            if (degree == 0) {
                queue.add(course)
            }
        }
        val orders = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            orders.add(current)

            graph[current].orEmpty().forEach { next ->
                degrees[next]--
                if (degrees[next] == 0) {
                    queue.add(next)
                }
            }
        }

        return if (orders.size == numCourses) {
            orders.toIntArray()
        } else {
            intArrayOf()
        }
    }
}

fun main() {
    val solution = `210`()
    println(solution.findOrder(2, arrayOf(intArrayOf(1, 0))).toList()) // [0,1]
    println(solution.findOrder(4, arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(3, 1), intArrayOf(3, 2))).toList()) // [0,2,1,3]
    println(solution.findOrder(1, arrayOf()).toList()) // [0]
    println(solution.findOrder(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 0))).toList()) // []
    println(solution.findOrder(3, arrayOf(intArrayOf(1, 0), intArrayOf(1, 2), intArrayOf(0, 1))).toList()) // []
    println(solution.findOrder(3, arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(2, 1))).toList()) // [0, 1, 2]
    println(solution.findOrder(4, arrayOf(intArrayOf(1, 0), intArrayOf(3, 2))).toList()) // [0, 1, 2, 3]
}
