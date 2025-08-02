package leetcode.medium

/**
 *  시간 복잡도 : O(n)
 *  공간 복잡도 : O(n)
 */
class `1711_2` {
    fun countPairs(deliciousness: IntArray): Int {
        val mod = 1_000_000_007
        var answer = 0
        val map = mutableMapOf<Int, Int>()
        deliciousness.forEach { d ->
            var power = 1
            (0 until 22).forEach { _ ->
                if (map.contains(power - d)) {
                    answer += map[power - d]!!
                    answer %= mod
                }
                power *= 2
            }
            map[d] = map.getOrDefault(d, 0) + 1
        }
        return answer % mod
    }
}
