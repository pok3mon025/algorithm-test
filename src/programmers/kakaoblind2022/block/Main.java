package programmers.kakaoblind2022.block;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"muzi", "frodo", "apeach", "neo"}
        , new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2));

        System.out.println(solution(new String[] {"con", "ryan"}
            , new String[] {"ryan con", "ryan con", "ryan con", "ryan con"}, 3));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 중복 제거
        String[] distinctArr = Arrays.stream(report).distinct().toArray(String[]::new);

        // 신고 맵
        Map<String, Set<String>> reportMap = new HashMap<>();

        // 정지 유저
        Map<String, Integer> blockMap = new HashMap<>();
        Set<String> blockId = new HashSet<>();

        for (String rep : distinctArr) {
            String[] split = rep.split(" ");
            blockMap.put(split[1], blockMap.getOrDefault(split[1], 0) + 1);

            Set<String> re = reportMap.getOrDefault(split[0], new HashSet<>());
            re.add(split[1]);
            reportMap.put(split[0], re);
        }

        // 정지된 유저 확인
        for (Map.Entry<String, Integer> entry : blockMap.entrySet()) {
            if (entry.getValue() < k)
                continue;

            blockId.add(entry.getKey());
        }

        // 정지된 유저를 신고한 모든 유저에게 메일 발송
        for (int i=0; i<id_list.length; i++) {
            if (!reportMap.containsKey(id_list[i])) {
                answer[i] = 0;
                continue;
            }

            reportMap.get(id_list[i]).retainAll(blockId);
            answer[i] = reportMap.get(id_list[i]).size();
        }

        return answer;
    }
}
