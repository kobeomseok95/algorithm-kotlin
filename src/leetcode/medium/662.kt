package leetcode.medium

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost
 * non-null nodes), where the null nodes between the end-nodes that would be present in a complete
 * binary tree extending down to that level are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 3000].
 * - -100 <= Node.val <= 100
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */
class `662` {
    fun widthOfBinaryTree(root: TreeNode?): Int {
        val queue = ArrayDeque<Pair<Int, TreeNode>>().apply { root?.let { add(1 to it) } }
        var answer = 1
        while (queue.isNotEmpty()) {
            val size = queue.size
            var left: Pair<Int, TreeNode>? = null
            var right: Pair<Int, TreeNode>? = null
            for (i in 0 until size) {
                val current = queue.removeFirst()

                if (left == null) {
                    left = current
                } else {
                    right = current
                }

                current.second.left?.let { queue.add(current.first * 2 to it) }
                current.second.right?.let { queue.add(current.first * 2 + 1 to it) }
            }

            if (left != null && right != null) {
                answer = maxOf(answer, right.first - left.first + 1)
            }
        }
        return answer
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `662`()
    println(
        solution.widthOfBinaryTree(
            // root = [1,3,2,5,3,null,9]
            `662`.TreeNode(1).apply {
                left = `662`.TreeNode(3).apply {
                    left = `662`.TreeNode(5)
                    right = `662`.TreeNode(3)
                }
                right = `662`.TreeNode(2).apply {
                    right = `662`.TreeNode(9)
                }
            }
        )
    ) // 4
    println(
        solution.widthOfBinaryTree(
            // root = [1,3,2,5,null,null,9,6,null,7]
            `662`.TreeNode(1).apply {
                left = `662`.TreeNode(3).apply {
                    left = `662`.TreeNode(5).apply {
                        left = `662`.TreeNode(6)
                    }
                }
                right = `662`.TreeNode(2).apply {
                    right = `662`.TreeNode(9).apply {
                        left = `662`.TreeNode(7)
                    }
                }
            }
        )
    ) // 7
    println(
        solution.widthOfBinaryTree(
            // root = [1,3,2,5]
            `662`.TreeNode(1).apply {
                left = `662`.TreeNode(3).apply {
                    left = `662`.TreeNode(5)
                }
                right = `662`.TreeNode(2)
            }
        )
    ) // 2
}
