package leetcode.hard

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection
 * link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 * your serialization/deserialization algorithm should work. You just need to ensure that a binary
 * tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 10^4].
 * - -1000 <= Node.val <= 1000
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(N)
 */

class `297` {
    fun serialize(root: TreeNode?): String {
        val queue = ArrayDeque<TreeNode?>()
        root?.let { queue.add(it) }
        val list = mutableListOf<String>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            if (queue.all { it == null }) {
                break
            }
            (0 until size).forEach { _ ->
                val current = queue.removeFirstOrNull()
                current?.let {
                    list.add(it.`val`.toString())
                    queue.add(it.left)
                    queue.add(it.right)
                } ?: list.add(NULL)
            }
        }
        return OPEN + list.joinToString(SEPARATOR) + CLOSE
    }

    fun deserialize(data: String): TreeNode? {
        if (data == "[]") {
            return null
        }
        val tokens = data
            .removeSurrounding(OPEN, CLOSE)
            .split(SEPARATOR)
        val root = TreeNode(tokens[0].toInt())
        var index = 1
        val queue = ArrayDeque<TreeNode?>().apply { add(root) }
        while (queue.isNotEmpty() && index < tokens.size) {
            val size = queue.size
            (0 until size).forEach { _ ->
                val currentNode = queue.removeFirstOrNull()
                currentNode?.left = tokens[index++].toIntOrNull()?.let { TreeNode(it) }
                currentNode?.left?.let { queue.add(it) }
                currentNode?.right = tokens[index++].toIntOrNull()?.let { TreeNode(it) }
                currentNode?.right?.let { queue.add(it) }
            }
        }
        return root
    }

    companion object {
        private const val OPEN = "["
        private const val CLOSE = "]"
        private const val SEPARATOR = ","
        private const val NULL = "null"
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

}

fun main() {
    val codec = `297`()

    // Example 1: root = [1,2,3,null,null,4,5]
    val root1 = `297`.TreeNode(1).apply {
        left = `297`.TreeNode(2)
        right = `297`.TreeNode(3).apply {
            left = `297`.TreeNode(4)
            right = `297`.TreeNode(5)
        }
    }
    println(codec.serialize(root1)) // [1,2,3,null,null,4,5]
    println(codec.deserialize(codec.serialize(root1))) // [1,2,3,null,null,4,5]

    // Example 2: root = []
    println(codec.serialize(null)) // []
    println(codec.deserialize(codec.serialize(null))) // []

    // Example 3: root = [1,2,3,null,null,4,5,6,7]
    val root3 = `297`.TreeNode(1).apply {
        left = `297`.TreeNode(2)
        right = `297`.TreeNode(3).apply {
            left = `297`.TreeNode(4).apply {
                left = `297`.TreeNode(6)
                right = `297`.TreeNode(7)
            }
            right = `297`.TreeNode(5)
        }
    }
    println(codec.serialize(root3)) // [1,2,3,null,null,4,5,6,7]
    println(codec.deserialize(codec.serialize(root3))) // [1,2,3,null,null,4,5,6,7]
}
