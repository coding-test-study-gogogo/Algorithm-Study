package week12.olsohee;

import java.util.*;

class 당구_연습 {

    int m, n;
    int[] answer;

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        this.m = m;
        this.n = n;
        answer = new int[balls.length];

        Node start = new Node(startX, startY);
        for (int i = 0; i < balls.length; i++) {
            int[] ball = balls[i];
            Node dest = new Node(ball[0], ball[1]);

            // 위, 아래, 오른쪽, 왼쪽에 닿을 벽 리스트
            int min = Integer.MAX_VALUE;
            for (double result : getList(start, dest)) {
                min = Math.min(min, (int)result);
            }

            answer[i] = min;
        }

        return answer;
    }

    private List<Double> getList(Node start, Node dest) {
        List<Double> list = new ArrayList<>();

        // 위
        if (!((start.x == dest.x) && (start.y < dest.y))) {
            int xDiff = Math.max(start.x, dest.x) - Math.min(start.x, dest.x);
            int yDiff = (n - start.y) + (n - dest.y);
            list.add(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)); // 이동 거리
        }

        // 아래
        if (!((start.x == dest.x) && (start.y > dest.y))) {
            int xDiff = Math.max(start.x, dest.x) - Math.min(start.x, dest.x);
            int yDiff = start.y + dest.y;
            list.add(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)); // 이동 거리
        }

        // 오른쪽
        if (!((start.y == dest.y) && (start.x < dest.x))) {
            int yDiff = Math.max(start.y, dest.y) - Math.min(start.y, dest.y);
            int xDiff = (m - start.x) + (m - dest.x);
            list.add(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)); // 이동 거리
        }

        // 왼쪽
        if (!((start.y == dest.y) && (start.x > dest.x))) {
            int yDiff = Math.max(start.y, dest.y) - Math.min(start.y, dest.y);
            int xDiff = start.x + dest.x;
            list.add(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)); // 이동 거리
        }

        return list;
    }

    private class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
