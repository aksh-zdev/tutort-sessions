# 1. Two Sum


**LeetCode 1** | Array | Hash Map


## Problem

Given an array `nums` and a target integer, return the indices of the two numbers that add up to `target`. Exactly one solution exists.

---


## Approach 1 — Brute Force

**Idea:** Check every pair (i, j) and see if they sum to target.

```
for i from 0 to n-1:
    for j from i+1 to n-1:
        if nums[i] + nums[j] == target:
            return [i, j]
```

- **Time:** O(n²)
- **Space:** O(1)
- **Why it's slow:** Two nested loops check all pairs.


---

## Approach 2 — Optimized (Hash Map)

**Idea:** For each number, compute its complement (`target - num`) and check if it already exists in a hash map. Store each number's index as you go.

```
map = {}
for i from 0 to n-1:
    complement = target - nums[i]
    if complement in map:
        return [map[complement], i]
    map[nums[i]] = i
```

- **Time:** O(n) — single pass
- **Space:** O(n) — hash map stores up to n entries
- **Why it's fast:** Hash map lookup is O(1), so each element is processed once.


---

## Key Takeaway

Trade space for time — hash map turns O(n²) into O(n).
