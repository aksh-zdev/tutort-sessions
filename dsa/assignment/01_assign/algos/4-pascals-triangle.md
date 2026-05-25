# 4. Pascal's Triangle
**LeetCode 118** | Array | Dynamic Programming

## Problem
Given `numRows`, return the first `numRows` rows of Pascal's triangle.
Each element = sum of the two elements directly above it. Edges are always 1.

```
Row 0:     [1]
Row 1:    [1, 1]
Row 2:   [1, 2, 1]
Row 3:  [1, 3, 3, 1]
Row 4: [1, 4, 6, 4, 1]
```

---

## Approach 1 — Naive (Recompute Each Row from Scratch)
**Idea:** For each row, compute every element using the combinatorial formula C(row, col) = row! / (col! * (row-col)!).

```
for row from 0 to numRows-1:
    current_row = []
    for col from 0 to row:
        current_row.append( C(row, col) )
    result.append(current_row)
```

- **Time:** O(n²) but with expensive factorial computations per element
- **Space:** O(n²)
- **Why it's slow:** Factorial computations are expensive and redundant.

---

## Approach 2 — Optimized (Build Row from Previous Row)
**Idea:** Each interior element of row `r` at position `c` is `triangle[r-1][c-1] + triangle[r-1][c]`. Edges are always 1.

```
triangle = []
for row from 0 to numRows-1:
    current = [1] * (row + 1)       ← initialize with 1s
    for col from 1 to row-1:        ← fill interior elements
        current[col] = triangle[row-1][col-1] + triangle[row-1][col]
    triangle.append(current)
return triangle
```

- **Time:** O(n²) — must fill all n² / 2 cells
- **Space:** O(n²) — storing all rows (required by problem)
- **Why it's best:** Simple, no extra computation, each cell computed exactly once.

---

## Key Takeaway
Reuse previous row results (DP) instead of recomputing from formula — eliminates factorial overhead.
