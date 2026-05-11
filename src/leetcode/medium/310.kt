package leetcode.medium

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h)) are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Constraints:
 * - 1 <= n <= 2 * 10^4
 * - edges.length == n - 1
 * - 0 <= ai, bi < n
 * - ai != bi
 * - All the pairs (ai, bi) are distinct.
 * - The given input is guaranteed to be a tree and there will be no repeated edges.
 *
 * 시간 복잡도: O(n)
 * 공간 복잡도: O(n)
 */
class `310` {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n == 1) {
            return listOf(0)
        }
        val degrees = IntArray(n) {0}
        val graph = Array(n) { mutableListOf<Int>() }
        edges.forEach { (from, to) ->
            graph[from].add(to)
            graph[to].add(from)

            degrees[from]++
            degrees[to]++
        }

        val leaves = ArrayDeque<Int>()
        degrees.forEachIndexed { leaf, degree ->
            if (degree == 1) {
                leaves.add(leaf)
            }
        }
        var remainingLeaves = n
        while (remainingLeaves > 2) {
            val size = leaves.size
            repeat(size) {
                val leaf = leaves.removeFirst()
                graph[leaf].forEach { next ->
                    degrees[next]--
                    if (degrees[next] == 1) {
                        leaves.add(next)
                    }
                }
            }
            remainingLeaves -= size
        }
        return leaves.toList()
    }
}

fun main() {
    val solution = `310`()
    println(solution.findMinHeightTrees(1, arrayOf())) // [0]
    println(solution.findMinHeightTrees(4, arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(1,3)))) // [1]
    println(solution.findMinHeightTrees(6, arrayOf(intArrayOf(3,0), intArrayOf(3,1), intArrayOf(3,2), intArrayOf(3,4), intArrayOf(5,4)))) // [3,4]
    println(solution.findMinHeightTrees(7, arrayOf(intArrayOf(0,1), intArrayOf(1,2), intArrayOf(1,3), intArrayOf(2,4), intArrayOf(3,5), intArrayOf(4,6)))) // [1,2]
}
