package calculator;

public class ArithmeticCalculator extends Calculator {

    // calculate 메소드에서는 CalculatorException이 발생할 수 있다
    public double calculate(double num1, double num2, OperatorType operator) throws CalculatorException {

        return operator.apply(num1, num2);
    }
}
