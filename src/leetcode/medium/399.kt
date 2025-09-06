package leetcode.medium

import java.util.LinkedList

/**
 * eq[i]][0] / eq[i]][1] = values[i]
 *
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(n)
 */
class `399` {
    fun calcEquation(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>,
    ): DoubleArray {
        val map = createMap(equations, values)
        return queries.map { query ->
            map.getValue(query[0], query[1])
        }.toDoubleArray()
    }

    private fun createMap(
        equations: List<List<String>>,
        values: DoubleArray,
    ): Map<String, Set<Equation>> {
        val map = mutableMapOf<String, MutableSet<Equation>>()
        equations.forEachIndexed { index, equation ->
            val from = equation[0]
            val to = equation[1]
            val value = values[index]
            val reciprocal = 1.0 / value
            map[from] = map.getOrDefault(from, mutableSetOf()).apply { add(Equation(to, value)) }
            map[to] = map.getOrDefault(to, mutableSetOf()).apply { add(Equation(from, reciprocal)) }
        }
        return map
    }

    private fun Map<String, Set<Equation>>.getValue(
        from: String,
        to: String,
    ): Double {
        if (from !in this || to !in this) {
            return NOT_FOUND
        }
        if (from == to) {
            return EQUALS
        }
        val visited = this.keys.associateWith { false }.toMutableMap()
        val queue = LinkedList<Equation>()
            .apply { offer(Equation(from, 1.0)) }
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current.point == to) {
                return current.value
            }
            visited[current.point] = true
            this[current.point].orEmpty().forEach { equation ->
                if (visited[equation.point] != true) {
                    queue.offer(
                        Equation(
                            point = equation.point,
                            value = equation.value * current.value,
                        )
                    )
                }
            }
        }
        return NOT_FOUND
    }

    data class Equation(
        val point: String,
        val value: Double,
    )

    companion object {
        const val NOT_FOUND = -1.0
        const val EQUALS = 1.0
    }
}
