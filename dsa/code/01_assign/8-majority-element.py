# LeetCode 169 - Majority Element
# https://leetcode.com/problems/majority-element/
#
# Find the element that appears more than n/2 times.
# Guaranteed to always exist.
# Boyer-Moore Voting Algorithm — O(n) time, O(1) space.

from typing import List


def majorityElement(nums: List[int]) -> int:
    candidate = None
    count = 0

    for num in nums:
        if count == 0:
            candidate = num
        count += 1 if num == candidate else -1

    return candidate


# --- test ---
print(majorityElement([3, 2, 3]))          # 3
print(majorityElement([2, 2, 1, 1, 1, 2, 2]))  # 2
