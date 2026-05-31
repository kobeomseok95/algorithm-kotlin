package leetcode.medium

import java.util.PriorityQueue

/**
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 * Constraints:
 * - 1 <= words.length <= 500
 * - 1 <= words[i].length <= 10
 * - words[i] consists of lowercase English letters.
 * - k is in the range [1, The number of unique words[i]]
 *
 * Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 *
 * 시간 복잡도: O(n log n)
 * 공간 복잡도: O(n)
 */
class `692` {
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val keywordMap = mutableMapOf<String, Int>()
        words.forEach { word -> keywordMap[word] = keywordMap.getOrElse(word) { 0 } + 1  }
        return keywordMap.entries
            .sortedWith { a, b ->
                if (b.value - a.value == 0) {
                    a.key.compareTo(b.key)
                } else {
                    b.value - a.value
                }
            }
            .take(k)
            .map { it.key }
    }
}

fun main() {
    val solution = `692`()
    println(solution.topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 2)) // [i, love]
    println(solution.topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 3)) // [i, love, coding]
    println(solution.topKFrequent(arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"), 4)) // [the, is, sunny, day]
}
