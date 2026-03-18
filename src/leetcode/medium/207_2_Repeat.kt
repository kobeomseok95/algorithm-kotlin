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
 * N: 지점, E: 간선
 * 시간 복잡도: O(N + E)
 * 공간 복잡도: O(N + E)
 */
class `207_2_Repeat` {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val map = createMapOrNull(prerequisites) ?: return true
        val progress = Array(numCourses) { Status.BEFORE_PROCEEDING }
        prerequisites.forEach { (_, course) ->
            if (!dfs(course, progress, map)) {
                return false
            }
        }
        return true
    }

    private fun createMapOrNull(prerequisites: Array<IntArray>): Map<Int, Set<Int>>? {
        val map = mutableMapOf<Int, MutableSet<Int>>()
        prerequisites.forEach { (end, start) ->
            map.getOrPut(start) { mutableSetOf() }.add(end)
        }
        return map.takeIf { it.isNotEmpty() }
    }

    private fun dfs(course: Int, progress: Array<Status>, map: Map<Int, Set<Int>>): Boolean {
        if (progress[course] == Status.DONE) {
            return true
        }
        if (progress[course] == Status.IN_PROGRESS) {
            return false
        }
        progress[course] = Status.IN_PROGRESS
        map[course].orEmpty().forEach { next ->
            if (!dfs(next, progress, map)) {
                return false
            }
        }
        progress[course] = Status.DONE

        return true
    }

    private enum class Status {
        BEFORE_PROCEEDING,
        IN_PROGRESS,
        DONE,
        ;
    }
}

fun main() {
    val solution = `207_2_Repeat`()
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
    // false
    println(solution.canFinish(20, arrayOf(intArrayOf(0, 10), intArrayOf(3, 18), intArrayOf(5, 5), intArrayOf(6, 11), intArrayOf(11, 14), intArrayOf(13, 1), intArrayOf(15, 1), intArrayOf(17, 4))))
    // false
    println(solution.canFinish(3, arrayOf(intArrayOf(0, 2), intArrayOf(1, 2), intArrayOf(2, 0))))
}
