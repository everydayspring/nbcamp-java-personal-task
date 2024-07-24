package calculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        double num1;
        double num2;
        char operator;
        double result;

        // 첫번째 연산을 위한 초기화
        String flag = "연산";

        Scanner sc = new Scanner(System.in);

        // flag에 exit가 입력되었을때 까지 반복
        while (!flag.equals("exit")) {

            // Scanner에서 nextInt()와 nextLine() 사용시 공란 입력 방지를 위해 parser 사용
            // 연산을 위해 Double로 선언
            System.out.print("첫 번째 숫자를 입력하세요: ");
            num1 = Double.parseDouble(sc.nextLine());
            System.out.print("두 번째 숫자를 입력하세요: ");
            num2 = Double.parseDouble(sc.nextLine());

            // 연산 기호를 Character 형태로 입력받기 위해 charAt() 사용
            System.out.print("사칙연산 기호를 입력하세요: ");
            operator = sc.nextLine().charAt(0);

            result = 0;

            // 스위치문을 사용한 연산
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

            System.out.println("결과: " + result);

            // 계산기 종료를 위한 flag 입력
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료)");
            flag = sc.nextLine();
        }

    }
}