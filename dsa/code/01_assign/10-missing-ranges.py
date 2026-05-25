# LeetCode 163 - Missing Ranges
# https://leetcode.com/problems/missing-ranges/
#
# Given a sorted unique integer array nums and a range [lower, upper],
# return the smallest sorted list of ranges that cover every missing number.

from typing import List


def findMissingRanges(nums: List[int], lower: int, upper: int) -> List[str]:
    result = []

    def format_range(lo: int, hi: int) -> str:
        return str(lo) if lo == hi else f"{lo}->{hi}"

    prev = lower - 1

    for i in range(len(nums) + 1):
        curr = nums[i] if i < len(nums) else upper + 1
        if curr - prev >= 2:
            result.append(format_range(prev + 1, curr - 1))
        prev = curr

    return result


# --- test ---
print(findMissingRanges([0, 1, 3, 50, 75], 0, 99))  # ["2", "4->49", "51->74", "76->99"]
print(findMissingRanges([-1], -1, -1))               # []
print(findMissingRanges([], 1, 1))                   # ["1"]
