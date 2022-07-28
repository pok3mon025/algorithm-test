package programmers.baekjoon.mandf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            if (n == 0 && m == 0)
                break;

            if (n >= m) {
                if (n % m == 0) {
                    System.out.println("multiple");
                    continue;
                }
            } else {
                if (m % n == 0) {
                    System.out.println("factor");
                    continue;
                }
            }

            System.out.println("neither");
        }
    }
}
