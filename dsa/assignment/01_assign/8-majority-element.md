# 8. Majority Element
**LeetCode 169** | Array | Boyer-Moore Voting

## Problem
Find the element that appears **more than ⌊n/2⌋** times. Guaranteed to always exist.

---

## Approach 1 — Brute Force
**Idea:** For each element, count how many times it appears. Return the one with count > n/2.

```
for i from 0 to n-1:
    count = 0
    for j from 0 to n-1:
        if nums[j] == nums[i]:
            count++
    if count > n/2:
        return nums[i]
```

- **Time:** O(n²)
- **Space:** O(1)

---

## Approach 2 — Hash Map
**Idea:** Count frequency of each element using a hash map.

```
freq = {}
for num in nums:
    freq[num] = freq.get(num, 0) + 1
    if freq[num] > n/2:
        return num
```

- **Time:** O(n)
- **Space:** O(n)

---

## Approach 3 — Sort
**Idea:** If an element appears > n/2 times, after sorting it must occupy the middle index.

```
sort(nums)
return nums[n // 2]
```

- **Time:** O(n log n)
- **Space:** O(1) (in-place sort)

---

## Approach 4 — Optimized (Boyer-Moore Voting)
**Idea:** Maintain a `candidate` and a `count`. When count drops to 0, switch the candidate. Elements that cancel out non-majority votes leave the majority element standing.

```
candidate = None
count = 0

for num in nums:
    if count == 0:
        candidate = num
    if num == candidate:
        count += 1
    else:
        count -= 1

return candidate
```

- **Time:** O(n)
- **Space:** O(1)
- **Why it works:** The majority element appears > n/2 times, so it can absorb all cancellations by minority elements and still be the last candidate standing.

---

## Dry Run
```
nums = [2, 2, 1, 1, 1, 2, 2]

num=2: count=0 → candidate=2, count=1
num=2: count=2
num=1: count=1
num=1: count=0
num=1: count=0 → candidate=1, count=1
num=2: count=0
num=2: count=0 → candidate=2, count=1

Answer: 2
```

---

## Key Takeaway
Boyer-Moore Voting achieves O(n) time and O(1) space — best possible for this problem.
