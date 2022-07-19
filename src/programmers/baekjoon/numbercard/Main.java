package programmers.baekjoon.numbercard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> cards = new HashSet<>(Arrays.asList(br.readLine().split(" ")));

        int m = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        // 완전탐색
        for (String number : input) {
            if (cards.contains(number)) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
            System.out.print(" ");
        }

        // 이분탐색으로 풀어보기
        n = Integer.parseInt(br.readLine());
        int[] array = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        for(int i=0; i<m; i++) {
            System.out.print(binarySearch(Integer.parseInt(st.nextToken()), array));
            System.out.print(" ");
        }
    }

    private static int binarySearch(int number, int[] cards) {
        int low = 0;
        int high = cards.length;
        int result = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (cards[mid] == number) {
                result = 1;
                break;
            }

            if (cards[mid] < number) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

}
