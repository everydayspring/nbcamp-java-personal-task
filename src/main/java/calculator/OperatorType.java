package calculator;

public enum OperatorType {
    // 각 연산을 열거형으로 선언
    ADD('+') {
        @Override
        public double apply(double num1, double num2) {
            return num1 + num2;
        }
    },
    SUBTRACT('-') {
        @Override
        public double apply(double num1, double num2) {
            return num1 - num2;
        }
    },
    MULTIPLY('*') {
        @Override
        public double apply(double num1, double num2) {
            return num1 * num2;
        }
    },
    DIVIDE('/') {
        @Override
        public double apply(double num1, double num2) throws CalculatorException {
            if (num2 == 0) {
                throw new CalculatorException("Division by zero");
            }
            return num1 / num2;
        }
    },
    MOD('%') {
        @Override
        public double apply(double num1, double num2) throws CalculatorException {
            if (num2 == 0) {
                throw new CalculatorException("Division by zero");
            }
            return num1 % num2;
        }
    };

    // 사칙연산시 들어온 기호 변경을 막기위해 final 선언
    private final char symbol;

    // 생성자에서 심볼값을 넣어줌
    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    // 연산 기호 예외처리
    public static OperatorType fromSymbol(char symbol) throws CalculatorException {
        for (OperatorType type : values()) {
            if (type.symbol == symbol) {
                return type;
            }
        }
        throw new CalculatorException("Invalid operator");
    }

    // 추상메소드로 선언하여 각 열거 타입 내에서 해당하는 apply 메소드가 자세히 선언된다 (Override)
    public abstract double apply(double num1, double num2) throws CalculatorException;
}
