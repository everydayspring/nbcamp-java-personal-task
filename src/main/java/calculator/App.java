package calculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Calculator 인스턴스 생성
        Calculator calc = new Calculator();

        // 입력을 위한 Scanner 생성
        Scanner sc = new Scanner(System.in);

        // 첫번째 숫자, 두번째 숫자, 연산기호, 결과
        double num1;
        double num2;
        char operator;
        double result = 0;

        // 첫번째 연산을 위한 초기화
        String flag = "연산";

        // flag에 exit가 될 때까지 반복
        while (!flag.equals("exit")) {

            // Scanner에서 nextInt()와 nextLine() 사용시 공란 입력 방지를 위해 parser 사용
            System.out.print("첫 번째 숫자를 입력하세요: ");
            num1 = Double.parseDouble(sc.nextLine());
            System.out.print("두 번째 숫자를 입력하세요: ");
            num2 = Double.parseDouble(sc.nextLine());

            // 연산 기호를 Character 형태로 입력받기 위해 charAt() 사용
            System.out.print("사칙연산 기호를 입력하세요: ");
            operator = sc.nextLine().charAt(0);

            // Exception이 발생할 수 있는 코드를 try-catch 문으로 감싸줌
            try {
                // Calculator 클래스의 calculate 메소드를 사용하여 연산
                result = calc.calculate(num1, num2, operator);

                // 결과 출력후 Queue에 저장
                // 캡슐화 된 resultQueue에 직접 접근 할 수 없기 때문에 getter 함수 사용
                System.out.println("결과: " + result);
                calc.getResultQueue().add(result);


                // 가장 오래된 연산 결과 삭제
                System.out.print("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제) : ");
                if (sc.nextLine().equals("remove")) {
                    calc.getResultQueue().poll();
                }

                // 연산 결과 전체 조회
                System.out.print("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회) : ");
                if (sc.nextLine().equals("inquiry")) {
                    for (double res : calc.getResultQueue()) {
                        System.out.print(res + " ");
                    }
                    System.out.println();
                }
            } catch (CalculatorException e) {
                System.out.println(e.getMessage());
            }

            // 계산기 종료를 위한 flag 입력
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료) : ");
            flag = sc.nextLine();

        }
    }
}
