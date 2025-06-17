package leetcode.easy

class `1071` {
    fun gcdOfStrings(str1: String, str2: String): String {
        return getCds(str1.length, str2.length)
            .sortedBy { -it }
            .firstNotNullOfOrNull { cd ->
                str1.substring(0, cd)
                    .takeIf { isDivide(it, str1, str2) }
            } ?: ""
    }

    private fun getCds(len1: Int, len2: Int): Set<Int> {
        val len1Cds = getCds(len1)
        val len2Cds = getCds(len2)
        return len1Cds intersect len2Cds
    }

    private fun getCds(len: Int): Set<Int> {
        return (1..len).filter { len  % it == 0 }.toSet()
    }

    private fun isDivide(substr: String, str1: String, str2: String): Boolean {
        val substrLength = substr.length
        str1.forEachIndexed { index, c ->
            if (c != substr[index % substrLength]) {
                return false
            }
        }
        str2.forEachIndexed { index, c ->
            if (c != substr[index % substrLength]) {
                return false
            }
        }
        return true
    }
}
