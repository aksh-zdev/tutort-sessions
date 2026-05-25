# LeetCode 88 - Merge Sorted Array
# https://leetcode.com/problems/merge-sorted-array/
#
# Given two sorted arrays nums1 and nums2, merge nums2 into nums1 in-place.
# nums1 has length m + n where last n elements are 0 (placeholders).

from typing import List


def merge(nums1: List[int], m: int, nums2: List[int], n: int) -> None:
    i = m - 1      # pointer for nums1 valid elements
    j = n - 1      # pointer for nums2
    k = m + n - 1  # pointer for merged position

    while i >= 0 and j >= 0:
        if nums1[i] > nums2[j]:
            nums1[k] = nums1[i]
            i -= 1
        else:
            nums1[k] = nums2[j]
            j -= 1
        k -= 1

    # copy remaining nums2 elements if any
    while j >= 0:
        nums1[k] = nums2[j]
        j -= 1
        k -= 1


# --- test ---
nums1 = [1, 2, 3, 0, 0, 0]
merge(nums1, 3, [2, 5, 6], 3)
print(nums1)  # [1, 2, 2, 3, 5, 6]

nums1 = [1]
merge(nums1, 1, [], 0)
print(nums1)  # [1]

nums1 = [0]
merge(nums1, 0, [1], 1)
print(nums1)  # [1]
