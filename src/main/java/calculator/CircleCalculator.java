package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class CircleCalculator extends Calculator {

    private static Queue<Double> resultQueue;

    CircleCalculator() {
        resultQueue = new LinkedList<>();
    }

    // 값이 수정되면 안되는 PI 변수는 final로 선언
    static final double PI = 3.14159265358979323846;

    // 원의 넓이 계산 메소드
    public double calculate(double redius) {
        return PI * redius * redius;
    }

    public Queue<Double> getResultQueue() {
        return resultQueue;
    }

    // Setter
    public void setResultQueue(Queue<Double> resultQueue) {
        this.resultQueue = resultQueue;
    }

}