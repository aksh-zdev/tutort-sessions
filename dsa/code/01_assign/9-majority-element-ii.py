# LeetCode 229 - Majority Element II
# https://leetcode.com/problems/majority-element-ii/
#
# Find all elements that appear more than n/3 times.
# At most two such elements can exist.
# Boyer-Moore Voting extended for n/3 — O(n) time, O(1) space.

from typing import List


def majorityElement(nums: List[int]) -> List[int]:
    candidate1, candidate2 = None, None
    count1, count2 = 0, 0

    for num in nums:
        if num == candidate1:
            count1 += 1
        elif num == candidate2:
            count2 += 1
        elif count1 == 0:
            candidate1, count1 = num, 1
        elif count2 == 0:
            candidate2, count2 = num, 1
        else:
            count1 -= 1
            count2 -= 1

    # verify candidates
    result = []
    for candidate in (candidate1, candidate2):
        if nums.count(candidate) > len(nums) // 3:
            result.append(candidate)

    return result


# --- test ---
print(majorityElement([3, 2, 3]))        # [3]
print(majorityElement([1]))              # [1]
print(majorityElement([1, 2]))           # [1, 2]
print(majorityElement([1, 1, 1, 3, 3, 2, 2, 2]))  # [1, 2]
