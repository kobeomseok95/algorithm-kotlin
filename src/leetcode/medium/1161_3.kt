package leetcode.medium

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * -10^5 <= Node.val <= 10^5
 *
 * DFS 방식으로 접근
 *
 * 시간 복잡도 : O(n) -> 노드 갯수만큼 탐색해야한다.
 * 공간 복잡도 : O(2h) -> O(n) -> sumByLevels 리스트의 크기는 최대 h 개이다. 재귀 탐색 시 스택을 통해 참조가 복사된다. 이로 인해 O(h) 의 공간 복잡도가 추가된다.
 *  -> sumByLevels 공간 복잡도 + 재귀 스택 공간 복잡도 = O(h) + O(h) = O(2h) => O(n)
 */
class `1161_3` {
    fun maxLevelSum(root: TreeNode?): Int {
        root ?: throw IllegalArgumentException("root is null")
        val sumByLevels = mutableListOf<Int>()
        dfs(root, sumByLevels)
        return sumByLevels.indices.maxByOrNull { index -> sumByLevels[index] }
            ?.plus(1)
            ?: throw IllegalArgumentException("root is null")

    }

    private fun dfs(
        node: TreeNode?,
        sumByLevels: MutableList<Int>,
        currentLevel: Int = 0,
    ) {
        if (node == null) {
            return
        }
        if (sumByLevels.size == currentLevel) {
            sumByLevels.add(node.`val`)
        } else {
            sumByLevels[currentLevel] += node.`val`
        }
        dfs(node.left, sumByLevels, currentLevel + 1)
        dfs(node.right, sumByLevels, currentLevel + 1)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `1161_3`()
    println(
        solution.maxLevelSum(
            `1161_3`.TreeNode(1).apply {
                left = `1161_3`.TreeNode(7).apply {
                    left = `1161_3`.TreeNode(7)
                    right = `1161_3`.TreeNode(-8)
                }
                right = `1161_3`.TreeNode(0)
            }
        )
    )
    println(
        solution.maxLevelSum(
            `1161_3`.TreeNode(989).apply {
                right = `1161_3`.TreeNode(10250).apply {
                    left = `1161_3`.TreeNode(98693)
                    right = `1161_3`.TreeNode(-89388).apply {
                        right = `1161_3`.TreeNode(-32127)
                    }
                }
            }
        )
    )
}
