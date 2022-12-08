package org.multithreading;

import org.multithreading.method.CalculatorTemplate;

import java.util.List;
import java.util.stream.IntStream;

public class CalculatorOutcome {



    private CalculatorTemplate.Method method;
    private List<StudentResult> studentResults;
    private long executionTime;

    public CalculatorOutcome(CalculatorTemplate.Method method, List<StudentResult> studentResults, long executionTime) {
        this.method = method;
        this.studentResults = studentResults;
        this.executionTime = executionTime;
    }

    public void printAveragesAndDistinctions(){
        IntStream.range(0, studentResults.size()).forEach(index -> {
            System.out.println(String.format("Student (%s) : average (%s) and distinction (%s)",
                    index,
                    studentResults.get(index).getAverage(),
                    studentResults.get(index).getDistinction()));

        });
    }

    public void printExecutionTimeInNano(){
        System.out.println(String.format("\nMethod: %s\nexecution time: %s ns ", method, executionTime));
    }

    public void printExecutionTimeInMilliseconds(){
        System.out.println(String.format("\nMethod: %s\nexecution time: %s ms\n\n ", method, executionTime/1000000.0));
    }
}
