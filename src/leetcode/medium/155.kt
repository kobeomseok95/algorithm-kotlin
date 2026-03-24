package leetcode.medium

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 *
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Constraints:
 * - -2^31 <= val <= 2^31 - 1
 * - Methods pop, top and getMin operations will always be called on non-empty stacks.
 * - At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 *
 * N: push 연산 수
 * 시간 복잡도: O(1)
 * 공간 복잡도: O(N)
 */
class `155` {
    private val list = mutableListOf<Int>()
    private val minValues = mutableListOf<Int>()

    fun push(`val`: Int) {
        list.add(`val`)
        minValues.add(minOf((minValues.lastOrNull() ?: Int.MAX_VALUE), `val`))
    }

    fun pop() {
        list.removeAt(list.lastIndex)
        minValues.removeAt(minValues.lastIndex)
    }

    fun top(): Int {
        return list.last()
    }

    fun getMin(): Int {
        return minValues.last()
    }
}

fun main() {
    val minStack = `155`()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    println(minStack.getMin()) // -3
    minStack.pop()
    println(minStack.top()) // 0
    println(minStack.getMin()) // -2
}
