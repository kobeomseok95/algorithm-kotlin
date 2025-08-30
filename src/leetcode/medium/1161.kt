package leetcode.medium

import java.util.LinkedList

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(w) => 최대 너비 크기
 */
class `1161` {
    fun maxLevelSum(root: TreeNode?): Int {
        var level = 0
        var answer = MaxSumLevel(level, Int.MIN_VALUE)
        val queue = LinkedList<TreeNode>()
        root?.let { queue.add(it) }

        while (queue.isNotEmpty()) {
            level += 1
            var sum = 0
            val currentSize = queue.size
            (0 until currentSize).forEach { _ ->
                val node = queue.poll()
                sum += node.`val`
                node.left?.let(queue::add)
                node.right?.let(queue::add)
            }
            answer = if (answer.sum < sum) {
                MaxSumLevel(level, sum)
            } else {
                answer
            }
        }

        return answer.level
    }

    data class MaxSumLevel(
        val level: Int,
        val sum: Int,
    )

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
