package org.multithreading;

import java.util.*;
import java.util.stream.IntStream;

public class StudentScoreGenerator {

    static final Random R = new Random();
    static int MIN_SCORE = 6;
    static int MAX_SCORE = 20;
    static int MAX_STUDENT_PERFORMANCE_BIAS = 5;

    public static List<int[]> generate(int studentBodySize, int nbOfStores) {
        //  choose ArrayList has it is the best collection to be splitted for parallel processing
        List<int[]> studentsScores = new ArrayList<>(studentBodySize);


        IntStream.range(0,studentBodySize).forEach(index -> {
            int studentBias = R.nextInt(MAX_STUDENT_PERFORMANCE_BIAS *2) - MAX_STUDENT_PERFORMANCE_BIAS;
            //System.out.println(String.format("Student (%s)  with bias (%s)", index, studentBias));
            studentsScores.add( new Random()
                    .ints(nbOfStores, MIN_SCORE, MAX_SCORE)
                    .map(number -> number + studentBias )
                    .toArray());
        });

        return studentsScores;
    }

    public static void display(List<int[]> studentsScores) {
        IntStream.range(0, studentsScores.size()).forEach((index) -> {
            System.out.print("\nStudent "+index+" : ");
            Arrays.stream(studentsScores.get(index)).forEach(s -> System.out.print(s+", "));
        });
        System.out.println("\n");
    }


}
