package programmers.baekjoon.springset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int count = 0;

        Set<String> stringSet = new HashSet<>();

        for (int i=0; i<n; i++) {
            stringSet.add(br.readLine());
        }

        for (int i=0; i<m; i++) {
            if (stringSet.contains(br.readLine())) {
                count++;
            }
        }
        System.out.println(count);
    }
}
