package leetcode.medium

import java.util.PriorityQueue

/**
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 *
 * Constraints:
 *  1 <= k <= n <= 100
 *  1 <= times.length <= 6000
 *  times[i].length == 3
 *  1 <= ui, vi <= n
 *  ui != vi
 *  0 <= wi <= 100
 *  All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 *
 * [ui, vi, wi] = [출발노드, 도착노드, 시간]
 * k 노드에서 신호를 보내 모든 n개 노드가 수신하는 데 걸리는 최소 시간 구하기
 *
 * 간선 수 = E
 * 포인트 수 = V = n
 * 시간 복잡도 : O(E * logV)
 *   - getGraph: O(E)
 *   - getDist: O(E * logV) - 각 간선마다 힙 연산
 *   - dist.max(): O(n)
 * 공간 복잡도 : O(E + n)
 *   - graph: O(E)
 *   - dist: O(n)
 *   - pq: 최악 O(E)
 */
class `743` {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val graph = getGraph(times)
        val dist = getDist(graph, n, k)
        return dist.max().takeIf { it != MAX } ?: -1
    }

    private fun getGraph(times: Array<IntArray>): Map<Int, List<Pair<Int, Int>>> {
        return times.groupBy({ it[0] }) { it[1] to it[2] }
    }

    private fun getDist(graph: Map<Int, List<Pair<Int, Int>>>, n: Int, start: Int): IntArray {
        val dist = IntArray(n + 1) { MAX }
        dist[0] = 0
        dist[start] = 0
        val pq = PriorityQueue<Pair<Int, Int>> { a, b -> a.second - b.second }
        pq.add(start to 0)

        while (pq.isNotEmpty()) {
            val (node, time) = pq.poll()
            if (dist[node] < time) {
                continue
            }

            graph[node]?.forEach { (next, weight) ->
                if (dist[next] > time + weight) {
                    dist[next] = time + weight
                    pq.add(next to time + weight)
                }
            }
        }

        return dist
    }

    companion object {
        private const val MAX = Int.MAX_VALUE
    }
}

fun main() {
    val solution = `743`()
    println( // 3
        solution.networkDelayTime(
            times = arrayOf(
                intArrayOf(1,2,1),
                intArrayOf(2,1,3),
            ),
            n = 2,
            k = 2,
        )
    )
    println( // 2
        solution.networkDelayTime(
            times = arrayOf(
                intArrayOf(2,1,1),
                intArrayOf(2,3,1),
                intArrayOf(3,4,1),
            ),
            n = 4,
            k = 2,
        )
    )
    println( // 1
        solution.networkDelayTime(
            times = arrayOf(
                intArrayOf(1,2,1)
            ),
            n = 2,
            k = 1,
        )
    )
    println( // -1
        solution.networkDelayTime(
            times = arrayOf(
                intArrayOf(1,2,1)
            ),
            n = 2,
            k = 2,
        )
    )
}
