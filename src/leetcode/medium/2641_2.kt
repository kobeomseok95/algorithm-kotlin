package leetcode.medium

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
 * DFS 방식으로 접근
 * 1. 노드를 하나씩 방문하여 공통으로 관리되는 레벨 별 합을 구함
 * 2. 노드를 방문하면서 레벨 별 합과 현재 자식노드의 합을 뺀 값으로 셋팅
 *
 * 시간 복잡도 : O(2n) -> O(n) -> 모든 노드를 두 번 방문
 * 공간 복잡도 : O(h) - 재귀 스택 + depthSums 리스트 -> 완전 이진 트리: O(log n), 편향 트리(worst): O(n)
 */
class `2641_2` {
    fun replaceValueInTree(root: TreeNode?): TreeNode? {
        val depthSums = mutableListOf<Int>()
        fillDepthSumByDFS(root, depthSums)
        replaceValueByDFS(root, depthSums, root?.`val` ?: 0)
        return root
    }

    private fun fillDepthSumByDFS(
        root: TreeNode?,
        depthSums: MutableList<Int>,
        depth: Int = 0,
    ) {
        if (root == null) {
            return
        }

        val value = root.`val`
        if (depth == depthSums.size) {
            depthSums.add(0)
        }
        depthSums[depth] += value
        fillDepthSumByDFS(root = root.left, depthSums = depthSums, depth = depth + 1)
        fillDepthSumByDFS(root = root.right, depthSums = depthSums, depth = depth + 1)
    }

    private fun replaceValueByDFS(
        root: TreeNode?,
        depthSums: List<Int>,
        siblingSum: Int,
        depth: Int = 0,
    ) {
        if (root == null) {
            return
        }

        root.`val` = depthSums[depth] - siblingSum
        val nextChildrenSum = (root.left?.`val` ?: 0) + (root.right?.`val` ?: 0)
        val nextDepth = depth + 1
        replaceValueByDFS(root.left, depthSums, nextChildrenSum, nextDepth)
        replaceValueByDFS(root.right, depthSums, nextChildrenSum, nextDepth)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `2641_2`()
    println(
        solution.replaceValueInTree(
            `2641_2`.TreeNode(5).apply {
                left = `2641_2`.TreeNode(4).apply {
                    left = `2641_2`.TreeNode(1)
                    right = `2641_2`.TreeNode(10)
                }
                right = `2641_2`.TreeNode(9).apply {
                    right = `2641_2`.TreeNode(7)
                }
            }
        )
    ) // [0,0,0,7,7,null,11]
    println(
        solution.replaceValueInTree(
            `2641_2`.TreeNode(3).apply {
                left = `2641_2`.TreeNode(1)
                right = `2641_2`.TreeNode(2)
            }
        )
    ) // [0,0,0]
}
