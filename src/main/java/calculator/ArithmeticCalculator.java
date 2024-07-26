package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class ArithmeticCalculator extends Calculator {
    private static Queue<Double> resultQueue;
    AddOperator addOperator;
    SubtractOperator subtractOperator ;
    MultiplyOperator multiplyOperator;
    DivideOperator divideOperator;

    ArithmeticCalculator() {
        resultQueue = new LinkedList<>();
        addOperator = new AddOperator();
        subtractOperator = new SubtractOperator();
        multiplyOperator = new MultiplyOperator();
        divideOperator = new DivideOperator();
    }

    // calculate 메소드에서는 CalculatorException가 발생할 수 있다
    public double calculate(double num1, double num2, char operator) throws CalculatorException {
        double result = 0;

        switch (operator) {
            case '+':
                result = addOperator.add(num1, num2);
                break;
            case '-':
                result = subtractOperator.subtract(num1, num2);
                break;
            case '*':
                result = multiplyOperator.multiply(num1, num2);
                break;
            case '/':
                // 두번째 숫자 0에 대한 나눗셈 예외처리
                if (num2 == 0) {
                    // CalculatorException 발생
                    throw new CalculatorException("Division by zero");
                }
                result = divideOperator.divide(num1, num2);
                break;
            default:
                // CalculatorException 발생
                throw new CalculatorException("Invalid operator");
        }

        return result;
    }

    public Queue<Double> getResultQueue() {
        return resultQueue;
    }

    // Setter
    public void setResultQueue(Queue<Double> resultQueue) {
        this.resultQueue = resultQueue;
    }
}
