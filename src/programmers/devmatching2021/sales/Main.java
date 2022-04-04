package programmers.devmatching2021.sales;

import java.util.Arrays;

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
        Tree sales = new Tree(enroll.length, enroll, referral);

        for (int i=0; i<seller.length; i++) {
            if (amount[i] == 0)
                continue;

            sales.calAmt(seller[i], amount[i]);
        }

        return Arrays.stream(sales.nodes).map( x -> x.sumAmt).mapToInt(Integer::intValue).skip(1).toArray();
    }

    public static class Node {
        String name;
        int sumAmt;
        Node parent;

        public Node(String name) {
            this.name = name;
        }
    }

    public static class Tree {
        Node[] nodes;

        public Tree (int size, String[] name, String[] parent) {
            nodes = new Node[size+1];
            // center
            nodes[0] = new Node("center");

            for (int i=1; i<=size; i++) {
                nodes[i] = new Node(name[i-1]);
            }

            for (int i=1; i<=size; i++) {
                String parentName = parent[i-1];

                if ("-".equals(parentName)) {
                    nodes[i].parent = nodes[0];
                    continue;
                }

                nodes[i].parent = getNode(parentName);
            }
        }

        public Node getNode(String name) {
            return  Arrays.stream(nodes).filter(n -> name.equals(n.name)).findFirst().get();
        }

        public void calAmt(String sellerName, int amount) {
            Node target = getNode(sellerName);
            int amt = 100 * amount;

            while (true) {
                if (amt == 0 || "center".equals(target.name))
                    break;

                int parentAmt = (int)(amt * 0.1);

                if (parentAmt < 1) {
                    target.sumAmt += amt;
                    break;
                }

                target.sumAmt += amt - parentAmt;
                amt = parentAmt;
                target = target.parent;
            }

        }
    }
}
