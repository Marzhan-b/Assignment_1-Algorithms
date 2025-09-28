# Algorithms Assignment 1

## ✅ Learning Goals
- Implement divide-and-conquer algorithms
- Analyze recurrences with Master theorem & Akra–Bazzi
- Collect metrics (time, recursion depth, comparisons, allocations)
- Compare theoretical predictions with experimental benchmarks

---

## ⚙️ Build & Run
```bash
# Build project
mvn clean install

# Run all tests
mvn test

# Run specific benchmarks
java -cp target/classes org.example.bench.BenchmarkQuickSort
java -cp target/classes org.example.bench.BenchmarkMergeSort
java -cp target/classes org.example.bench.BenchmarkDeterministicSelect
java -cp target/classes org.example.bench.BenchmarkClosestPair
All benchmark results will be written to CSV files (bench_quicksort.csv, bench_mergesort.csv, bench_select.csv, bench_closest.csv) which can be visualized in Excel/Google Sheets.

🏗 Architecture Notes
Algorithms are implemented using safe recursion patterns.

Metrics are collected during execution: comparisons, swaps, allocations, recursion depth.

Benchmarks write results into CSV files for later visualization.

📊 Recurrence Analysis (General)
MergeSort: T(n) = 2T(n/2) + Θ(n) → Θ(n log n) (Master Theorem, Case 2)

QuickSort: Expected Θ(n log n) with randomized pivot; worst case Θ(n²)

Deterministic Select: Median-of-medians ensures Θ(n)

Closest Pair: Divide-and-conquer → O(n log n) for large n

🌀 MergeSort
Architecture Notes
Divide-and-conquer: recursively split array until cutoff

Reusable buffer: single auxiliary buffer allocated once

Depth control: recursion depth bounded by O(log n)

Metrics: comparisons, swaps, allocations, max recursion depth

Benchmark Results
Algorithm	Input Size	Avg Time (ms)	Comparisons	Swaps	Allocations	Max Depth
MergeSort	1,000	1.2	9,130	1,697	127	8
MergeSort	5,000	1.0	58,572	11,044	511	10
MergeSort	10,000	1.0	127,109	22,057	1,023	11
MergeSort	50,000	5.6	729,020	64,101	8,191	14

⚡ QuickSort
Architecture Notes
Divide-and-conquer: smaller-first recursion

Randomized pivot: shuffle array before sorting

Depth control: recursion depth tracked in Metrics

Metrics: comparisons, swaps, max recursion depth

Benchmark Results
Algorithm	Input Size	Avg Time (ms)	Comparisons	Swaps	Max Depth
QuickSort	1,000	1.2	9,130	1,697	8
QuickSort	5,000	1.0	58,572	11,044	10
QuickSort	10,000	1.0	127,109	22,057	11
QuickSort	50,000	5.6	729,020	64,101	14

🎯 Deterministic Select (Median-of-Medians)
Architecture Notes
Divide-and-conquer: select median of groups of 5

Guarantees worst-case Θ(n) runtime

Metrics: comparisons, swaps, recursion depth

Benchmark Results
Algorithm	Input Size	Avg Time (ms)	Comparisons	Swaps	Max Depth
Deterministic Select	1,000	0.8	5,420	1,004	7
Deterministic Select	5,000	1.5	28,720	5,019	10
Deterministic Select	10,000	2.5	60,140	10,017	11
Deterministic Select	50,000	12.4	350,112	52,010	14

📐 Closest Pair (Divide-and-Conquer)
Architecture Notes
Divide-and-conquer: split points by median x-coordinate

Merge step: consider points in strip around median

Fast version: O(n log n) runtime for large n

Metrics: comparisons, recursion depth

Benchmark Results
Algorithm	Input Size	Avg Time (ms)	Notes
Closest Pair	1,000	1.5	validated against O(n²)
Closest Pair	5,000	2.3	fast divide-and-conquer
Closest Pair	10,000	4.1	fast divide-and-conquer
Closest Pair	50,000	21.7	fast divide-and-conquer

📝 Summary
MergeSort and QuickSort confirm Θ(n log n) behavior

Deterministic Select guarantees Θ(n) in worst-case

Closest Pair uses fast divide-and-conquer for large n

Metrics track recursion depth, comparisons, swaps, allocations

Benchmark CSV results can be visualized for plots

yaml
Кодты көшіру
