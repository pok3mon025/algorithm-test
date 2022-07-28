package programmers.baekjoon.gcdandlcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);

        int d = gcd(x, y);

        System.out.println(d);
        System.out.println(x * y / d);
    }

    private static int gcd(int x, int y) {
        while (y != 0) {
            int r = x % y;

            x = y;
            y = r;
        }
        return x;
    }

}
