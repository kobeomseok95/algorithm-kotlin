package leetcode.medium

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 100].
 * - 0 <= Node.val <= 100
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `24` {
    fun swapPairs(head: ListNode?): ListNode? {
        val result = ListNode(0)
        result.next = head

        var current: ListNode? = result
        while (current?.next?.next != null) {
            val next= current.next
            val next2 = next?.next

            next?.next = next2?.next
            next2?.next = next

            current.next = next2
            current = next
        }

        return result.next
    }
    
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}


private fun listOfNodes(vararg values: Int): `24`.ListNode? {
    val dummy = `24`.ListNode(0)
    var current = dummy
    for (value in values) {
        current.next = `24`.ListNode(value)
        current = current.next!!
    }
    return dummy.next
}

private fun `24`.ListNode?.toList(): List<Int> {
    val result = mutableListOf<Int>()
    var node = this
    while (node != null) {
        result.add(node.`val`)
        node = node.next
    }
    return result
}

fun main() {
    val solution = `24`()
    println(solution.swapPairs(listOfNodes(1, 2, 3, 4)).toList()) // [2, 1, 4, 3]
    println(solution.swapPairs(listOfNodes()).toList()) // []
    println(solution.swapPairs(listOfNodes(1)).toList()) // [1]
    println(solution.swapPairs(listOfNodes(1, 2, 3)).toList()) // [2, 1, 3]
}
