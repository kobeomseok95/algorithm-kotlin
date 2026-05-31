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
 * 시간 복잡도: O(N log N)
 * 공간 복잡도: O(N)
 */
class `692_2` {
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val keywordMap = mutableMapOf<String, Int>()
        words.forEach { word -> keywordMap[word] = keywordMap.getOrDefault(word, 0) + 1 }
        val priorityQueue = PriorityQueue<WordCount> { a, b ->
            if (a.count == b.count) {
                a.word.compareTo(b.word)
            } else {
                b.count - a.count
            }
        }

        keywordMap.forEach { (key, value) -> priorityQueue.add(WordCount(key, value)) }
        val topKFrequent = mutableListOf<String>()
        for (i in 0 until k) {
            topKFrequent.add(priorityQueue.poll().word)
        }
        return topKFrequent
    }

    private data class WordCount(val word: String, val count: Int)
}

fun main() {
    val solution = `692_2`()
    println(solution.topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 2)) // [i, love]
    println(solution.topKFrequent(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 3)) // [i, love, coding]
    println(solution.topKFrequent(arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"), 4)) // [the, is, sunny, day]
}
