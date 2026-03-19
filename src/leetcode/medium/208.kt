package leetcode.medium

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 * - Trie() Initializes the trie object.
 * - void insert(String word) Inserts the string word into the trie.
 * - boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * - boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * Constraints:
 * - 1 <= word.length, prefix.length <= 2000
 * - word and prefix consist only of lowercase English letters.
 * - At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 *
 * N: word.length, M: insert 호출 수
 * 시간 복잡도: 하나의 함수 동작 기준으로 O(N)
 * 공간 복잡도: O(M * N)
 */
class `208` {
    private var endOf = false
    private val map = mutableMapOf<Char, `208`>()

    fun insert(word: String, index: Int = 0) {
        if (index >= word.length) {
            endOf = true
            return
        }
        map.getOrPut(word[index]) { `208`() }.insert(word, index + 1)
    }

    fun search(word: String, index: Int = 0): Boolean {
        if (index == word.length) {
            return endOf
        }
        return map[word[index]]?.search(word, index + 1) ?: false
    }

    fun startsWith(prefix: String, index: Int = 0): Boolean {
        if (prefix.length == index) {
            return true
        }
        return map[prefix[index]]?.startsWith(prefix, index + 1) ?: false
    }
}

fun main() {
    val trie = `208`()
    trie.insert("apple")
    trie.insert("add")
    trie.insert("abc")
    trie.insert("def")
    println(trie.search("apple"))   // true
    println(trie.search("add"))   // true
    println(trie.search("abc"))   // true
    println(trie.search("def"))   // true
    println(trie.search("de"))   // false
    println(trie.search("appl"))   // false
    println(trie.search("appled"))   // false
    println(trie.search("app"))     // false
    println(trie.startsWith("app")) // true
    trie.insert("app")
    println(trie.search("app"))     // true
}
