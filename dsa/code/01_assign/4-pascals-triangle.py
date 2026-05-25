# LeetCode 118 - Pascal's Triangle
# https://leetcode.com/problems/pascals-triangle/
#
# Given numRows, return the first numRows of Pascal's triangle.

from typing import List


def generate(numRows: int) -> List[List[int]]:
    triangle = []

    for row in range(numRows):
        current = [1] * (row + 1)
        for col in range(1, row):
            current[col] = triangle[row - 1][col - 1] + triangle[row - 1][col]
        triangle.append(current)

    return triangle


# --- test ---
print(generate(5))  # [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
print(generate(1))  # [[1]]
