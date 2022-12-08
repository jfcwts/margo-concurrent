package org.multithreading.method;

import org.multithreading.StudentResult;
import org.multithreading.StudentResult.Distinction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculatorStreamBased extends CalculatorTemplate {


    @Override
    public Method getMethod() {
        return Method.STREAMS;
    }

    @Override
    protected List<StudentResult> computeStudentResults(List<int[]> studentScores) {
        List<StudentResult> studentResults = studentScores.stream().map(scores -> {
            double average = ((double) Arrays.stream(scores).sum()) / scores.length;
            return new StudentResult(average);
        }).collect(Collectors.toList());

        return studentResults;
    }
}
