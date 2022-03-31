package programmers.devmatching2021.rotation;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        print(solution(6, 6, new int[][] {{2,2,5,4}, {3,3,6,6}, {5,1,6,3}})); // [8, 10, 25]
        print(solution(3, 3, new int[][] {{1,1,2,2}, {1,2,2,3}, {2,1,3,2}, {2,2,3,3}})); // [1, 1, 5, 3]
        print(solution(100, 97, new int[][] {{1,1,100,97}})); // [1]
    }

    public static void print(int[] array) {
        System.out.println("array = " + Arrays.toString(array));
    }

    public static void print(int[][] array) {
        for(int i=0; i<array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
        System.out.println();
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] matrix = new int[rows][columns];
        int num = 0;

        // 행렬 초기화
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                matrix[i][j] = ++num;
            }
        }

        // 행렬 회전
        for (int i=0; i<queries.length; i++) {

            int r1 = queries[i][0] - 1;
            int c1 = queries[i][1] - 1;
            int r2 = queries[i][2] - 1;
            int c2 = queries[i][3] - 1;

            int startNumber = matrix[r1][c1];
            int min = startNumber;

            for (int r=r1; r<r2; r++) {
                min = Math.min(min, matrix[r][c1]);
                matrix[r][c1] = matrix[r+1][c1];
            }

            for (int c=c1; c<c2; c++) {
                min = Math.min(min, matrix[r2][c]);
                matrix[r2][c] = matrix[r2][c+1];
            }

            for (int r=r2; r>r1; r--) {
                min = Math.min(min, matrix[r][c2]);
                matrix[r][c2] = matrix[r-1][c2];
            }

            for (int c=c2; c>c1; c--) {
                min = Math.min(min, matrix[r1][c]);
                matrix[r1][c] = matrix[r1][c-1];
            }

            matrix[r1][c1+1] = startNumber;
            answer[i] = min;
        }

        return answer;
    }
}
