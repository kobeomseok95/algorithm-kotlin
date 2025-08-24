package leetcode.medium

/**
 *  연속된 지그재그 패턴 left -> right -> left... 의 최장 길이 구하기
 *
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n)
 */
class `1372` {
    fun longestZigZag(root: TreeNode?): Int {
        var answer = 0

        fun dfs(node: TreeNode?, isLeft: Boolean, length: Int) {
            if (node == null) {
                return
            }
            answer = maxOf(answer, length)
            dfs(
                node = node?.left,
                isLeft = true,
                length = if (isLeft) {
                    1
                } else {
                    length + 1
                },
            )
            dfs(
                node = node?.right,
                isLeft = false,
                length = if (isLeft) {
                    length + 1
                } else {
                    1
                }
            )
        }

        dfs(root, true, 0)
        dfs(root, false, 0)

        return answer
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
