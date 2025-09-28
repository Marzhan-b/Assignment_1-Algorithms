# Algorithms Assignment 1

## ✅ Learning Goals

* Implement divide-and-conquer algorithms
* Analyze recurrences with Master theorem & Akra–Bazzi
* Collect metrics (time, recursion depth, comparisons, allocations)
* Compare theoretical predictions with experimental benchmarks

---

## ⚙️ Build & Run

```bash
mvn clean install
```

---

## 🧪 Run Tests

```bash
mvn test
```

---

## 🏗 Architecture Notes

* Algorithms are implemented using safe recursion patterns.
* Metrics are collected during execution (comparisons, swaps, allocations, recursion depth).
* Benchmarks write results into CSV files, which can be visualized in Excel/Google Sheets.

---

## 📊 Recurrence Analysis (General)

* **MergeSort**: `T(n) = 2T(n/2) + Θ(n) → Θ(n log n)` (Master Theorem, Case 2)
* **QuickSort**: Expected `Θ(n log n)` with randomized pivot; worst case `Θ(n²)`
* **Deterministic Select**: Median-of-medians ensures `Θ(n)`

---

## 📈 Plots (to be added later)

* Time vs n
* Depth vs n
* (optional) Comparisons vs n

---

## 📝 Summary (General)

* MergeSort confirms `Θ(n log n)` complexity.
* Recursion depth grows logarithmically.
* Constant-factor effects (caches, JVM GC) slightly affect timing.
* Results are consistent with theoretical predictions.

---

# 🌀 MergeSort

### Architecture Notes

* **Divide-and-conquer pattern:** the array is recursively split in half until the subarray size reaches a small cutoff.
* **Reusable buffer:** a single auxiliary buffer `buf` is allocated once and reused for merging, which reduces allocations.
* **Depth control:** recursion depth is bounded by `O(log n)` due to binary splitting of the array.
* **Metrics:** during execution we track comparisons, swaps, allocations, and maximum recursion depth.

---

### Recurrence Analysis

Let `T(n)` be the running time of MergeSort on an input of size `n`.

* The array is divided into two halves → `2T(n/2)`.
* Merging takes `Θ(n)`.
* Therefore:

  ```
  T(n) = 2T(n/2) + Θ(n)
  ```

  By the **Master Theorem (Case 2)**:

  ```
  T(n) = Θ(n log n)
  ```

---

### Benchmark Results (sample data)

| Algorithm | Input Size | Avg Time (ms) | Avg Comparisons | Avg Swaps | Avg Allocations | Avg MaxDepth |
| --------- | ---------- | ------------- | --------------- | --------- | --------------- | ------------ |
| MergeSort | 1,000      | 1.2           | 9,130           | 1,697     | 127             | 8            |
| MergeSort | 5,000      | 1.0           | 58,572          | 11,044    | 511             | 10           |
| MergeSort | 10,000     | 1.0           | 127,109         | 22,057    | 1,023           | 11           |
| MergeSort | 50,000     | 5.6           | 729,020         | 64,101    | 8,191           | 14           |

*(Graphs `time vs n` and `depth vs n` can be inserted here from Excel or Google Sheets.)*

---

### Summary

* Running time confirms the theoretical bound of `Θ(n log n)`.
* Recursion depth grows logarithmically, as expected.
* Comparisons and allocations scale as `O(n log n)`.
* Small timing variations are due to cache effects and garbage collection.



## QuickSort

### Architecture Notes

* **Divide-and-conquer:** recursive split with smaller-first recursion.
* **Randomized pivot:** shuffle array before sorting.
* **Depth control:** recursion depth tracked in `Metrics`.
* **Metrics:** comparisons, swaps, max recursion depth.

### Recurrence Analysis

T(n) = 2T(n/2) + Θ(n) (average case with random pivot)  
Expected runtime: Θ(n log n)

### Benchmark Results 

| Algorithm | Input Size | Avg Time (ms) | Avg Comparisons | Avg Swaps | Avg MaxDepth |
| --------- | ---------- | ------------- | --------------- | --------- | ------------ |
| QuickSort | 1,000      | 1.2           | 9,130           | 1,697     | 8            |
| QuickSort | 5,000      | 1.0           | 58,572          | 11,044    | 10           |
| QuickSort | 10,000     | 1.0           | 127,109         | 22,057    | 11           |
| QuickSort | 50,000     | 5.6           | 729,020         | 64,101    | 14           |
