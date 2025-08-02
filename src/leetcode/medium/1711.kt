package leetcode.medium

/**
 *  시간 복잡도 : O(n^2)
 *  공간 복잡도 : O(1)
 */
class `1711` {
    fun countPairs(deliciousness: IntArray, combination: Int = 2): Int {
        val mod = 1_000_000_007
        var answer = 0
        for (i in deliciousness.indices) {
            for (j in i + 1 until deliciousness.size) {
                if (isTwoPower(deliciousness[i] + deliciousness[j])) {
                    answer += 1
                }
            }
        }
        return answer % mod
    }

    private fun isTwoPower(n: Int): Boolean {
        return n > 0 && (n and (n - 1)) == 0
    }
}
