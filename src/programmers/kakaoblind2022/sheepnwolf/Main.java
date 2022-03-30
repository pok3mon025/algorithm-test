package programmers.kakaoblind2022.sheepnwolf;

import java.util.LinkedList;

public class Main {
    private static int max = 0;

    public static void main(String[] args) {
        System.out.println(solution(new int[] {0,0,1,1,1,0,1,0,1,0,1,1},
            new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}}));    // 5

        max = 0;

        System.out.println(solution(new int[] {0,1,0,1,1,0,1,0,0,1,0},
            new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}})); // 5

    }

    public static int solution(int[] info, int[][] edges) {
        Tree tree = new Tree(info.length, info);

        for(int i=0; i<edges.length; i++) {
            tree.addEdge(edges[i][0], edges[i][1]);
        }

        // 다음으로 갈 수 있는 노드
        LinkedList<Node> nextNode = new LinkedList<Node>();

        dfs(tree.nodes[0], 0, 0, nextNode);

        return max;
    }

    static void dfs(Node node, int sheep, int wolf, LinkedList<Node> nextNode) {
        if (node == null) return;

        sheep += node.type ^ 1;
        wolf += node.type;

        if (sheep <= wolf) return;

        max = Math.max(max, sheep);

        node.visited = true;

        nextNode.addAll(node.child);
        nextNode.remove(node);

        for (Node next : nextNode) {
            dfs(next, sheep, wolf, (LinkedList<Node>) nextNode.clone());
        }
    }

    public static class Node {
        int data;
        int type; // 양 or 늑대
        LinkedList<Node> child; // 자식 노드
        boolean visited;

        public Node(int data, int type) {
            this.data = data;
            this.type = type;
            this.visited = false;
            child = new LinkedList<>();
        }
    }

    public static class Tree {
        Node[] nodes;

        public Tree(int size, int[] types) {
            nodes = new Node[size];
            for (int i=0; i<size; i++) {
                nodes[i] = new Node(i, types[i]);
            }
        }

        void addEdge(int i1, int i2) {
            Node n1 = nodes[i1];
            Node n2 = nodes[i2];

            if(!n1.child.contains(n2)) {
                n1.child.add(n2);
            }
        }
    }
}
