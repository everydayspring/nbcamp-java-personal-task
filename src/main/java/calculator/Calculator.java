package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    // private 캡슐화
    // static으로 선언된 클래스 멤버, 저장공간 면에서 효율적이다
    // 값이 수정되면 안되는 PI 변수는 final로 선언
    private static Queue<Double> calcResultQueue;
    private static Queue<Double> areaResultQueue;

    static final double PI = 3.14159265358979323846;

    public Calculator() {
        calcResultQueue = new LinkedList<>();
        areaResultQueue = new LinkedList<>();
    }

    // calculate 메소드에서는 CalculatorException가 발생할 수 있다
    public double calculate(double num1, double num2, char operator) throws CalculatorException {
        double result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                // 두번째 숫자 0에 대한 나눗셈 예외처리
                if (num2 == 0) {
                    // CalculatorException 발생
                    throw new CalculatorException("Division by zero");
                }
                result = num1 / num2;
                break;
            default:
                // CalculatorException 발생
                throw new CalculatorException("Invalid operator");
        }

        return result;
    }

    public void removeResult() {
        calcResultQueue.poll();
    }

    public void inquiryResults(Queue<Double> queue) {

        System.out.println();
        System.out.println("---- 연산 결과 전체 조회 ----");

        for (double res : queue) {
            System.out.print(res + " ");
        }
        System.out.println();
        System.out.println("------------------------");

    }

    public double calculateCircleArea(double redius) {
        return PI * redius * redius;
    }

    // Getter
    public Queue<Double> getCalcResultQueue() {
        return calcResultQueue;
    }

    // Setter
    public void setCalcResultQueue(Queue<Double> resultQueue) {
        this.calcResultQueue = resultQueue;
    }

    public Queue<Double> getAreaResultQueue() {
        return areaResultQueue;
    }

    public void setAreaResultQueue(Queue<Double> areaResultQueue) {
        this.areaResultQueue = areaResultQueue;
    }

}
