# Find Unique Artifact

Each artifact has a magical energy signature represented as an integer. Most artifacts appear exactly three times in the chest, but there is one unique artifact that appears exactly once. Your task is to identify this unique artifact and return its magical energy signature. You must implement a solution with a **linear runtime complexity** and use only **constant extra space**.

---

## Examples

**Example 1:**

- **Input:** `artifacts = [8, 8, 12, 8]`
- **Output:** `12`

**Example 2:**

- **Input:** `artifacts = [0, 7, 0, 7, 0, 7, 42]`
- **Output:** `42`

---

## Sample I/O

**Sample Input 1:**

```
4
8 8 12 8
```

**Sample Output 1:**

```
12
```

**Sample Input 2:**

```
7
0 7 0 7 0 7 42
```

**Sample Output 2:**

```
42
```

---

## Explanation

Implement a program that takes in a list of integers `artifacts` representing the magical energy signatures of the artifacts, and returns the magical energy signature of the unique artifact that appears exactly once.

---

## Input Format

- The first line contains an integer `n`, the number of artifacts.
- The second line contains `n` integers, the magical energy signatures of the artifacts.

## Constraints

- `1 <= artifacts.length <= 30,000`
- `-2^31 <= artifacts[i] <= 2^31 - 1`
- Each artifact in `artifacts` appears exactly three times except for one unique artifact which appears once.

## Output Format

Output a single integer, the magical energy signature of the unique artifact.
