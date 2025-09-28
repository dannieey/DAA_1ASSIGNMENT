package utils;

public class Metrics {
    private int comparisons = 0;
    private int assignments = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;
    private long runTime = 0;
    private int allocations = 0;

    // Comparisons
    public void incrementComparison() {
        comparisons++;
    }
    public int getComparisons() {
        return comparisons;
    }

    // Assignments
    public void incrementAssignments() {
        assignments++;
    }
    public int getAssignments() {
        return assignments;
    }

    // Depth
    public void incrementCurrentDepth() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }
    public void decrementCurrentDepth() {
        currentDepth--;
    }
    public int getMaxDepth() {
        return maxDepth;
    }

    // Allocations
    public void incrementAllocations() {
        allocations++;
    }
    public int getAllocations() {
        return allocations;
    }

    // Runtime
    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }
    public long getRunTime() {
        return runTime;
    }
}
