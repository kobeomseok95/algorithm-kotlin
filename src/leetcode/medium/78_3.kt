package leetcode.medium

import java.util.LinkedList

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * * 1 <= nums.length <= 10
 * * -10 <= nums[i] <= 10
 * * All the numbers of nums are unique.
 *
 * subset의 의미: 순서에 상관 없는 부분집합 ([1,2]와 [2,1]은 같은 Subset)
 *
 * BFS 방식으로 접근
 * 1. Queue 에서 경우의 수 두 가지를 Snapshot 자료구조 형태로 저장
 *  - 현재 values 그대로 다음 인덱스 탐색
 *  - 현재 values + nums[index] 하여 다음 인덱스 탐색
 * 2. 탐색을 모두 마무리해서 answer 에 추가
 *
 * 시간 복잡도 : O(n * 2^n) -> 스냅샷 별 최대 리스트 크기 n * subset 모든 경우의 수 2^n
 * 공간 복잡도 : O(n * 2^n) -> 스냅샷 별 최대 리스트 크기 n * subset 모든 경우의 수 2^n
 */
class `78_3` {
    fun subsets(nums: IntArray): List<List<Int>> {
        val queue = LinkedList<Snapshot>().apply { add(Snapshot(listOf(), 0)) }
        val answer = mutableListOf<List<Int>>()

        while (queue.isNotEmpty()) {
            val snapshot = queue.poll()
            if (snapshot.nextIndex > nums.lastIndex) {
                answer.add(snapshot.values)
                continue
            }
            queue.add(
                Snapshot(
                    values = snapshot.values,
                    nextIndex = snapshot.nextIndex + 1,
                )
            )
            queue.add(
                Snapshot(
                    values = snapshot.values.plus(nums[snapshot.nextIndex]),
                    nextIndex = snapshot.nextIndex + 1,
                )
            )
        }

        return answer
    }

    private data class Snapshot(
        val values: List<Int>,
        val nextIndex: Int,
    )
}

fun main() {
    val solution = `78_3`()
    println(solution.subsets(intArrayOf(1, 2, 3)))
    println(solution.subsets(intArrayOf(0)))
}
