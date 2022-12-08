package org.multithreading.method;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.multithreading.StudentResult;
import org.multithreading.StudentResult.Distinction;

public class CalculatorConcurrentStreamBased extends CalculatorTemplate {


    @Override
    public Method getMethod() {
        return CalculatorTemplate.Method.CONCURRENT_STREAMS;
    }

    @Override
    protected List<StudentResult> computeStudentResults(List<int[]> studentScores) {
        List<StudentResult> studentResults = studentScores.stream().parallel().map(scores -> {
            double average = ((double) Arrays.stream(scores).sum()) / scores.length;
            return new StudentResult(average);
        }).collect(Collectors.toList());

        return studentResults;
    }
}
