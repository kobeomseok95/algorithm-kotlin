package leetcode.medium

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * an adjacency list is a collection of unordered lists used to represent a finite graph. each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 * Constraints:
 * - The number of nodes in the graph is in the range [0, 100].
 * - 1 <= Node.val <= 100
 * - Node.val is unique for each node.
 * - There are no repeated edges and no self-loops in the graph.
 * - The Graph is connected and all nodes can be visited starting from the given node.
 *
 * node 수 = N, 간선 수 = E
 * 시간 복잡도: O(N + E)
 * 공간 복잡도: O(N + E)
 */

/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList<Node?>()
 * }
 */

class `133_2` {
    fun cloneGraph(node: Node?): Node? {
        if (node == null) {
            return null
        }

        val nodeMap = mutableMapOf<Int, Node>().apply { this.copyAndAdd(node) }
        // Queue는 항상 탐색하지 않은 원본 Node 만 넣는다.
        val queue = ArrayDeque<Node>().apply { add(node) }

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            val copiedNode = nodeMap.getOrThrow(node)

            node.neighbors.filterNotNull().forEach { neighbor ->
                if (nodeMap.getOrNull(neighbor) == null) {
                    nodeMap.copyAndAdd(neighbor)
                    queue.add(neighbor)
                }
                val copiedNeighbor = nodeMap.getOrThrow(neighbor)
                copiedNode.neighbors.add(copiedNeighbor)
            }
        }

        return nodeMap[node.`val`]
    }

    private fun MutableMap<Int, Node>.getOrNull(node: Node): Node? {
        return this[node.`val`]
    }

    private fun MutableMap<Int, Node>.getOrThrow(node: Node): Node {
        return this[node.`val`] ?: throw NoSuchElementException(node.`val`.toString())
    }

    private fun MutableMap<Int, Node>.copyAndAdd(node: Node): Node {
        val copied = node.copy()
        this[copied.`val`] = copied
        return this[copied.`val`]!!
    }

    private fun Node.copy(): Node {
        return Node(this.`val`)
    }

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList<Node?>()
    }
}

fun main() {
    val solution = `133_2`()

    // Example 1: adjList = [[2,4],[1,3],[2,4],[1,3]]
    val node1 = `133_2`.Node(1)
    val node2 = `133_2`.Node(2)
    val node3 = `133_2`.Node(3)
    val node4 = `133_2`.Node(4)
    node1.neighbors = arrayListOf(node2, node4)
    node2.neighbors = arrayListOf(node1, node3)
    node3.neighbors = arrayListOf(node2, node4)
    node4.neighbors = arrayListOf(node1, node3)
    val copied1 = solution.cloneGraph(node1) // [[2,4],[1,3],[2,4],[1,3]]

    // Example 2: adjList = [[]]
    val single = `133_2`.Node(1)
    val copied2 = solution.cloneGraph(single) // [[]]

    // Example 3: adjList = []
    val copied3 = solution.cloneGraph(null) // []
}
