# Data Structures

A data structure is a way of organizing and storing data so that it can be accessed and modified efficiently.

"Think of it as choosing the right container for your data based on how you'll use it."

## Why Does Organizing Data Improve Efficiency?

Unorganized data requires scanning everything to find what you need (like searching a pile of unsorted papers). Organized data allows shortcuts:

- A sorted array enables binary search → O(log n) instead of O(n)
- A hash table enables direct lookup by key → O(1) instead of O(n)
- A tree keeps data hierarchically sorted → no need to scan all elements

> The better the organization, the fewer steps needed to access, insert, or delete data.

## Why Is Software Performance Connected to Data Organization?

Every operation in software (search, insert, update, delete) depends on how data is stored:

- Wrong structure → unnecessary iterations, wasted memory, slow responses
- Right structure → minimal operations, optimal memory, fast execution

Example: Searching 1 million users

- Unsorted array → scan all 1M entries (O(n))
- Hash table → direct jump to the result (O(1))
- BST → halve the search space each step (O(log n))

> Algorithm + Data Structure = Program. You can't have a fast program with slow data access.

## Real-Life Examples of Data Structures

| Data Structure | Real-Life Analogy                                   |
| -------------- | ----------------------------------------------      |
| Array          | Row of numbered lockers                             |
| Linked List    | Train cars connected in sequence                    |
| Stack          | Stack of plates (take from top)                     |
| Queue          | Line at a ticket counter (first come, first served) |
| Hash Table     | Dictionary (look up word → get meaning)             |
| Tree           | Company org chart (CEO → managers → employees)      |
| Graph          | Road map (cities connected by roads)                |
| Heap           | Hospital ER (highest priority patient first)        |

## Why Is Choosing the Correct Data Structure Important?

Choosing wrong leads to:

- Slow operations (O(n) where O(1) was possible)
- Wasted memory (storing sparse data in a full matrix)
- Complex code (fighting the structure instead of leveraging it)

Choosing right gives you:

- Performance that scales with data size
- Simpler, more readable code
- Lower memory footprint

> Example: Need frequent min/max? Use a Heap (O(1) access). Using an array instead means O(n) every time — 1000x slower at scale.

## Why Data Structures Matter

- Efficient data access and modification (faster data processing)
- Optimized memory usage
- Better algorithm performance
- Cleaner, more maintainable code

## Classification of Data Structures

    Data Structures
    ├── Linear
    │   ├── Array
    │   ├── Linked List
    │   ├── Stack
    │   ├── Queue
    │   └── Hash Table
    └── Non-Linear
        ├── Tree
        ├── Graph
        └── Heap

## Linear Data Structures

### 1. Array

- Fixed-size, contiguous memory allocation
- Index-based access → O(1)
- Insertion/Deletion → O(n)
- Use when: size is known and random access is needed

### 2. Linked List

- Dynamic size, nodes connected via pointers
- Access → O(n)
- Insertion/Deletion at head → O(1), at arbitrary position → O(n)
- Use when: frequent insertions/deletions at ends, unknown size

### 3. Stack (LIFO — Last In, First Out)

- Operations: push, pop, peek
- All operations → O(1)
- Use when: undo operations, expression evaluation, recursion

### 4. Queue (FIFO — First In, First Out)

- Operations: enqueue, dequeue, front
- All operations → O(1)
- Use when: scheduling, BFS, buffering

### 5. Hash Table (Dictionary / Map)

- Key-value pair storage
- Average access → O(1)
- Worst case (collisions) → O(n)
- Use when: fast lookup by key, counting frequencies

## Non-Linear Data Structures

### 6. Tree

- Hierarchical structure with parent-child relationships
- Binary Tree: each node has at most 2 children
- Binary Search Tree (BST): left < root < right
- Search/Insert/Delete → O(log n) average (balanced), O(n) worst (skewed)
- Use when: hierarchical data, sorted data, searching

### 7. Graph

- Nodes (vertices) connected by edges
- Types: directed, undirected, weighted, unweighted
- Representations: adjacency matrix, adjacency list
- Use when: networks, paths, relationships

### 8. Heap

- Complete binary tree with heap property
- Min-Heap: parent ≤ children
- Max-Heap: parent ≥ children
- Access root (min/max) → O(1), arbitrary access → not supported
- Insert/Delete → O(log n)
- Use when: priority queues, finding min/max efficiently

## Time Complexity Comparison

| Data Structure | Access     | Search     | Insert     | Delete     |
| -------------- | ---------- | ---------- | ---------- | ---------- |
| Array          | O(1)       | O(n)       | O(n)       | O(n)       |
| Linked List    | O(n)       | O(n)       | O(1)¹      | O(1)¹      |
| Stack          | O(n)       | O(n)       | O(1)       | O(1)       |
| Queue          | O(n)       | O(n)       | O(1)       | O(1)       |
| Hash Table     | —          | O(1)²      | O(1)²      | O(1)²      |
| BST            | O(log n)³  | O(log n)³  | O(log n)³  | O(log n)³  |
| Heap           | O(1)⁴      | O(n)       | O(log n)   | O(log n)   |

**Footnotes:**

1. ¹ Linked List O(1) insert/delete is only at head (or tail with tail pointer). At arbitrary position → O(n) to traverse + O(1) to modify.
2. ² Hash Table O(1) is average case. Worst case (many collisions) → O(n).
3. ³ BST O(log n) is average for a balanced tree. Worst case (skewed/unbalanced) → O(n).
4. ⁴ Heap Access O(1) is only for the root (min or max). Arbitrary element access is not supported.

## How to Choose a Data Structure

| Need                            | Use              |
| ------------------------------- | ---------------- |
| Fast access by index            | Array            |
| Frequent insert/delete          | Linked List      |
| Last-in, first-out behavior     | Stack            |
| First-in, first-out behavior    | Queue            |
| Fast lookup by key              | Hash Table       |
| Sorted data with fast search    | BST              |
| Get min/max quickly             | Heap             |
| Represent relationships/network | Graph            |
| Hierarchical data               | Tree             |

## Key Takeaway

> Master the trade-offs. Every data structure excels at some operations and is weak at others. Choosing the right one is half the problem solved.
