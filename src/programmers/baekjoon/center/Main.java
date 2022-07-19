package programmers.baekjoon.center;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("args = " + Arrays.deepToString(solution(7, new int[]{1, 5, 2, 10, -99, 7, 5})));
        // 제출용 소스
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for(int i=0; i<n; i++) {
            int number = Integer.parseInt(br.readLine());

            if (minHeap.size() == maxHeap.size()) {
                maxHeap.add(number);
            } else {
                minHeap.add(number);
            }

            if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int swap = maxHeap.poll();
                    maxHeap.add(minHeap.poll());
                    minHeap.add(swap);
                }
            }

            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.println(sb);
    }

    public static Integer[] solution(int n, int[] array) {
        Integer[] result = new Integer[n];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for(int i=0; i<array.length; i++) {
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.add(array[i]);
            } else {
                minHeap.add(array[i]);
            }

            if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int swap = maxHeap.poll();
                    maxHeap.add(minHeap.poll());
                    minHeap.add(swap);
                }
            }

            result[i] = maxHeap.peek();
        }

        return result;
    }
}
