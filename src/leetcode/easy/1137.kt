package leetcode.easy

class `1137` {
    fun tribonacci(n: Int): Int {
        if (n < 2) return n
        if (n == 2) return 1
        var count = n - 2
        var a = 0
        var b = 1
        var c = 1
        var answer = 0

        while (count > 0) {
            answer = a + b + c
            a = b
            b = c
            c = answer
            count -= 1
        }

        return answer
    }
}
