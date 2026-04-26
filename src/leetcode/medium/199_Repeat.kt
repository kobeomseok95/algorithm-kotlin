package leetcode.medium

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 *
 * n: node.size
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n / 2 + 1)
 */
class `199_Repeat` {
    fun rightSideView(root: TreeNode?): List<Int> {
        root ?: return emptyList()
        val answer = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>().apply { this.add(root) }
        while (queue.isNotEmpty()) {
            answer.add(queue.last().`val`)
            val currentSize = queue.size
            repeat(currentSize) {
                val node = queue.removeFirst()
                node.left?.let(queue::add)
                node.right?.let(queue::add)
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
    val solution = `199_Repeat`()

    // Example 1: root = [1,2,3,null,5,null,4]
    val n1 = `199_Repeat`.TreeNode(1)
    val n2 = `199_Repeat`.TreeNode(2)
    val n3 = `199_Repeat`.TreeNode(3)
    val n5 = `199_Repeat`.TreeNode(5)
    val n4 = `199_Repeat`.TreeNode(4)
    n1.left = n2; n1.right = n3
    n2.right = n5
    n3.right = n4
    println(solution.rightSideView(n1)) // [1, 3, 4]

    // Example 2: root = [1,2,3,4,null,null,null,5]
    val m1 = `199_Repeat`.TreeNode(1)
    val m2 = `199_Repeat`.TreeNode(2)
    val m3 = `199_Repeat`.TreeNode(3)
    val m4 = `199_Repeat`.TreeNode(4)
    val m5 = `199_Repeat`.TreeNode(5)
    m1.left = m2; m1.right = m3
    m2.left = m4
    m4.left = m5
    println(solution.rightSideView(m1)) // [1, 3, 4, 5]

    // Example 3: root = [1,null,3]
    val k1 = `199_Repeat`.TreeNode(1)
    val k3 = `199_Repeat`.TreeNode(3)
    k1.right = k3
    println(solution.rightSideView(k1)) // [1, 3]

    // Example 4: root = []
    println(solution.rightSideView(null)) // []
}
