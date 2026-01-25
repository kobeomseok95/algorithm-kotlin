package leetcode.medium

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Constraints:
 *  1 <= numCourses <= 2000
 *  0 <= prerequisites.length <= 5000
 *  prerequisites[i].length == 2
 *  0 <= ai, bi < numCourses
 *  All the pairs prerequisites[i] are unique.
 *
 * DFS 방식으로 접근
 * ㄴ 방문 상태를 3개(탐색전, 중, 후) 로 표현한다.
 * ㄴ 탐색 중 상태로 그래프 탐색 시 사이클을 발견하기 위함
 *
 * 시간 복잡도: O(V + E)
 * 공간 복잡도: O(V + E)
 */
class `207_2` {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = getGraph(prerequisites)
        val visited = Array(numCourses) { CourseStatus.UNVISITED }
        (0 until numCourses).forEach { course ->
            if (visited[course] == CourseStatus.UNVISITED && !dfs(course, graph, visited)) {
                return false
            }
        }
        return true
    }

    private fun dfs(course: Int, graph: Map<Int, List<Int>>, visited: Array<CourseStatus>): Boolean {
        visited[course] = CourseStatus.VISITING
        graph[course].orEmpty().forEach { next ->
            when (visited[next]) {
                CourseStatus.UNVISITED -> {
                    if (!dfs(next, graph, visited)) {
                        return false
                    }
                }
                CourseStatus.VISITING -> return false
                CourseStatus.VISITED -> return@forEach
            }
        }
        visited[course] = CourseStatus.VISITED
        return true
    }

    private fun getGraph(prerequisites: Array<IntArray>): Map<Int, List<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        prerequisites.forEach { (from, to) -> graph.getOrPut(to) { mutableListOf() }.add(from) }
        return graph
    }

    enum class CourseStatus {
        UNVISITED, VISITING, VISITED
    }
}

fun main() {
    val solution = `207_2`()
    println( // true
        solution.canFinish(
            numCourses = 2,
            prerequisites = arrayOf(intArrayOf(1, 0)),
        )
    )
    println( // false
        solution.canFinish(
            numCourses = 2,
            prerequisites = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1)),
        )
    )
    println( // true
        solution.canFinish(
            numCourses = 3,
            prerequisites = arrayOf(intArrayOf(0,1), intArrayOf(1,2)),
        )
    )
    println( // true
        solution.canFinish(
            numCourses = 5,
            prerequisites = arrayOf(intArrayOf(1,4), intArrayOf(2,4), intArrayOf(3,1), intArrayOf(3,2)),
        )
    )
    println( // false
        solution.canFinish(
            numCourses = 20,
            prerequisites = arrayOf(intArrayOf(0,10), intArrayOf(3,18), intArrayOf(5,5), intArrayOf(6,11), intArrayOf(11,14), intArrayOf(13,1), intArrayOf(15,1), intArrayOf(17,4)),
        )
    )
}
