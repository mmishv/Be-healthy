package by.fpmibsu.be_healthy.entity;

import java.util.Objects;
public class Calculator {
    private double weight;
    private int height;
    private int age;
    private double activity;
    private String sex;
    private double goal;
    Calculator(String sex, int height, double weight, int age, double act, double goal){
        this.sex=sex;
        this.height=height;
        this.weight=weight;
        this.age=age;
        this.activity=act;
        this.goal=goal;
    }

    int calculate(){
        double result = (Objects.equals(sex, "female"))? 447.6 + 9.2 * weight + 3.1 * height - 4.3 * age :
        88.36 + 13.4 * weight + 4.8 * height - 5.7 * age;
        result *= activity*goal;
        return (int) result;
    }
}
