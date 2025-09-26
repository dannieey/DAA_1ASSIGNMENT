package utils;

public class Metrics {
    private int comparison; //сколько раз сравнивали элементы
    private int assignments; //сколько раз переписывали
    private int currentDepth; //для отслеживания рекурсии
    private int maxDepth;
    private long runTime;


    //каждый раз когда сравнивают
    public void incrementComparison() {
        comparison++;
    }
    public int getComparison() {
        return comparison;
    }

    //каждый раз когда присваивают
    public void incrementAssignments() {
        assignments++;
    }
    public int getAssignments() {
        return assignments;
    }

    //глубина рекурсии
    public void interRecursion() {
        currentDepth++;
        if(currentDepth > maxDepth) {
            maxDepth=currentDepth;
        }

    }
    public void exitRecursion(){
        currentDepth--;
    }
    public int getCurrentDepth() {
        return maxDepth;
    }

    //для времени
    public void startTime() {
        runTime=-System.nanoTime();
    }
    public void endTime() {
        runTime+=System.nanoTime();
    }
    public long getRunTime() {
        return runTime;
    }

    //для сброса, чтобы при входе в алго, начинали все по новому
    public void reset(){
        comparison=0;
        assignments=0;
        currentDepth=0;
        maxDepth=0;
        runTime=0;

    }



}
