package leetcode.medium

/**
 *  시간 복잡도 = 정렬: O(n log n) + 투 포인터 순서: O(n) = O(n log n)
 *  공간 복잡도 = O(1)
 */
class `2491` {
    fun dividePlayers(skill: IntArray): Long {
        skill.sort()
        var first = 0
        var last = skill.size - 1
        var answer = 0L
        var base = skill[first] + skill[last]
        while (first < last) {
            val current = skill[first] + skill[last]
            if (base != current) {
                return -1
            }
            answer += skill[first] * skill[last]
            first += 1
            last -= 1
        }
        return answer
    }
}
