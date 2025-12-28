package leetcode.medium

import java.util.LinkedList

/**
 * Given an integer array nums,
 * return all the different possible non-decreasing subsequences of the given array with at least two elements.
 * You may return the answer in any order.
 *
 * Constraints:
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 *
 * BFS 방식으로 접근
 *
 * 1. queue.poll()은 확장할 기준 부분수열을 꺼낸다.
 * 2. 해당 부분수열에서 같은 값을 중복 선택하지 않기 위해 used를 여기서 초기화한다.
 * 3. queue 에서 뽑힌 원소의 nextIndex 를 기준으로 끝까지 탐색해서 조건에 맞는 subsequences 를 찾아 넣는다.
 *
 * 시간 복잡도 : O(n * 2^n) -> 부분수열 경우의 수 : 2^n, 스냅샷 별 최대 리스트 크기 : n
 * 공간 복잡도 : O(n * 2^n) -> 부분수열 경우의 수 : 2^n, 스냅샷 별 최대 리스트 크기 : n
 */
class `491_3` {
    fun findSubsequences(nums: IntArray): List<List<Int>> {
        var answer = mutableListOf<List<Int>>()
        val queue = LinkedList<Snapshot>().apply { add(Snapshot(listOf(), 0)) }

        while (queue.isNotEmpty()) {
            val snapshot = queue.poll()
            val used = mutableSetOf<Int>()
            (snapshot.nextIndex until nums.size).forEach { i ->
                if (snapshot.lastValue > nums[i] || nums[i] in used) {
                    return@forEach
                }
                used.add(nums[i])
                val newSnapshot = Snapshot(snapshot.values + nums[i], i + 1)
                if (newSnapshot.values.size >= MIN_SIZE) {
                    answer.add(newSnapshot.values)
                }
                queue.add(newSnapshot)
            }

        }

        return answer
    }

    private data class Snapshot(
        val values: List<Int>,
        val nextIndex: Int,
    ) {
        val lastValue: Int
            get() = values.lastOrNull() ?: DEFAULT_MIN_VALUE
    }

    companion object {
        private const val MIN_SIZE = 2
        private const val DEFAULT_MIN_VALUE = -101
    }
}

fun main() {
    val solution = `491_3`()
    println(solution.findSubsequences(intArrayOf(1,2,3,4)))
    println(solution.findSubsequences(intArrayOf(4,6,7,7)))
    println(solution.findSubsequences(intArrayOf(4,4,3,2,1)))
}
