package leetcode.medium

/**
 *  재귀를 진행하면서
 *      - 현재 노드가 p or q 인지?
 *      - 왼쪽 서브트리 or 오른쪽 서브트리에 p or q 가 있는지?
 *  확인하여 진행한다
 *
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n)
 */
class `236_2` {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root?.`val` == null) {
            return null
        }
        if (root?.`val` == p?.`val`) {
            return p
        }
        if (root?.`val` == q?.`val`) {
            return q
        }
        val left = lowestCommonAncestor(root?.left, p, q)
        val right = lowestCommonAncestor(root?.right, p, q)
        return when {
            left == null && right != null -> right
            left != null && right == null -> left
            left != null && right != null -> root
            else -> null
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
