package leetcode.medium

import java.util.LinkedList
import java.util.PriorityQueue

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
 *
 * k 번째로 큰 합을 구하는 문제이므로, 모든 레벨의 합을 저장하지 않아도 된다.
 * 우선순위 큐를 통해 k 크기 만큼의 사이즈를 제한하여 반환한다.
 *
 * 시간 복잡도
 *  - 균형 트리: O(log2n * logk) -> (큐 반복 수 * 현재 레벨에 해당하는 노드 수) + 우선순위 큐에 추가 삭제 비용
 *  - 편향 트리: O(n * logk) -> (현재 레벨에 해당하는 노드 수는 1개) + 우선순위 큐에 추가 삭제 비용
 * 공간 복잡도
 *  - 균형 트리: O(n/2 + k) -> 하단 레벨의 최대 n/2 노드 만큼 공간 필요 + k 크기 만큼의 우선순위 큐 공간
 *  - 편향 트리: O(1 + k) -> 레벨당 반드시 1개의 공간 필요 + k 크기 만큼의 우선순위 큐 공간
 */
class `2583_2` {
    fun kthLargestLevelSum(root: TreeNode?, k: Int): Long {
        val pq = PriorityQueue<Long>(k)
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
            pq.add(currentLevelSum)
            if (pq.size > k) {
                pq.poll()
            }
            currentLevelSum = 0L
        }
        return if (pq.size >= k) pq.peek() else -1
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}

fun main() {
    val solution = `2583_2`()
    println(
        solution.kthLargestLevelSum(
            root = `2583_2`.TreeNode(5).apply {
                left = `2583_2`.TreeNode(8).apply {
                    left = `2583_2`.TreeNode(2).apply {
                        left = `2583_2`.TreeNode(4)
                        right = `2583_2`.TreeNode(6)
                    }
                    right = `2583_2`.TreeNode(1).apply {}
                }
                right = `2583_2`.TreeNode(9).apply {
                    left = `2583_2`.TreeNode(3)
                    right = `2583_2`.TreeNode(7)
                }
            },
            k = 2,
        )
    ) // 13
    println(
        solution.kthLargestLevelSum(
            root = `2583_2`.TreeNode(1).apply {
                left = `2583_2`.TreeNode(2).apply {
                    left = `2583_2`.TreeNode(3)
                }
            },
            k = 1,
        )
    ) // 3
    println(
        solution.kthLargestLevelSum(
            root = `2583_2`.TreeNode(5).apply {
                left = `2583_2`.TreeNode(8).apply {
                    left = `2583_2`.TreeNode(2)
                    right = `2583_2`.TreeNode(1).apply {}
                }
                right = `2583_2`.TreeNode(9).apply {
                    left = `2583_2`.TreeNode(3)
                    right = `2583_2`.TreeNode(7)
                }
            },
            k = 4,
        )
    ) // -1
}
