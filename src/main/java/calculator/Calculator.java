package calculator;

import java.util.LinkedList;
import java.util.Queue;

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
