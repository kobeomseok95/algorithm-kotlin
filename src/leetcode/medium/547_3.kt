package leetcode.medium

/**
 *  union find 방식
 *  시간 복잡도 : O(n^2)
 *  공간 복잡도 : O(n)
 */
class `547_3` {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        val unionFind = UnionFind(n)

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j)
                }
            }
        }

        val roots = mutableSetOf<Int>()
        for (i in 0 until n) {
            roots.add(unionFind.find(i))
        }
        return roots.size
    }
}

class UnionFind(val n: Int) {
    private val parent = IntArray(n) { it }
    private val rank = IntArray(n) { 0 }

    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)
        if (xRoot == yRoot) {
            return
        }

        when {
            rank[xRoot] < rank[yRoot] -> parent[xRoot] = yRoot
            rank[xRoot] > rank[yRoot] -> parent[yRoot] = xRoot
            else -> {
                parent[yRoot] = xRoot
                rank[xRoot]++
            }
        }
    }
}
