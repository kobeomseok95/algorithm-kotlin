package leetcode.hard

import java.util.PriorityQueue

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 * - For example, for arr = [2,3,4], the median is 3.
 * - For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data structure.
 * - double findMedian() returns the median of all elements so far. Answers within 10^-5 of the actual answer will be accepted.
 *
 * Constraints:
 * - -10^5 <= num <= 10^5
 * - There will be at least one element in the data structure before calling findMedian.
 * - At most 5 * 10^4 calls will be made to addNum and findMedian.
 *
 * Follow up:
 * - If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * - If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 *
 * 시간 복잡도:
 * 공간 복잡도:
 */
class `295` {
    private val maxHeap = PriorityQueue<Int> { a, b -> b - a }
    private val minHeap = PriorityQueue<Int> { a, b -> a - b }

    private val equalSize
        get() = minHeap.size == maxHeap.size

    fun addNum(num: Int) {
        maxHeap.offer(num)
        minHeap.offer(maxHeap.poll())
        if (maxHeap.size < minHeap.size) {
            maxHeap.offer(minHeap.poll())
        }
    }

    fun findMedian(): Double {
        return if (equalSize) {
            (maxHeap.peek() + minHeap.peek()) / 2.0
        } else {
            maxHeap.peek().toDouble()
        }
    }
}

fun main() {
    val solution = `295`()
    solution.addNum(1)
    solution.addNum(2)
    println(solution.findMedian()) // 1.5
    solution.addNum(3)
    println(solution.findMedian()) // 2.0
    println("------------------")
    val solution2 = `295`()
    solution2.addNum(4)
    solution2.addNum(4)
    println(solution2.findMedian()) // 4.0
    solution2.addNum(3)
    println(solution2.findMedian()) // 4.0
    println("------------------")
    val solution3 = `295`()
    solution3.addNum(-1)
    println(solution3.findMedian()) // -1.0
    solution3.addNum(-2)
    println(solution3.findMedian()) // -1.5
    solution3.addNum(-3)
    println(solution3.findMedian()) // -2.0
    solution3.addNum(-4)
    println(solution3.findMedian()) // -2.5
    solution3.addNum(-5)
    println(solution3.findMedian()) // -3.0
    println("------------------")
    val solution4 = `295`()
    solution4.addNum(1)
    println(solution4.findMedian()) // 1.0
    solution4.addNum(2)
    println(solution4.findMedian()) // 1.5
    solution4.addNum(3)
    println(solution4.findMedian()) // 2.0
    solution4.addNum(4)
    println(solution4.findMedian()) // 2.5
    solution4.addNum(5)
    println(solution4.findMedian()) // 3.0
}
