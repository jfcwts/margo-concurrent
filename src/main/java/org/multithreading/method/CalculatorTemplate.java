package org.multithreading.method;

import org.multithreading.CalculatorOutcome;
import org.multithreading.StudentResult;

import java.util.List;

public abstract class CalculatorTemplate {

    public enum Method {
        STREAMS,
        CONCURRENT_STREAMS,
        THREADS,
        EXECUTOR_SERVICE,
        COMPUTABLE_FUTURES,
        COMPUTABLE_FUTURES_AND_STREAMS
    }


    public abstract Method getMethod();

    protected abstract List<StudentResult> computeStudentResults(List<int[]> studentScores);


    public CalculatorOutcome compute(List<int[]> studentScores){

        long start = System.nanoTime();

        List<StudentResult> studentResults = computeStudentResults(studentScores);

        long end = System.nanoTime();

        return new CalculatorOutcome(
                getMethod(),
                studentResults,
                end - start);
    }


}
