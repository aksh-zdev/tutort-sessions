# 5. Pascal's Triangle II
**LeetCode 119** | Array | Dynamic Programming

## Problem
Given `rowIndex`, return only the `rowIndex`-th row (0-indexed) of Pascal's triangle using **O(rowIndex) extra space**.

---

## Approach 1 — Brute Force (Build All Rows)
**Idea:** Generate all rows up to `rowIndex` just like Pascal's Triangle I and return the last one.

```
triangle = generate all rows up to rowIndex
return triangle[rowIndex]
```

- **Time:** O(n²)
- **Space:** O(n²) — stores all rows
- **Why it fails:** Violates the O(n) space constraint.

---

## Approach 2 — Two Arrays
**Idea:** Keep only the previous row and build the current row from it.

```
prev = [1]
for row from 1 to rowIndex:
    curr = [1] * (row + 1)
    for col from 1 to row-1:
        curr[col] = prev[col-1] + prev[col]
    prev = curr
return prev
```

- **Time:** O(n²)
- **Space:** O(n) — only two rows stored at once

---

## Approach 3 — Optimized (Single Array, Update In-Place)
**Idea:** Use a single array and update it from right to left so that previous values aren't overwritten before they're used.

```
row = [1] * (rowIndex + 1)
for i from 2 to rowIndex:
    for j from i-1 down to 1:     ← right to left is critical
        row[j] += row[j-1]
return row
```

- **Time:** O(n²)
- **Space:** O(n) — single array, updated in-place
- **Why right-to-left matters:** Updating left-to-right would use already-modified values. Going right-to-left preserves the previous row's values needed for the next computation.

---

## Key Takeaway
Right-to-left in-place update is the classic trick for reducing 2-array DP to a single array.
