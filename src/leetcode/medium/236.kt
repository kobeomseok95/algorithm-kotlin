package leetcode.medium

/**
 *  p, q 포인트를 dfs 로 탐색하고, 탐색할 때 마다 본인의 발자취를 리턴한다.
 *  p, q 발자취를 비교해서 공통으로 존재하는 값을 반환한다.
 *
 *  시간 복잡도 : O(3n) => O(n)
 *  공간 복잡도 : O(n)
 */
class `236` {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val pPaths = getPath(root, p)
        val qPaths = getPath(root, q)
        val length = minOf(pPaths.size, qPaths.size)
        (length - 1 downTo 0).forEach { i ->
            if (pPaths[i] == qPaths[i]) {
                return pPaths[i]
            }
        }
        return null
    }

    private fun getPath(currentNode: TreeNode?, findNode: TreeNode?): List<TreeNode> {
        if (findNode == null) {
            return listOf()
        }
        if (currentNode == null) {
            return listOf()
        }
        if (currentNode?.`val` == findNode.`val`) {
            return listOf(currentNode)
        }
        val left = getPath(currentNode?.left, findNode)
        val right = getPath(currentNode?.right, findNode)
        return if (left.isNotEmpty()) {
            listOf(currentNode).plus(left)
        } else if (right.isNotEmpty()) {
            listOf(currentNode).plus(right)
        } else {
            listOf()
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
