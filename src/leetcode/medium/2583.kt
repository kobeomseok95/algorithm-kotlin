package leetcode.medium

import java.util.LinkedList

/**
 * You are given the root of a binary tree and a positive integer k.
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 * Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
 * Note that two nodes are on the same level if they have the same distance from the root.
 *
 * Constraints:
 *  The number of nodes in the tree is n.
 *  2 <= n <= 10^5
 *  1 <= Node.val <= 10^6
 *  1 <= k <= n
 *
 * 트리 레벨 합에서 k번째로 큰 레벨의 총합을 구하는 문제
 * BFS 로 접근하여 레벨별 합을 구한다.
 *
 * 시간 복잡도 : O(nlogn) -> 균형 트리 / 편향 트리를 고려했을 때 노드 수 만큼 반복하기 때문에 O(n) + 정렬 비용
 * 공간 복잡도 : O(n) -> 균형 트리 / 편향 트리 상황에 따라 queue 와 sumOfLevels 가 반비례적으로 늘어나기 때문에 O(n)
 *   e.g. 균형 트리 -> sumOfLevels 공간 복잡도는 O(logn) / queue 공간 복잡도는 O(n)
 *        편향 트리 -> sumOfLevels 공간 복잡도는 O(n) / queue 공간 복잡도는 O(1)
 */
class `2583` {
    fun kthLargestLevelSum(root: TreeNode?, k: Int): Long {
        val sumOfLevels = mutableListOf<Long>()
        val index = k - 1
        val queue = LinkedList<TreeNode>().apply { root?.let { add(it) } }
        var currentLevelSum = 0L
        while (queue.isNotEmpty()) {
            val currentLevelWidth = queue.size
            repeat(currentLevelWidth) {
                val node = queue.poll()
                currentLevelSum += node.`val`
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            sumOfLevels.add(currentLevelSum)
            currentLevelSum = 0L
        }
        sumOfLevels.sortByDescending { it }
        return sumOfLevels.getOrNull(index) ?: -1
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `2583`()
    println(
        solution.kthLargestLevelSum(
            root = `2583`.TreeNode(5).apply {
                left = `2583`.TreeNode(8).apply {
                    left = `2583`.TreeNode(2).apply {
                        left = `2583`.TreeNode(4)
                        right = `2583`.TreeNode(6)
                    }
                    right = `2583`.TreeNode(1).apply {}
                }
                right = `2583`.TreeNode(9).apply {
                    left = `2583`.TreeNode(3)
                    right = `2583`.TreeNode(7)
                }
            },
            k = 2,
        )
    ) // 13
    println(
        solution.kthLargestLevelSum(
            root = `2583`.TreeNode(1).apply {
                left = `2583`.TreeNode(2).apply {
                    left = `2583`.TreeNode(3)
                }
            },
            k = 1,
        )
    ) // 3
    println(
        solution.kthLargestLevelSum(
            root = `2583`.TreeNode(5).apply {
                left = `2583`.TreeNode(8).apply {
                    left = `2583`.TreeNode(2)
                    right = `2583`.TreeNode(1).apply {}
                }
                right = `2583`.TreeNode(9).apply {
                    left = `2583`.TreeNode(3)
                    right = `2583`.TreeNode(7)
                }
            },
            k = 4,
        )
    ) // -1
}
