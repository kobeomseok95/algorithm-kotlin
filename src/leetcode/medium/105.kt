package leetcode.medium

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * Constraints:
 * - 1 <= preorder.length <= 3000
 * - inorder.length == preorder.length
 * - -3000 <= preorder[i], inorder[i] <= 3000
 * - preorder and inorder consist of unique values.
 * - Each value of inorder also appears in preorder.
 * - preorder is guaranteed to be the preorder traversal of the tree.
 * - inorder is guaranteed to be the inorder traversal of the tree.
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class `105` {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inorderIndices = inorder.mapIndexed { index, i -> i to index }.toMap()
        var rootIndex = 0

        fun build(left: Int, right: Int): TreeNode? {
            if (left > right) {
                return null
            }

            val value = preorder[rootIndex++]
            val treeNode = TreeNode(value)
            val index = inorderIndices[value] ?: -1
            treeNode.left = build(left, index - 1)
            treeNode.right = build(index + 1, right)
            return treeNode
        }

        return build(0, preorder.lastIndex)
    }
}

fun main() {
    val solution = `105`()
    println(solution.buildTree(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7))) // [3,9,20,null,null,15,7]
    println(solution.buildTree(intArrayOf(-1), intArrayOf(-1))) // [-1]
}
