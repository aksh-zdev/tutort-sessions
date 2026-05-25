# 3. Merge Sorted Array

**LeetCode 88** | Array | Two Pointers

## Problem

Given two sorted arrays `nums1` (size m+n, last n slots are 0) and `nums2` (size n), merge `nums2` into `nums1` in-place in sorted order.

---

## Approach 1 — Brute Force (Copy + Sort)

**Idea:** Copy nums2 into the extra space in nums1, then sort nums1.

```pseudocode
copy nums2 into nums1[m ... m+n-1]
sort nums1
```

- **Time:** O((m+n) log(m+n))
- **Space:** O(1) extra (sort in-place)
- **Why it's slow:** Doesn't use the fact that both arrays are already sorted.

---

## Approach 2 — Extra Array

**Idea:** Merge both arrays into a temporary array using two pointers, then copy back.

```pseudocode
temp = []
i = 0, j = 0
while i < m and j < n:
    if nums1[i] <= nums2[j]: append nums1[i], i++
    else: append nums2[j], j++
append remaining elements
copy temp into nums1
```

- **Time:** O(m+n)
- **Space:** O(m+n) — extra array

---

## Approach 3 — Optimized (Merge from End)

**Idea:** Fill nums1 from the back. Compare largest elements of both arrays and place the bigger one at the current tail position. This avoids overwriting unprocessed nums1 elements.

```pseudocode
i = m - 1       ← last valid index in nums1
j = n - 1       ← last index in nums2
k = m + n - 1   ← last index overall

while i >= 0 and j >= 0:
    if nums1[i] > nums2[j]:
        nums1[k] = nums1[i]; i--
    else:
        nums1[k] = nums2[j]; j--
    k--

while j >= 0:   ← copy remaining nums2
    nums1[k] = nums2[j]; j--; k--
```

- **Time:** O(m+n)
- **Space:** O(1)
- **Why it's best:** In-place, no extra array, single pass.

---

## Key Takeaway

Merging from the end avoids overwriting valid data — turns an O(m+n) space solution into O(1) space.
