package leetcode.easy

class `392` {
    fun isSubsequence(s: String, t: String): Boolean {
        var sIndex = 0
        var tIndex = 0
        while (sIndex < s.length) {
            while (tIndex < t.length && t[tIndex] != s[sIndex]) {
                tIndex += 1
            }
            if (sIndex < s.length && tIndex >= t.length) {
                return false
            }
            sIndex += 1
            tIndex += 1
        }
        return true
    }
}
