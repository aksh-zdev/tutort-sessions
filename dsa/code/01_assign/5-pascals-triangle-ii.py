# LeetCode 119 - Pascal's Triangle II
# https://leetcode.com/problems/pascals-triangle-ii/
#
# Given rowIndex, return the rowIndex-th (0-indexed) row of Pascal's triangle.
# Must use O(rowIndex) extra space.

from typing import List


def getRow(rowIndex: int) -> List[int]:
    row = [1] * (rowIndex + 1)

    for i in range(2, rowIndex + 1):
        for j in range(i - 1, 0, -1):
            row[j] += row[j - 1]

    return row


# --- test ---
print(getRow(3))  # [1, 3, 3, 1]
print(getRow(0))  # [1]
print(getRow(1))  # [1, 1]
