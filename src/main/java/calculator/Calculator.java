package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {

    // 첫번째 결과값 삭제
    public void removeResult(Queue<Double> queue) {
        queue.poll();
    }

    // 결과값 전체 출력
    public void inquiryResults(Queue<Double> queue) {

        System.out.println();
        System.out.println("---- 연산 결과 전체 조회 ----");

        for (double res : queue) {
            System.out.print(res + " ");
        }
        System.out.println();
        System.out.println("------------------------");

    }

}
