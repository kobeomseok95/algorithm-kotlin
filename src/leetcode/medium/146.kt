package leetcode.medium

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 * - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Constraints:
 * - 1 <= capacity <= 3000
 * - 0 <= key <= 10^4
 * - 0 <= value <= 10^5
 * - At most 2 * 10^5 calls will be made to get and put.
 *
 * K: 중복을 제외한 key 갯수
 * 시간 복잡도: O(1)
 * 공간 복잡도: O(K)
 */
class `146`(capacity: Int) {
    private val maxCapacity = capacity
    private val map = LinkedHashMap<Int, Int>(capacity, 1.0f, true)

    fun get(key: Int): Int {
        return map[key] ?: -1
    }

    fun put(key: Int, value: Int) {
        map[key] = value
        if (map.size > maxCapacity) {
            map.remove(map.keys.first())
        }
    }
}

fun main() {
    val lRUCache = `146`(2)
    lRUCache.put(1, 1) // cache is {1=1}
    lRUCache.put(2, 2) // cache is {1=1, 2=2}
    println(lRUCache.get(1))    // return 1
    lRUCache.put(3, 3) // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    println(lRUCache.get(2))    // returns -1 (not found)
    lRUCache.put(4, 4) // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    println(lRUCache.get(1))    // return -1 (not found)
    println(lRUCache.get(3))    // return 3
    println(lRUCache.get(4))    // return 4

    val lRUCache2 = `146`(2)
    lRUCache2.put(1, 1)
    lRUCache2.put(1, 2)
    println(lRUCache2.get(1))    // 2
    lRUCache2.put(1, 3)
    println(lRUCache2.get(2))    // -1
    lRUCache2.put(1, 4)
    println(lRUCache2.get(1))    // 4
    lRUCache2.put(1, 5)
    println(lRUCache2.get(1))    // 5

    // 반례: capacity=2, 같은 key 갱신 후 새 key 삽입 시 evict가 잘못되는 케이스
    val lRUCache3 = `146`(2)
    println(lRUCache3.get(2))    // -1
    lRUCache3.put(2, 6)
    println(lRUCache3.get(1))    // -1
    lRUCache3.put(1, 5)
    lRUCache3.put(1, 2)
    println(lRUCache3.get(1))    // 2
    println(lRUCache3.get(2))    // 6  ← 현재 코드는 -1 을 반환 (버그)
}
