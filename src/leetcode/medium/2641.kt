package leetcode.medium

import java.util.LinkedList

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 *
 * Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 * Return the root of the modified tree.
 * Note that the depth of a node is the number of edges in the path from the root node to it.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^4
 *
 * 두 노드가 사촌 관계 == 서로 다른 부모를 가지며 동일한 depth 에 위치한다.
 * 사촌 노드들의 합으로 값을 대체한다.
 *
 * 같은 depth 의 서로 다른 노드들에 대한 합을 구해야하므로 BFS 로 접근한다.
 * 각 노드에 수정해야할 `val` = BFS 로 구한 같은 레벨의 총 노드의 `val` 합 - 같은 부모에 대한 자식들의 `val` 합
 *
 * 시간 복잡도 : O(n) -> 전체 노드에서 최대 자식 갯수인 2번 순회하므로 O(2n) -> O(n)
 * 공간 복잡도 : O(n) -> 현재 노드의 최대 자식 갯수인 n / 2 만큼 늘어나므로 O(n / 2) -> O(n)
 */
class `2641` {
    fun replaceValueInTree(root: TreeNode?): TreeNode? {
        val queue = LinkedList<Pair<TreeNode?, List<TreeNode>>>()
            .apply { root?.let { this.add(null to listOf(it)) } }

        while (queue.isNotEmpty()) {
            val currentDepths = mutableListOf<Pair<TreeNode?, List<TreeNode>>>()
            while (queue.isNotEmpty()) {
                currentDepths.add(queue.poll())
            }

            val depthSum = currentDepths.sumOf { it.second.sumOf { it.`val` } }
            currentDepths.forEach { (_, nodes) ->
                val childrenSum = nodes.sumOf { it.`val` }
                nodes.forEach { node ->
                    node.`val` = depthSum - childrenSum
                    queue.add(node to listOfNotNull(node.left, node.right))
                }
            }
        }

        return root
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `2641`()
    println(
        solution.replaceValueInTree(
            `2641`.TreeNode(5).apply {
                left = `2641`.TreeNode(4).apply {
                    left = `2641`.TreeNode(1)
                    right = `2641`.TreeNode(10)
                }
                right = `2641`.TreeNode(9).apply {
                    right = `2641`.TreeNode(7)
                }
            }
        )
    ) // [0,0,0,7,7,null,11]
    println(
        solution.replaceValueInTree(
            `2641`.TreeNode(3).apply {
                left = `2641`.TreeNode(1)
                right = `2641`.TreeNode(2)
            }
        )
    ) // [0,0,0]
}
