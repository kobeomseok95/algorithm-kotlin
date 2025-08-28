package leetcode.medium

import java.util.LinkedList

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n) => 트리의 최대 너비
 */
class `199` {
    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) {
            return listOf()
        }
        val answer = mutableListOf<Int>()
        val queue = LinkedList<TreeNode>().apply { add(root) }

        while (queue.isNotEmpty()) {
            answer.add(queue.peekLast().`val`)

            val size = queue.size
            for (i in 0 until size) {
                val current = queue.poll()
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
        }

        return answer
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
