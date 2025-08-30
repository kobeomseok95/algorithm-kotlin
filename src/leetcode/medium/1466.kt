package leetcode.medium

import java.util.LinkedList

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n - 1)
 */
class `1466` {
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        var count = 0
        val visit = BooleanArray(n) { false }
        val connectionMap = getConnections(connections)
        val queue = LinkedList<Int>().apply { offer(0) }
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            visit[current] = true
            val next = connectionMap[current].orEmpty()
            next.forEach { next ->
                if (!visit[next.to]) {
                    if (next.isForward) {
                        count += 1
                    }
                    queue.offer(next.to)
                }
            }
        }
        return count
    }

    private fun getConnections(connections: Array<IntArray>): Map<Int, List<Next>> {
        val map = mutableMapOf<Int, MutableList<Next>>()
        connections.forEach { (from, to) ->
            map.getOrPut(from) {
                mutableListOf()
            }.add(Next(to, true))
            map.getOrPut(to) {
                mutableListOf()
            }.add(Next(from, false))
        }
        return map
    }

    data class Next(
        val to: Int,
        val isForward: Boolean,
    )
}
