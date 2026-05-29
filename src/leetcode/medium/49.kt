package leetcode.medium

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * Constraints:
 * - 1 <= strs.length <= 10^4
 * - 0 <= strs[i].length <= 100
 * - strs[i] consists of lowercase English letters.
 *
 * N: strs.length / K: strs[i].length
 * 시간 복잡도: O(N * K)
 * 공간 복잡도: O(N * K) // 문자열 크기 까지.
 */
class `49` {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val anagramGroups = mutableMapOf<String, MutableList<String>>()
        strs.forEach { str ->
            anagramGroups.getOrPut(str.createKey()) { mutableListOf() }.add(str)
        }
        return anagramGroups.map { (_, anagrams) -> anagrams }
    }

    private fun String.createKey(): String {
        val arr = IntArray(26)
        this.forEach { c -> arr[c - 'a']++ }
        val stringBuilder = StringBuilder()
        arr.forEachIndexed { index, value ->
            if (value > 0) {
                stringBuilder.append('a' + index)
                stringBuilder.append(value)
            }
        }
        return stringBuilder.toString()
    }
}

fun main() {
    val solution = `49`()
    println(solution.groupAnagrams(arrayOf("ddddddddddg","dgggggggggg"))) // [["dgggggggggg"],["ddddddddddg"]]
    println(solution.groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat"))) // [[bat],[nat,tan],[ate,eat,tea]]
    println(solution.groupAnagrams(arrayOf(""))) // [[""]]
    println(solution.groupAnagrams(arrayOf("a"))) // [[a]]
}
