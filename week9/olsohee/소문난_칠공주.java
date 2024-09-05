package week9.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소문난_칠공주 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] map = new char[5][5];
    static int answer = 0;
    static boolean[][] visited = new boolean[5][5];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 1. 25개 중 7개 고르기 (조합 만들기)
        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int start, int sCnt, int yCnt) {

        if (yCnt == 4) {
            return;
        }
        if (sCnt + yCnt == 7) {
            // 2. 각 조합의 7개가 모두 붙어있는지 확인
            if (sCnt >= 4) {
                if (canAnswer()) answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            visited[i / 5][i % 5] = true;

            if (map[i / 5][i % 5] == 'S') {
                dfs(i + 1, sCnt + 1, yCnt);
            } else {
                dfs(i + 1, sCnt, yCnt + 1);
            }

            visited[i / 5][i % 5] = false;
        }
    }

    private static boolean canAnswer() {
        int cnt = 0;

        outer: for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    cnt = bfs(i, j); // 붙어있는 갯수
                    break outer;
                }
            }
        }
        return cnt == 7;
    }

    private static int bfs(int y, int x) {
        boolean[][] copyVisited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            copyVisited[i] = visited[i].clone();
        }

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        copyVisited[y][x] = false;
        int cnt = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;
                if (copyVisited[ny][nx]) {
                    copyVisited[ny][nx] = false;
                    que.add(new Node(ny, nx));
                }
            }
        }

        return cnt;
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
