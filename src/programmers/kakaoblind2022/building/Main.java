package programmers.kakaoblind2022.building;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}
                                    , new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}})); // 10

        System.out.println(solution(new int[][] {{1,2,3},{4,5,6},{7,8,9}}
                                    , new int[][] {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}})); // 6
    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] acum = new int[board.length+1][board[0].length+1];

        for (int i=0; i<skill.length; i++) {
            int degree = skill[i][0] == 1 ? skill[i][5] * (-1) : skill[i][5];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3]+1;
            int c2 = skill[i][4]+1;

            acum[r1][c1] += degree;
            acum[r1][c2] += (-1) * degree;
            acum[r2][c1] += (-1) * degree;
            acum[r2][c2] += degree;
        }

        // 누적합 계산
        // 위에서 아래로
        for(int r=0; r<acum.length-1; r++) {
            for(int c=0; c<acum[0].length; c++) {
                acum[r+1][c] += acum[r][c];
            }
        }

        // 왼쪽에서 오른쪽으로
        for(int c=0; c<acum[0].length-1; c++) {
            for(int r=0; r<acum.length; r++) {
                acum[r][c+1] += acum[r][c];
            }
        }

        // 파괴되지 않은 건물 수
        for(int r=0; r<board.length; r++) {
            for(int c=0; c<board[0].length; c++) {
                if (board[r][c] + acum[r][c] <= 0)
                    continue;

                answer++;
            }
        }

        return answer;
    }

}
