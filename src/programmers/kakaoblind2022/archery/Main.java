package programmers.kakaoblind2022.archery;


public class Main {
    private static int maxScore = 0;
    private static int[] answer = new int[11];
    private static int[] ryan = new int[11];

    public static void main(String[] args) {
        print(solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0})); // 0,2,2,0,1,0,0,0,0,0,0
        print(solution(1, new int[] {1,0,0,0,0,0,0,0,0,0,0})); // -1
        print(solution(9, new int[] {0,0,1,2,0,1,1,1,1,1,1})); // 1,1,2,0,1,2,2,0,0,0,0
        print(solution(10, new int[] {0,0,0,0,0,0,0,0,3,4,3})); // 1,1,1,1,1,1,1,1,0,0,2
    }

    private static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] solution(int n, int[] info) {
        int[] loose = {-1};
        int use = 0;

        // 어피치 점수 계산
        dfs(0, n, use, info, 0, 0); // idx, 제공된 화살의 갯수, 사용한 화살의 갯수, 나의 점수, 상대 점수

        return (maxScore == 0) ? loose : answer;
    }

    // 어피치가 쏜 과녁 + 1 or 0
    private static void dfs(int idx, int n, int use, int[] info, int ryanScore, int apeachScore) {

        if (idx == 11) {
            // 점수 계산
            if (n >= use && ryanScore > apeachScore && (ryanScore - apeachScore) >= maxScore) {
                if (n > use) {
                    ryan[10] = n - use;
                }

                if ((ryanScore - apeachScore) == maxScore) { // 우승할 수 있는 방법이 여러 가지 일 경우
                    // 가장 낮은 점수를 더 많이 맞힌 경우
                    for (int i=10; i >= 0; i--) {
                        if (answer[i] > ryan[i])
                            break;

                        if (ryan[i] > answer[i]) {
                            answer = ryan.clone();
                            break;
                        }
                    }

                } else {
                    maxScore = ryanScore - apeachScore;
                    answer = ryan.clone();
                }
            }
            return;
        }

        int score = 10 - idx;

        // 과녁을 맞추는 경우
        if (n >= use + info[idx] + 1) {
            ryan[idx] = info[idx] + 1; // 쏜 과녁의 수 세팅

            dfs(idx + 1, n, use + ryan[idx], info, ryanScore + score, apeachScore);

        }

        // 안맞추는 경우
        ryan[idx] = 0;

        if (info[idx] != 0) {
            dfs(idx + 1, n, use, info, ryanScore, apeachScore + score);
        } else {
            dfs(idx + 1, n, use, info, ryanScore, apeachScore);
        }
    }
}
