package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private BufferedWriter bw;
    private String separator=";";

    public CSVWriter(String filename) throws IOException { //конструктор-->открываем файл для записи
            bw=new BufferedWriter(new FileWriter(filename));
    }

    public void writeHeader(String line) throws IOException {
        String header=String.join(separator,"Algorithm", "RunTime(ms)", "Comparison","Assignments", "MaxDepth", "Allocations");
        bw.write(header);
        bw.newLine();
    }

    public void writeRow(String algoName, Metrics metrics, int allocations) throws IOException {
        String row=algoName+separator+(metrics.getRunTime()/1_000_000.0)+separator+metrics.getComparison()+separator+metrics.getAssignments()+separator+metrics.getCurrentDepth()+separator+allocations;
        bw.write(row);
        bw.newLine();
    }//заполняем

    public void close() throws IOException {
        if(bw!=null){
            bw.close();
        }
    }
}
