# 6. Best Time to Buy and Sell Stock
**LeetCode 121** | Array | Greedy

## Problem
Given `prices[]` where `prices[i]` is the stock price on day `i`, find the maximum profit from **one** buy-sell transaction. Return 0 if no profit is possible. Must buy before selling.

---

## Approach 1 — Brute Force
**Idea:** Try every pair (i, j) where i < j and track the maximum profit.

```
max_profit = 0
for i from 0 to n-2:
    for j from i+1 to n-1:
        profit = prices[j] - prices[i]
        max_profit = max(max_profit, profit)
return max_profit
```

- **Time:** O(n²)
- **Space:** O(1)
- **Why it's slow:** Redundantly checks all pairs.

---

## Approach 2 — Optimized (Track Minimum Price)
**Idea:** Scan left to right. At each day, compute profit if you sold today (today's price − minimum price seen so far). Track the global maximum profit.

```
min_price = infinity
max_profit = 0

for price in prices:
    if price < min_price:
        min_price = price          ← update cheapest buy day
    elif price - min_price > max_profit:
        max_profit = price - min_price   ← update best profit
return max_profit
```

- **Time:** O(n) — single pass
- **Space:** O(1)
- **Why it works:** The minimum buy price up to day `i` is always tracked, so checking profit at each sell day is O(1).

---

## Dry Run
```
prices = [7, 1, 5, 3, 6, 4]

Day 0: price=7  min=7  profit=0
Day 1: price=1  min=1  profit=0
Day 2: price=5  min=1  profit=4
Day 3: price=3  min=1  profit=4
Day 4: price=6  min=1  profit=5  ← max
Day 5: price=4  min=1  profit=5

Answer: 5
```

---

## Key Takeaway
Track the running minimum buy price — no need to look back, making O(n²) into O(n).
