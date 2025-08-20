package leetcode.easy

/**
 *  간선 간 최대 지름 구하기. root 가 꼭 최상단이 아닐 수 있음.
 *  연결된 노드의 최대 길이 구하기.
 *
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(log n) - 재귀 스택 (최악의 경우 O(n))
 */
class `543` {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var answer = 0

        fun dfs(root: TreeNode?): Int {
            if (root == null) {
                return 0
            }

            val left = dfs(root.left)
            val right = dfs(root.right)

            answer = maxOf(left + right, answer)
            return maxOf(left, right) + 1
        }

        dfs(root)
        return answer
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
