package programmers.baekjoon.unknown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        Set<String> listen = new HashSet<>();
        Set<String> see = new HashSet<>();

        for(int i=0; i<n; i++) {
            listen.add(br.readLine());
        }

        for(int i=0; i<m; i++) {
            see.add(br.readLine());
        }

        listen.retainAll(see);
        List<String> sortedList = new ArrayList<>(listen);
        Collections.sort(sortedList);

        System.out.println(sortedList.size());
        for (int i=0; i<sortedList.size(); i++) {
            System.out.println(sortedList.get(i));
        }
    }
}
