package leetcode.medium

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1].
 *
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
 * then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 *
 * Given an array of integers nums, find the next permutation of nums.
 * The replacement must be in place and use only constant extra memory.
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 100
 *
 * 시간 복잡도: O(N)
 * 공간 복잡도: O(1)
 */
class `31` {
    fun nextPermutation(nums: IntArray): Unit {
        var index = -1
        for (i in nums.lastIndex - 1 downTo 0) {
            if (nums[i] < nums[i + 1]) {
                index = i
                break
            }
        }
        if (index < 0) {
            nums.sort()
            return
        }
        var swapIndex = -1
        for (i in nums.lastIndex downTo index + 1) {
            if (nums[index] < nums[i]) {
                swapIndex = i
                break
            }
        }
        swap(nums, index, swapIndex)
        sort(nums, index + 1, nums.lastIndex)
    }

    private fun swap(nums: IntArray, left: Int, right: Int) {
        val temp = nums[left]
        nums[left] = nums[right]
        nums[right] = temp
    }

    private fun sort(nums: IntArray, startInclusiveIndex: Int, endInclusiveIndex: Int) {
        var left = startInclusiveIndex
        var right = endInclusiveIndex
        while (left < right) {
            swap(nums, left, right)
            left++
            right--
        }
    }
}

fun main() {
    val solution = `31`()
    val nums1 = intArrayOf(1, 2, 3)
    solution.nextPermutation(nums1)
    println(nums1.toList()) // [1, 3, 2]

    val nums2 = intArrayOf(3, 2, 1)
    solution.nextPermutation(nums2)
    println(nums2.toList()) // [1, 2, 3]

    val nums3 = intArrayOf(1, 1, 5)
    solution.nextPermutation(nums3)
    println(nums3.toList()) // [1, 5, 1]

    val nums4 = intArrayOf(1, 3, 5, 4, 2)
    solution.nextPermutation(nums4)
    println(nums4.toList()) // [1, 4, 2, 3, 5]

    val nums5 = intArrayOf(1, 2)
    solution.nextPermutation(nums5)
    println(nums5.toList()) // [2, 1]

    val nums6 = intArrayOf(1)
    solution.nextPermutation(nums6)
    println(nums6.toList()) // [1]
}
