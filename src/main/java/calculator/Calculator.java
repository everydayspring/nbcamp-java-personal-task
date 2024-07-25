package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    Queue<Double> resultQueue = new LinkedList<>();

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

}
