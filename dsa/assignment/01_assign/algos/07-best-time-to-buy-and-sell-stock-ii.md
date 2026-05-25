# 7. Best Time to Buy and Sell Stock II
**LeetCode 122** | Array | Greedy

## Problem
Given `prices[]`, find the maximum profit with **unlimited** buy-sell transactions. You must sell before buying again. Can buy and sell on the same day.

---

## Approach 1 — Brute Force (Recursion / All States)
**Idea:** Try all combinations of buy/sell/hold decisions using recursion with memoization.

```
def solve(i, holding):
    if i == n: return 0
    # option 1: do nothing
    profit = solve(i+1, holding)
    if holding:
        # option 2: sell
        profit = max(profit, prices[i] + solve(i+1, False))
    else:
        # option 3: buy
        profit = max(profit, -prices[i] + solve(i+1, True))
    return profit
```

- **Time:** O(n) with memoization
- **Space:** O(n) recursion stack + memo

---

## Approach 2 — Greedy (Sum All Upward Slopes)
**Insight:** In unlimited transactions, total profit = sum of all positive day-to-day differences. Any upward move can be captured.

```
profit = 0
for i from 1 to n-1:
    if prices[i] > prices[i-1]:
        profit += prices[i] - prices[i-1]
return profit
```

- **Time:** O(n)
- **Space:** O(1)
- **Why it works:** Every consecutive increase `prices[i] - prices[i-1]` can be collected independently. Holding across multiple days = collecting each daily rise.

---

## Dry Run
```
prices = [7, 1, 5, 3, 6, 4]

i=1: 1 < 7 → skip
i=2: 5 > 1 → profit += 4  (profit=4)
i=3: 3 < 5 → skip
i=4: 6 > 3 → profit += 3  (profit=7)
i=5: 4 < 6 → skip

Answer: 7
```

---

## Key Takeaway
With unlimited transactions, greedy wins — collect every upward price move. No need for DP.
