package calculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Scanner에서 nextInt()와 nextLine() 사용시 공란 입력 방지를 위해 parser 사용
        // 연산을 위해 Double로 선언
        System.out.print("첫 번째 숫자를 입력하세요:");
        Double num1 = Double.parseDouble(sc.nextLine());
        System.out.print("두 번째 숫자를 입력하세요:");
        Double num2 = Double.parseDouble(sc.nextLine());

    }
}