# 2. Two Sum II — Input Array Is Sorted
**LeetCode 167** | Array | Two Pointers

## Problem
Given a **1-indexed** sorted array `numbers`, find two numbers that add up to `target`. Return their 1-based indices. Exactly one solution exists. Must use O(1) extra space.

---

## Approach 1 — Brute Force
**Idea:** Check every pair.

```
for i from 0 to n-1:
    for j from i+1 to n-1:
        if numbers[i] + numbers[j] == target:
            return [i+1, j+1]
```

- **Time:** O(n²)
- **Space:** O(1)

---

## Approach 2 — Hash Map
**Idea:** Same as Two Sum I — store complement in a map.

```
map = {}
for i from 0 to n-1:
    complement = target - numbers[i]
    if complement in map:
        return [map[complement]+1, i+1]
    map[numbers[i]] = i
```

- **Time:** O(n)
- **Space:** O(n)
- **Drawback:** Wastes the sorted property and uses extra space.

---

## Approach 3 — Optimized (Two Pointers)
**Idea:** Use the sorted property. Start with left=0, right=n-1.
- If sum == target → found
- If sum < target → move left pointer right (need bigger number)
- If sum > target → move right pointer left (need smaller number)

```
left = 0
right = n - 1

while left < right:
    sum = numbers[left] + numbers[right]
    if sum == target:
        return [left+1, right+1]
    elif sum < target:
        left += 1
    else:
        right -= 1
```

- **Time:** O(n)
- **Space:** O(1)
- **Why it's best:** Exploits sorted order, no extra space needed.

---

## Key Takeaway
Sorted arrays unlock the two-pointer pattern — O(1) space vs O(n) for hash map.
