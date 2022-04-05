package programmers.devmatching2021.sales;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        print(solution(new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
                        , new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
                        , new String[] {"young", "john", "tod", "emily", "mary"}
                        , new int[] {12, 4, 2, 5, 10}));
        // [360, 958, 108, 0, 450, 18, 180, 1080]

        print(solution(new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}
                        , new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}
                        , new String[] {"sam", "emily", "jaimie", "edward"}
                        , new int[] {2, 3, 5, 4}));
        // [0, 110, 378, 180, 270, 450, 0, 0]
    }

    private static void print(int[] solution) {
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Sales> childMap = new HashMap<>();
        Map<String, Sales> parentMap = new HashMap<>();

        childMap.put("-", new Sales("-"));

        for(int i=0; i<enroll.length; i++) {
            childMap.put(enroll[i], new Sales(enroll[i]));
            parentMap.put(enroll[i], childMap.getOrDefault(referral[i], null));
        }

        // 판매 수익 계산
        for(int i=0; i<seller.length; i++) {
            String sellerName = seller[i];
            int amt = amount[i] * 100;

            while (true) {
                if (parentMap.get(sellerName) == null || amt < 1) {
                    childMap.get(sellerName).amount += amt;
                    break;
                }

                int parentAmt = (int)(amt * 0.1);
                amt = amt - parentAmt;
                childMap.get(sellerName).amount += amt;

                amt = parentAmt;
                sellerName = parentMap.get(sellerName).name;
            }
        }

        for (int i=0; i<enroll.length; i++) {
            answer[i] = childMap.get(enroll[i]).amount;
        }

        return answer;
    }

    public static class Sales {
        int amount;
        String name;

        public Sales(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Sales sales = (Sales)o;
            return Objects.equals(name, sales.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
