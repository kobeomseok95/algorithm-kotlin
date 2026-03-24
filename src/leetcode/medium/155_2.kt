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
class `155_2` {
    private var head: Node? = null
    private var minHead: Node? = null
    private data class Node(val value: Int, val next: Node?)

    fun push(`val`: Int) {
        head = Node(`val`, head)
        minHead = Node(minOf((minHead?.value ?: Int.MAX_VALUE), `val`), minHead)
    }

    fun pop() {
        head = head?.next
        minHead = minHead?.next
    }

    fun top(): Int {
        return head?.value ?: throw IllegalStateException()
    }

    fun getMin(): Int {
        return minHead?.value ?: throw IllegalStateException()
    }
}

fun main() {
    val minStack = `155_2`()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    println(minStack.getMin()) // -3
    minStack.pop()
    println(minStack.top()) // 0
    println(minStack.getMin()) // -2

    val stack2 = `155_2`()
    stack2.push(0)
    stack2.push(1)
    stack2.push(0)
    println(stack2.getMin()) // 0
    stack2.pop()
    println(stack2.getMin()) // 0
    stack2.pop()
    println(stack2.getMin()) // 0
    stack2.pop()
    stack2.push(-2)
    stack2.push(-1)
    stack2.push(-2)
    println(stack2.getMin()) // -2
    stack2.pop()
    println(stack2.top()) // -1
    println(stack2.getMin()) // -2
    stack2.pop()
    println(stack2.getMin()) // -2
    stack2.pop()
}
