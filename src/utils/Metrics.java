package utils;

public class Metrics {
    private int comparisons;
    private int assignments;
    private int currentDepth;
    private int maxDepth;
    private long runTime;
    private int allocations;

    public void incrementComparison() { comparisons++; }
    public void incrementAssignments() { assignments++; }
    public void incrementAllocations() { allocations++; }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exitRecursion() { currentDepth--; }

    public void setRunTime(long runTime) { this.runTime = runTime; }

    public int getComparisons() { return comparisons; }
    public int getAssignments() { return assignments; }
    public int getMaxDepth() { return maxDepth; }
    public long getRunTime() { return runTime; }
    public int getAllocations() { return allocations; }

    public void reset() {
        comparisons = 0;
        assignments = 0;
        currentDepth = 0;
        maxDepth = 0;
        runTime = 0;
        allocations = 0;
    }
}
