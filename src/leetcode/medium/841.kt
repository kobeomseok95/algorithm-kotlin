package leetcode.medium

import java.util.LinkedList

/**
 *  BFS 방식
 *  n = 방 갯수, E = 간선 수
 *  시간 복잡도 : O(n + E)
 *  공간 복잡도 : O(n)
 */
class `841` {
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val visits = BooleanArray(rooms.size) { false }
        val queue = LinkedList<Int>().apply {
            offer(0)
            visits[0] = true
        }
        while (queue.isNotEmpty()) {
            val room = queue.poll()
            rooms[room].forEach { notVisitRoom ->
                if (visits[notVisitRoom].not()) {
                    queue.offer(notVisitRoom)
                    visits[notVisitRoom] = true
                }
            }
        }
        return visits.all { it }
    }
}
