# Java 개인 과제
## _진화하는 계산기 프로그램_

<details>
<summary>Level 1</summary>

1. Scanner 사용하여 숫자 입력받기
```java
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("첫 번째 숫자를 입력하세요:");
        Double num1 = Double.parseDouble(sc.nextLine());
        System.out.print("두 번째 숫자를 입력하세요:");
        Double num2 = Double.parseDouble(sc.nextLine());

    }
}
```
2. Scanner 사용하여 연산기호 입력받기
```java
System.out.print("사칙연산 기호를 입력하세요: ");
char operator = sc.nextLine().charAt(0);
```
3. 제어문을 사용한 연산, 결과 출력
```java
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
            System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다. ");
            break;
        }
        result = num1 / num2;
        break;
}
```
4. 연산 반복 적용
```java
// 첫번째 연산을 위한 초기화
String flag = "연산";

// flag에 exit가 입력되었을때 까지 반복
while (!flag.equals("exit")) {
    
    ...

    System.out.println("결과: " + result);

    // 계산기 종료를 위한 flag 입력
    System.out.print("더 계산하시겠습니까? (exit 입력 시 종료)");
    flag = sc.nextLine();
}
```
5. 배열에 연산 결과 저장 (size:10)
```java
double[] resultArray = new double[10];
int index = 0;

...

// 결과 출력후 배열에 저장, index 값 증가
System.out.println("결과: " + result);
resultArray[index++] = result;

```
6. 연산 결과 배열 순환
```java
 // 오래된 데이터 삭제를 위한 조건문, 반복문
if (index == resultArray.length) {
    for (int i = 0; i < (resultArray.length - 1); i++) {
        resultArray[i] = resultArray[i + 1];
    }
    index = --index;
}
resultArray[index++] = result;
```
7. JCF 적용, remove 기능 추가
```java
// 연산 결과를 저장하는 Queue
Queue<Double> resultQueue = new LinkedList<Double>();

...

// 결과 출력후 Queue에 저장
System.out.println("결과: " + result);
resultQueue.add(result);


// 가장 오래된 연산 결과 삭제
System.out.print("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제) : ");
if(sc.nextLine().equals("remove")) {
    resultQueue.poll();
}
```
8. 연산 결과 전체 조회
```java
// 연산 결과 전체 조회
System.out.print("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회) : ");
if(sc.nextLine().equals("inquiry")) {
    for(double res: resultQueue) {
        System.out.print(res + " ");
    }
    System.out.println();
}
```

</details>

<details>
<summary>Level 2</summary>

1. Calculator Class 생성
```java
/* Calculator.java */

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
```
```java
/* CalculatorException.java */

public class CalculatorException extends Exception{
    public CalculatorException(String errorMessage) {
        super(errorMessage);
    }
}
```
2. main method에서 Calculator Class 활용
```java
/* App.java */

...

// Calculator 인스턴스 생성
Calculator calc = new Calculator();

...

// Exception이 발생할 수 있는 코드를 try-catch 문으로 감싸줌
try {
    // Calculator 클래스의 calculate 메소드를 사용하여 연산
    result = calc.calculate(num1, num2, operator);

    ...
            
    }
} catch (CalculatorException e) {
    System.out.println(e.getMessage());
}
```
3. 캡슐화
```java
/* App.java */

// 캡슐화 된 resultQueue에 직접 접근 할 수 없기 때문에 getter 함수 사용
calc.getResultQueue().add(result);

System.out.print("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제) : ");
if (sc.nextLine().equals("remove")) {
    calc.getResultQueue().poll();
}

if (sc.nextLine().equals("inquiry")) {
    for (double res : calc.getResultQueue()) {
        System.out.print(res + " ");
    }
    System.out.println();
}
```
```java
/* Calculator.java */

// private 캡슐화
private Queue<Double> resultQueue = new LinkedList<>();

...

// Getter
public Queue<Double> getResultQueue() {
    return resultQueue;
}

// Setter
public void setResultQueue(Queue<Double> resultQueue) {
    this.resultQueue = resultQueue;
}
```
4. removeResult()
```java
/* App.java */

System.out.print("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제) : ");
if (sc.nextLine().equals("remove")) {
    calc.removeResult();
}
```
```java
/* Calculator.java */

public void removeResult() {
    resultQueue.poll();
}
```
5. inquiryResult()
```java
/* App.java */

System.out.print("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회) : ");
if (sc.nextLine().equals("inquiry")) {
    calc.inquiryResults();
}
```
```java
/* Calculator.java */

public void inquiryResults() {
    for (double res : resultQueue) {
        System.out.print(res + " ");
    }
    System.out.println();
}
```
6. 생성자를 통한 컬렉션 필드 초기화
```java
/* Calculator.java */

public class Calculator {
    private Queue<Double> resultQueue;

    public Calculator() {
        resultQueue = new LinkedList<>();
    }
    
    ...
    
}
```
7. 원의 넓이 계산
```java
/* App.java */

// 연산 번호
int num;

// 원의 반지름
double radius;

...

System.out.print("원하는 연산의 번호를 입력하세요: ");
num = Integer.parseInt(sc.nextLine());

if(num == 1) {
    ...
} else if(num == 2) {
    System.out.print("원의 반지름 값을 입력하세요: ");
    radius = Double.parseDouble(sc.nextLine());

    result = calc.calculateCircleArea(radius);
    System.out.println("결과: " + result);
    calc.getAreaResultQueue().add(result);
    calc.inquiryResults(calc.getAreaResultQueue());
}
```
```java
/* Calculator.java */

private static Queue<Double> areaResultQueue;
static final double PI = 3.14159265358979323846;

...

public Calculator() {
    ...
    areaResultQueue = new LinkedList<>();
}

...

public double calculateCircleArea(double redius) {
    return PI * redius * redius;
}
```
8. extends
```java
/* App.java */

ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator();
CircleCalculator circleCalculator = new CircleCalculator();

...

// Calculator 클래스의 calculate 메소드를 사용하여 연산
result = calc.calculate(num1, num2, operator);
result = arithmeticCalculator.calculate(num1, num2, operator);

// 결과 출력후 Queue에 저장
// 캡슐화 된 resultQueue에 직접 접근 할 수 없기 때문에 getter 함수 사용
System.out.println("결과: " + result);
arithmeticCalculator.getResultQueue().add(result);


// 가장 오래된 연산 결과 삭제
System.out.print("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제) : ");
if (sc.nextLine().equals("remove")) {
    arithmeticCalculator.removeResult(arithmeticCalculator.getResultQueue());
}

// 연산 결과 전체 조회
System.out.print("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회) : ");
if (sc.nextLine().equals("inquiry")) {
    arithmeticCalculator.inquiryResults(arithmeticCalculator.getResultQueue());
}

...

result = circleCalculator.calculate(radius);
System.out.println("결과: " + result);

circleCalculator.getResultQueue().add(result);
circleCalculator.inquiryResults(circleCalculator.getResultQueue());
```
```java
/* Calculator.java */

public class Calculator {
    private Queue<Double> resultQueue;

    public Calculator() {
        resultQueue = new LinkedList<>();
    }

    // 첫번째 결과값 삭제
    public void removeResult(Queue<Double> queue) {
        ...
    }

    // 결과값 전체 출력
    public void inquiryResults(Queue<Double> queue) {
        ...
    }

    public Queue<Double> getResultQueue() { return resultQueue; }

    public void setResultQueue(Queue<Double> resultQueue) {}
}
```
```java
/* ArithmeticCalculator.java */

public class ArithmeticCalculator extends Calculator {
    public double calculate(double num1, double num2, char operator) throws CalculatorException {
        ...
    }
}
```
```java
/* CircleCalculator.java */

public class CircleCalculator extends Calculator {
    static final double PI = 3.14159265358979323846;

    public double calculate(double redius) {
        ...
    }
}
```
10. Single Responsibility Principle
```java
/* ArithmeticCalculator.java */

public class ArithmeticCalculator extends Calculator {
    private static Queue<Double> resultQueue;
    AddOperator addOperator;
    SubtractOperator subtractOperator;
    MultiplyOperator multiplyOperator;
    DivideOperator divideOperator;

    ArithmeticCalculator() {
        addOperator = new AddOperator();
        subtractOperator = new SubtractOperator();
        multiplyOperator = new MultiplyOperator();
        divideOperator = new DivideOperator();
    }

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
    
    ...
    
}
```
```java
/* AddOperator.java */

public class AddOperator {
    double add(double num1, double num2) {
        return num1 + num2;
    }
}

/* SubtractOperator.java */

public class SubtractOperator {
    double subtract(double num1, double num2) {
        return num1 - num2;
    }
}

/* MultiplyOperator.java */

public class MultiplyOperator {
    double subtract(double num1, double num2) {
        return num1 * num2;
    }
}

/* DivideOperator.java */

public class DivideOperator {
    double subtract(double num1, double num2) {
        return num1 / num2;
    }
}
```
10. ModOperator
```java
/* ArithmeticCalculator.java */
...

switch (operator) {
    
    ...
        
    case '%':
        // 두번째 숫자 0에 대한 나눗셈 예외처리
        if (num2 == 0) {
            // CalculatorException 발생
            throw new CalculatorException("Division by zero");
        }
        result = modOperator.mod(num1, num2);
        break;
        
    ...
}
```
```java
/* ModOperator.java */

public class ModOperator {
    double mod(double num1, double num2) {
        return num1 % num2;
    }
}
```

</details>

<details>
<summary>Level 3</summary>

1. Enum
```java
/* App.java */

...
// 연산 기호를 OperatorType으로 변환
OperatorType operatorType = OperatorType.fromSymbol(operator);
result = arithmeticCalculator.calculate(num1, num2, operatorType);
...
```
```java
/* ArithmeticCalculator.java */
public class ArithmeticCalculator extends Calculator {

    // calculate 메소드에서는 CalculatorException이 발생할 수 있다
    public double calculate(double num1, double num2, OperatorType operator) throws CalculatorException {
        return operator.apply(num1, num2);
    }
}
```
```java
/* OperatorType.java */
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
```
2. Generic
```java
public class Calculator<T> {
    private Queue<T> resultQueue;

    public Calculator() {
        resultQueue = new LinkedList<T>();
    }

    // 첫번째 결과값 삭제
    public void removeResult(Queue<T> queue) {
        queue.poll();
    }

    // 결과값 전체 출력
    public void inquiryResults(Queue<T> queue) {

        System.out.println("\n---- 연산 결과 전체 조회 ----");
        for (T res : queue) {
            System.out.print(res + " ");
        }
        System.out.println("\n------------------------");

    }

    public Queue<T> getResultQueue() {
        return resultQueue;
    }

    public void setResultQueue(Queue<T> resultQueue) {}
}

```

</datails>