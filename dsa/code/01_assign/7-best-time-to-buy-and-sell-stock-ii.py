# LeetCode 122 - Best Time to Buy and Sell Stock II
# https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
#
# You can buy and sell on multiple days (but must sell before buying again).
# Return the maximum total profit.

from typing import List


def maxProfit(prices: List[int]) -> int:
    profit = 0

    for i in range(1, len(prices)):
        if prices[i] > prices[i - 1]:
            profit += prices[i] - prices[i - 1]

    return profit


# --- test ---
print(maxProfit([7, 1, 5, 3, 6, 4]))  # 7
print(maxProfit([1, 2, 3, 4, 5]))     # 4
print(maxProfit([7, 6, 4, 3, 1]))     # 0
