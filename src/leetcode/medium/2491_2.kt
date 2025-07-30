package leetcode.medium

/**
 *  시간 복잡도 = O(n)
 *  공간 복잡도 = O(n)
 */
class `2491_2` {
    fun dividePlayers(skill: IntArray, memberCount: Int = 2): Long {
        val sum = skill.sum()
        val teamCount = skill.size / memberCount
        if (sum % teamCount != 0) {
            return -1L
        }
        val map = mutableMapOf<Int, Int>()
        for (s in skill) {
            map[s] = map.getOrDefault(s, 0) + 1
        }
        val base = sum / teamCount
        var answer = 0L
        for (s in skill) {
            val pair = base - s
            if (map.getOrDefault(pair, 0) == 0 && map.getOrDefault(s, 0) == 0) {
                continue
            }
            if (map.getOrDefault(pair, 0) <= 0) {
                return -1L
            }
            answer += s.toLong() * pair.toLong()
            map[s] = map[s]!! - 1
            map[pair] = map[pair]!! - 1
        }

        return answer
    }
}
