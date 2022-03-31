package programmers.devmatching2021.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        print(solution(new int[] {44, 1, 0, 0, 31, 25}, new int[] {31, 10, 45, 1, 6, 19})); // 3, 5
        print(solution(new int[] {0, 0, 0, 0, 0, 0}, new int[] {38, 19, 20, 40, 15, 25})); // 1, 6
        print(solution(new int[] {45, 4, 35, 20, 3, 9}, new int[] {20, 9, 3, 45, 4, 35})); // 1, 1
    }

    public static void print(int[] array) {
        System.out.println("array = " + Arrays.toString(array));
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount = (int) Arrays.stream(lottos).filter(x -> x == 0).count();

        // 리스트로 변환
        List<Integer> selectNumber = Arrays.stream(lottos).boxed().collect(Collectors.toList());
        List<Integer> winNumber = Arrays.stream(win_nums).boxed().collect(Collectors.toList());

        // 일치하지 않는 번호 제거
        selectNumber.retainAll(winNumber);

        return new int[] {getRank(selectNumber.size() + zeroCount), getRank(selectNumber.size())};
    }

    public static int getRank(int count) {
        return count > 1 ? 7 - count : 6;
    }
}
