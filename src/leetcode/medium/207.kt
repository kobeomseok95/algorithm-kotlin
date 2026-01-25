package leetcode.medium

import java.util.LinkedList

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
 * 위상 정렬을 통해 course 별 차수를 표현한다.
 * 그래프 탐색(BFS)을 통해 차수를 차감하고, 차수가 0인 course 를 처리 시에는 완료된 코스 셋에 넣어준다.
 * 완료된 코스 셋의 크기가 numCourses와 같으면 true / 아니면 false 를 반환한다.
 * ㄴ 이유: 위상 정렬의 경우 선행 코스가 존재하는 만큼 값을 추가한다. 이를 하나씩 차감하며 순환 시 꼬임 없이 모든 코스를 순환한다면 모든 값이 0으로 변할 것이기 때문
 *
 * 시간 복잡도
 *  indegree 생성: prerequisites.length
 *  graph 생성: prerequisites.length
 *  간선 + 구간 만큼의 탐색
 *  최종 O(V + E) 의 시간 복잡도
 * 공간 복잡도 : O(V + E)
 */
class `207` {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val indegree = getIndegree(numCourses, prerequisites)
        val graph = getGraph(prerequisites)
        val completedCourses = mutableSetOf<Int>()
        val queue = LinkedList<Int>()
        indegree.forEachIndexed { index, degree ->
            if (degree == 0) {
                queue.add(index)
            }
        }

        while (queue.isNotEmpty()) {
            val course = queue.poll()
            completedCourses += course
            graph[course].orEmpty().forEach { next ->
                indegree[next]--
                if (indegree[next] == 0) {
                    queue.add(next)
                }
            }
        }

        return completedCourses.size == numCourses
    }

    private fun getIndegree(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val indegree = IntArray(numCourses) { 0 }
        prerequisites.forEach { (from, _) -> indegree[from]++ }
        return indegree
    }

    private fun getGraph(prerequisites: Array<IntArray>): Map<Int, List<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        prerequisites.forEach { (from, to) -> graph.getOrPut(to) { mutableListOf() }.add(from) }
        return graph
    }
}

fun main() {
    val solution = `207`()
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
}
