package org.multithreading;

public class StudentResult {

    public enum Distinction {
        AUCUNE,
        ASSEZ_BIEN,
        BIEN,
        TRES_BIEN
    }

    private double average;
    private Distinction distinction;


    public StudentResult(double average) {
        this.average = average;
        this.distinction = Distinction.AUCUNE;
        if(average >=12 && average <14){
            this.distinction = Distinction.ASSEZ_BIEN;
        }else if(average >= 14 && average < 16 ){
            this.distinction = Distinction.BIEN;
        }else if(average >= 16){
            this.distinction = Distinction.TRES_BIEN;
        }
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Distinction getDistinction() {
        return distinction;
    }

    public void setDistinction(Distinction distinction) {
        this.distinction = distinction;
    }
}
