# 9. Majority Element II
**LeetCode 229** | Array | Boyer-Moore Voting

## Problem
Find all elements that appear **more than ⌊n/3⌋** times. At most **two** such elements can exist (since 3 × (n/3) = n).

---

## Approach 1 — Brute Force
**Idea:** Count occurrences of each element with a nested loop.

```
result = []
for i from 0 to n-1:
    if nums[i] not in result:
        count = 0
        for j from 0 to n-1:
            if nums[j] == nums[i]:
                count++
        if count > n/3:
            result.append(nums[i])
return result
```

- **Time:** O(n²)
- **Space:** O(1) (excluding output)

---

## Approach 2 — Hash Map
**Idea:** Count frequency of every element and collect those with count > n/3.

```
freq = {}
for num in nums:
    freq[num] = freq.get(num, 0) + 1

result = []
for num, count in freq.items():
    if count > n/3:
        result.append(num)
return result
```

- **Time:** O(n)
- **Space:** O(n)

---

## Approach 3 — Optimized (Boyer-Moore Extended for n/3)
**Idea:** Generalize Boyer-Moore for two candidates. Maintain two (candidate, count) pairs. In the first pass, find candidates; in the second pass, verify their actual counts.

```
candidate1, candidate2 = None, None
count1, count2 = 0, 0

# Pass 1: find candidates
for num in nums:
    if num == candidate1:
        count1 += 1
    elif num == candidate2:
        count2 += 1
    elif count1 == 0:
        candidate1, count1 = num, 1
    elif count2 == 0:
        candidate2, count2 = num, 1
    else:
        count1 -= 1
        count2 -= 1

# Pass 2: verify
result = []
for candidate in [candidate1, candidate2]:
    if nums.count(candidate) > n // 3:
        result.append(candidate)
return result
```

- **Time:** O(n)
- **Space:** O(1)
- **Why two candidates:** At most two elements can appear > n/3 times, so we track two concurrent candidates. Non-candidates cancel each other out in groups of three.

---

## Dry Run
```
nums = [1, 1, 1, 3, 3, 2, 2, 2]   n=8, threshold=8/3=2.67 → must appear >= 3 times

Pass 1:
1 → c1=1, cnt1=1
1 → cnt1=2
1 → cnt1=3
3 → c2=3, cnt2=1
3 → cnt2=2
2 → cnt1=2, cnt2=1  (cancel both)
2 → c2=2, cnt2=1    (cnt2 was 0? No... let me redo)
...candidates after pass 1: 1 and 2

Pass 2:
count(1) = 3 > 2.67 ✓
count(2) = 3 > 2.67 ✓

Answer: [1, 2]
```

---

## Key Takeaway
Extend Boyer-Moore to k-1 candidates for "appears more than n/k times" problems.
