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
 * 시간 복잡도: O(n)
 * 공간 복잡도: O()
 */

/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList<Node?>()
 * }
 */

class `133` {
    fun cloneGraph(
        node: Node?,
        nodeMap: MutableMap<Int, Node> = mutableMapOf(),
    ): Node? {
        if (node == null) {
            return null
        }
        if (nodeMap[node.`val`] != null) {
            return nodeMap[node.`val`]
        }

        val copiedNode = Node(node.`val`).apply { nodeMap[this.`val`] = this }
        val copiedNeighbors = node.neighbors.mapNotNull { neighbor ->
            cloneGraph(neighbor, nodeMap)
        }
        copiedNode.neighbors = ArrayList(copiedNeighbors)

        return copiedNode
    }

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList<Node?>()
    }
}

fun main() {
    val solution = `133`()

    // Example 1: adjList = [[2,4],[1,3],[2,4],[1,3]]
    val node1 = `133`.Node(1)
    val node2 = `133`.Node(2)
    val node3 = `133`.Node(3)
    val node4 = `133`.Node(4)
    node1.neighbors = arrayListOf(node2, node4)
    node2.neighbors = arrayListOf(node1, node3)
    node3.neighbors = arrayListOf(node2, node4)
    node4.neighbors = arrayListOf(node1, node3)
    val copied1 = solution.cloneGraph(node1) // [[2,4],[1,3],[2,4],[1,3]]

    // Example 2: adjList = [[]]
    val single = `133`.Node(1)
    val copied2 = solution.cloneGraph(single) // [[]]

    // Example 3: adjList = []
    val copied3 = solution.cloneGraph(null) // []
}
