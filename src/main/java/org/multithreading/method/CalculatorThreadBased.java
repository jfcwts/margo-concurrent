package org.multithreading.method;

import org.apache.commons.collections4.ListUtils;
import org.multithreading.StudentResult;
import org.multithreading.StudentResult.Distinction;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CalculatorThreadBased extends CalculatorTemplate {


    @Override
    public Method getMethod() {
        return Method.THREADS;
    }

    @Override
    protected List<StudentResult> computeStudentResults(List<int[]> studentScores)  {

        List<List<int[]>> threadWorkloads = ListUtils.partition(studentScores, (int)Math.ceil(studentScores.size() / 8.0));
        int numberOfThreads = threadWorkloads.size();

        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        Map<Integer, List<StudentResult>> resultBucket = new ConcurrentHashMap<>(numberOfThreads);

        for(int indexBucket = 0; indexBucket < numberOfThreads; indexBucket++){
            new Thread(new CalculatorRunnable(
                    indexBucket, threadWorkloads.get(indexBucket), latch, resultBucket
            )).start();
        }

        try {
            latch.await();
            //System.out.println("All thread finished\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            return resultBucket.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
    }

    static class CalculatorRunnable implements Runnable {
        private int index;
        private List<int[]> workload;
        private CountDownLatch latch;
        Map<Integer, List<StudentResult>> workloadBucket;

        public CalculatorRunnable(int index, List<int[]> workload, CountDownLatch latch, Map<Integer, List<StudentResult>> workloadBucket){
            this.index = index;
            this.workload = workload;
            this.latch = latch;
            this.workloadBucket = workloadBucket;
            //System.out.println(String.format("Thread %s, workload %s", index, workload.size()));
        }

        @Override
        public void run() {
            workloadBucket.put(index, workload.stream().map(scores -> {
                double average = ((double) Arrays.stream(scores).sum()) / scores.length;
                return new StudentResult(average);
            }).collect(Collectors.toList()));
            //System.out.println(String.format("Thread %s finished", index));
            latch.countDown();
        }
    }
}
