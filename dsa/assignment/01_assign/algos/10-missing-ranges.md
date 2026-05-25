# 10. Missing Ranges
**LeetCode 163** | Array | Linear Scan

## Problem
Given a sorted unique integer array `nums` and a range `[lower, upper]`, return the shortest list of ranges that covers every missing number in `[lower, upper]`.

- A range `[a, b]` is formatted as `"a"` if `a == b`, else `"a->b"`.

**Example:**
```
nums = [0, 1, 3, 50, 75], lower = 0, upper = 99
Output: ["2", "4->49", "51->74", "76->99"]
```

---

## Approach 1 — Brute Force
**Idea:** Iterate through every integer from `lower` to `upper`. Collect all missing ones and format into ranges.

```
missing = []
for x from lower to upper:
    if x not in nums (use a set):
        missing.append(x)

result = []
i = 0
while i < len(missing):
    start = missing[i]
    while i+1 < len(missing) and missing[i+1] == missing[i]+1:
        i++
    end = missing[i]
    result.append(format(start, end))
    i++
return result
```

- **Time:** O(upper - lower) — can be huge
- **Space:** O(upper - lower)
- **Why it's bad:** Range can be up to 2³¹ wide.

---

## Approach 2 — Optimized (Gap Detection, Single Pass)
**Idea:** Instead of iterating over all integers, directly compute gaps between consecutive "boundaries": `lower-1`, `nums[0]`, `nums[1]`, ..., `nums[n-1]`, `upper+1`.

A gap exists between `prev+1` and `curr-1` whenever `curr - prev >= 2`.

```
result = []
prev = lower - 1

for i from 0 to n (inclusive):
    curr = nums[i] if i < n else upper + 1

    if curr - prev >= 2:              ← gap exists
        result.append(format(prev+1, curr-1))

    prev = curr

return result

format(lo, hi):
    return str(lo) if lo == hi else f"{lo}->{hi}"
```

- **Time:** O(n) — single pass through nums
- **Space:** O(1) extra (excluding output)
- **Why it works:** Treating `lower-1` and `upper+1` as virtual sentinels unifies edge cases (missing at start/end) with the general case.

---

## Dry Run
```
nums=[0,1,3,50,75], lower=0, upper=99
prev = -1

i=0: curr=0,  gap: 0-(-1)=1 → no gap,  prev=0
i=1: curr=1,  gap: 1-0=1    → no gap,  prev=1
i=2: curr=3,  gap: 3-1=2    → add "2", prev=3
i=3: curr=50, gap: 50-3=47  → add "4->49", prev=50
i=4: curr=75, gap: 75-50=25 → add "51->74", prev=75
i=5: curr=100,gap: 100-75=25→ add "76->99", prev=100

Output: ["2", "4->49", "51->74", "76->99"]
```

---

## Key Takeaway
Sentinel values (`lower-1` and `upper+1`) eliminate special-case handling for array edges.
