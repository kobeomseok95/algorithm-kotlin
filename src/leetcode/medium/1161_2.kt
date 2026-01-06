package leetcode.medium

import java.util.LinkedList

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -10^5 <= Node.val <= 10^5
 *
 * 같은 레벨 별 최대 합을 구하는 문제.
 * BFS 로 접근하여 각 레벨당 합을 구하여 가장 큰 레벨을 반환한다.
 * (너비 우선 탐색으로 한 번에 레벨의 합을 구하는 이점이 크다고 판단)
 *
 * 시간 복잡도 : O(n) -> 최종적으로 노드 갯수만큼 순회하기 때문
 * 공간 복잡도 : O(n / 2) -> O(n) -> queue의 균등 트리의 경우 최대 n / 2 개
 */
class `1161_2` {
    fun maxLevelSum(root: TreeNode?): Int {
        root ?: throw IllegalArgumentException("root is null")
        var maxLevel = 0
        var maxSum = Int.MIN_VALUE
        var currentLevel = 1
        val queue = LinkedList<TreeNode>().apply { add(root) }
        while (queue.isNotEmpty()) {
            var sum = 0
            repeat(queue.size) {
                val node = queue.poll()
                sum += node.`val`
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            if (sum > maxSum) {
                maxSum = sum
                maxLevel = currentLevel
            }
            currentLevel += 1
        }
        return maxLevel
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `1161_2`()
    println(
        solution.maxLevelSum(
            `1161_2`.TreeNode(1).apply {
                left = `1161_2`.TreeNode(7).apply {
                    left = `1161_2`.TreeNode(7)
                    right = `1161_2`.TreeNode(-8)
                }
                right = `1161_2`.TreeNode(0)
            }
        )
    )
    println(
        solution.maxLevelSum(
            `1161_2`.TreeNode(989).apply {
                right = `1161_2`.TreeNode(10250).apply {
                    left = `1161_2`.TreeNode(98693)
                    right = `1161_2`.TreeNode(-89388).apply {
                        right = `1161_2`.TreeNode(-32127)
                    }
                }
            }
        )
    )
}
