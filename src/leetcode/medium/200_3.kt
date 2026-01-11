package leetcode.medium

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Constraints:
 *  m == grid.length
 *  n == grid[i].length
 *  1 <= m, n <= 300
 *  grid[i][j] is '0' or '1'.
 *
 * 수직 / 수평으로 인접한 land를 탐색하여 섬의 갯수를 출력하는 문제
 *
 * 시간 복잡도 : O((m * n)^2) -> 노드 수 만큼 순회 * union find 의 find() 시 최악의 경우 시간 복잡도
 * 공간 복잡도 : O(m * n) -> union find 공간 복잡도
 */
class `200_3` {
    fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val unionFind = UnionFind(m, n)
        (0 until m).forEach { y ->
            (0 until n).forEach { x ->
                if (grid[y][x] == LAND) {
                    val current = Coordinate(y, x)
                    unionFind.addLand(current)

                    if (x + 1 < n && grid[y][x + 1] == LAND) {
                        unionFind.union(current, Coordinate(y, x + 1))
                    }
                    if (y + 1 < m && grid[y + 1][x] == LAND) {
                        unionFind.union(current, Coordinate(y + 1, x))
                    }
                }
            }
        }
        return unionFind.groupSize()
    }

    private data class Coordinate(
        val y: Int,
        val x: Int,
    )

    private class UnionFind(
        private val m: Int,
        private val n: Int,
    ) {
        private val parent = IntArray(m * n) { it }
        private val landIndices = mutableSetOf<Int>()

        private fun toIndex(y: Int, x: Int): Int {
            return y * n + x
        }

        fun addLand(c: Coordinate) {
            landIndices.add(toIndex(c.y, c.x))
        }

        fun find(index: Int): Int {
            if (parent[index] != index) {
                return find(parent[index])
            }
            return index
        }

        fun union(c1: Coordinate, c2: Coordinate) {
            val c1Index = toIndex(c1.y, c1.x)
            val c1Root = find(c1Index)
            val c2Index = toIndex(c2.y, c2.x)
            val c2Root = find(c2Index)
            landIndices.add(c1Index)
            landIndices.add(c2Index)
            if (c1Root != c2Root) {
                parent[c2Root] = c1Root
            }
        }

        fun groupSize(): Int {
            return landIndices.count { find(it) == it }
        }
    }

    companion object {
        private const val LAND = '1'
    }
}

fun main() {
    val solution = `200_3`()
    println(
        solution.numIslands(
            arrayOf(
                charArrayOf('1','1','1','1','0'),
                charArrayOf('1','1','0','1','0'),
                charArrayOf('1','1','0','0','0'),
                charArrayOf('0','0','0','0','0'),
            )
        )
    ) // 1
    println(
        solution.numIslands(
            arrayOf(
                charArrayOf('1','1','0','0','0'),
                charArrayOf('1','1','0','0','0'),
                charArrayOf('0','0','1','0','0'),
                charArrayOf('0','0','0','1','1'),
            )
        )
    ) // 3
    println(
        solution.numIslands(
            arrayOf(
                charArrayOf('1','1'),
                charArrayOf('1','1'),
            )
        )
    ) // 1
}
