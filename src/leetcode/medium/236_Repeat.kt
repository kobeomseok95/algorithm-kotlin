package leetcode.medium

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [2, 10^5].
 * - -10^9 <= Node.val <= 10^9
 * - All Node.val are unique.
 * - p != q
 * - p and q will exist in the tree.
 *
 * N: tree range
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `236_Repeat` {
    fun lowestCommonAncestor(
        root: TreeNode?,
        p: TreeNode?,
        q: TreeNode?,
    ): TreeNode? {
        if (root == null || p == null || q == null) {
            return null
        }

        val pPanel = getPanel(root, p)
        val qPanel = getPanel(root, q)
        if (pPanel == null || qPanel == null) {
            return null
        }

        var ancestor: TreeNode? = null
        (0 until minOf(pPanel.size, qPanel.size)).forEach { i ->
            if (pPanel[i].`val` == qPanel[i].`val`) {
                ancestor = pPanel[i]
            }
        }
        return ancestor
    }

    private fun getPanel(
        node: TreeNode,
        goal: TreeNode,
        current: MutableList<TreeNode> = mutableListOf(node),
    ): List<TreeNode>? {
        if (node.`val` == goal.`val`) {
            return current.toList()
        }
        if (node.left == null && node.right == null) {
            return null
        }

        return node.left?.let { leftNode ->
            current.add(leftNode)
            getPanel(leftNode, goal, current).apply { current.removeLast() }
        } ?: node.right?.let { rightNode ->
            current.add(rightNode)
            getPanel(rightNode, goal, current).apply { current.removeLast() }
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `236_Repeat`()

    // Example 1: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    val n3 = `236_Repeat`.TreeNode(3)
    val n5 = `236_Repeat`.TreeNode(5)
    val n1 = `236_Repeat`.TreeNode(1)
    val n6 = `236_Repeat`.TreeNode(6)
    val n2 = `236_Repeat`.TreeNode(2)
    val n0 = `236_Repeat`.TreeNode(0)
    val n8 = `236_Repeat`.TreeNode(8)
    val n7 = `236_Repeat`.TreeNode(7)
    val n4 = `236_Repeat`.TreeNode(4)
    n3.left = n5; n3.right = n1
    n5.left = n6; n5.right = n2
    n1.left = n0; n1.right = n8
    n2.left = n7; n2.right = n4

    println(solution.lowestCommonAncestor(n3, n5, n1)?.`val`) // 3
    println(solution.lowestCommonAncestor(n3, n5, n4)?.`val`) // 5

    // Example 3: root = [1,2], p = 1, q = 2
    val m1 = `236_Repeat`.TreeNode(1)
    val m2 = `236_Repeat`.TreeNode(2)
    m1.left = m2

    println(solution.lowestCommonAncestor(m1, m1, m2)?.`val`) // 1
}
