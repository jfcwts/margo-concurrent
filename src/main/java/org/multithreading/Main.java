package org.multithreading;

import org.multithreading.method.CalculatorConcurrentStreamBased;
import org.multithreading.method.CalculatorStreamBased;
import org.multithreading.method.CalculatorTemplate;
import org.multithreading.method.CalculatorThreadBased;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("    ***$$$ START");

        List<int[]> scores = StudentScoreGenerator.generate(5000, 10000);
        //StudentScoreGenerator.display(scores);

        CalculatorTemplate calculator1 = new CalculatorStreamBased();
        CalculatorOutcome outcome = calculator1.compute(scores);

        //outcome.printAveragesAndDistinctions();
        outcome.printExecutionTimeInMilliseconds();


        CalculatorTemplate calculator2 = new CalculatorConcurrentStreamBased();
        outcome = calculator2.compute(scores);

        //outcome.printAveragesAndDistinctions();
        outcome.printExecutionTimeInMilliseconds();

        CalculatorTemplate calculator3 = new CalculatorThreadBased();
        outcome = calculator3.compute(scores);

        //outcome.printAveragesAndDistinctions();
        outcome.printExecutionTimeInMilliseconds();

        System.out.println("\n    ***$$$ END");


    }


}