package leetcode.medium

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * Constraints:
 * - The number of nodes in the linked list is in the range [0, 10^4].
 * - -10^6 <= Node.val <= 10^6
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `328` {
    fun oddEvenList(head: ListNode?): ListNode? {
        var oddCurrent = head
        var evenCurrent: ListNode? = head?.next
        val evenTop: ListNode? = head?.next
        while (oddCurrent?.next?.next != null) {
            val oddNext = evenCurrent?.next
            val evenNext = oddNext?.next

            oddCurrent.next = oddNext
            evenCurrent?.next = evenNext

            oddCurrent = oddNext
            evenCurrent = evenNext
        }
        oddCurrent?.next = evenTop
        return head
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}

private fun listOfNodes(vararg values: Int): `328`.ListNode? {
    val dummy = `328`.ListNode(0)
    var current = dummy
    for (value in values) {
        current.next = `328`.ListNode(value)
        current = current.next!!
    }
    return dummy.next
}

private fun `328`.ListNode?.toList(): List<Int> {
    val result = mutableListOf<Int>()
    var node = this
    while (node != null) {
        result.add(node.`val`)
        node = node.next
    }
    return result
}

fun main() {
    val solution = `328`()
    println(solution.oddEvenList(listOfNodes(1, 2, 3, 4)).toList()) // [1, 3, 2, 4]
    println(solution.oddEvenList(listOfNodes(1, 2, 3, 4, 5)).toList()) // [1, 3, 5, 2, 4]
    println(solution.oddEvenList(listOfNodes(2, 1, 3, 5, 6, 4, 7)).toList()) // [2, 3, 6, 7, 1, 5, 4]
}
