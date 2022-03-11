package programmers.kakaoblind2022.prime;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        System.out.println(solution(437674, 3));    // 3
        System.out.println(solution(110011, 10));   // 2
        System.out.println(solution(997244, 3));   // 0
    }

    /**
     * n: 양의 정수
     * k: 진수
     * 조건: 0P0, P0, 0P, P (P: 0을 포함하지 않는 소수)
     * @param n
     * @param k
     * @return
     */
    public static int solution(int n, int k) {
        int answer = 0; // 조건에 맞는 소수의 개수

        // k 진수로 변환한다.
        String str = Integer.toString(n, k);

        // 케이스에 맞도록 0으로 split 한다.
        List<String> numbers = Arrays.stream(str.split("0"))
            .filter(s -> !("".equals(s) || "1".equals(s)))
            .collect(Collectors.toList());

        // 소수인지 판별한다.
        for (String number : numbers) {
            answer += isPrime(Long.valueOf(number));
        }

        return answer;
    }

    public static int isPrime(long n) {
        for (int i = 2;  i <= (long) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return 0;
            }
        }
        return 1;
    }

}
