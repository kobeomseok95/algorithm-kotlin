package leetcode.medium

/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 * - TimeMap() Initializes the object of the data structure.
 * - void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * - String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 *
 * Constraints:
 * - 1 <= key.length, value.length <= 100
 * - key and value consist of lowercase English letters and digits.
 * - 1 <= timestamp <= 10^7
 * - All the timestamps timestamp of set are strictly increasing.
 * - At most 2 * 10^5 calls will be made to set and get.
 *
 * 시간 복잡도: set -> O(1), get -> O(log n)
 * 공간 복잡도: O(n)
 */
class `981` {
    private val store: MutableMap<String, MutableList<TimestampValue>> = mutableMapOf()

    fun set(key: String, value: String, timestamp: Int) {
        store.getOrPut(key) { mutableListOf() }
            .add(TimestampValue(timestamp, value))
    }

    fun get(key: String, timestamp: Int): String {
        return store[key]?.getLatestByTimestampOrNull(timestamp)?.value ?: ""
    }

    private fun List<TimestampValue>.getLatestByTimestampOrNull(timestamp: Int): TimestampValue? {
        if (this.isEmpty()) {
            return null
        }
        if (this[0].timestamp > timestamp) {
            return null
        }

        var left = 0
        var right = this.lastIndex
        while (left < right) {
            val sum = left + right
            val mid = if (sum % 2 == 0) {
                sum / 2
            } else {
                sum / 2 + 1
            }
            if (this[mid].timestamp > timestamp) {
                right = mid - 1
            } else {
                left = mid
            }
        }

        return this.getOrNull(right)?.takeIf { it.timestamp <= timestamp }
    }

    private data class TimestampValue(
        val timestamp: Int,
        val value: String,
    )
}

fun main() {
    val timeMap = `981`()
    timeMap.set("foo", "bar", 1)
    println(timeMap.get("foo", 1)) // "bar"
    println(timeMap.get("foo", 3)) // "bar"
    timeMap.set("foo", "bar2", 4)
    println(timeMap.get("foo", 4)) // "bar2"
    println(timeMap.get("foo", 5)) // "bar2"

    val timeMap2 = `981`()
    timeMap2.set("love", "high", 10)
    timeMap2.set("love", "low", 20)
    println(timeMap2.get("love", 5))  // ""
    println(timeMap2.get("love", 10)) // "high"
    println(timeMap2.get("love", 15)) // "high"
    println(timeMap2.get("love", 20)) // "low"
    println(timeMap2.get("love", 25)) // "low"
}
