package week12.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소문난칠공주 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer;
    static char[] map;
    static int[] choiced = new int[7];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        map = new char[25];

        for (int i = 0; i < 5; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                map[i * 5 + j] = input[j];
            }
        }

        // 25개 중 7개 고르기
        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int start, int depth, int yCnt) {
        // 임도연 파가 4명 이상인 경우
        if (yCnt == 4) return;

        // 7명을 다 고른 경우
        if (depth == 7) {
            if (canAnswer()) answer++;
            return;
        }
        for (int i = start; i < 25; i++) {
            choiced[depth] = i;
            if (map[i] == 'Y') {
                dfs(i + 1, depth + 1, yCnt + 1);
            } else {
                dfs(i + 1, depth + 1, yCnt);
            }
        }
    }

    private static boolean canAnswer() {
        // bfs로 choiced 7명이 모두 인접해있는지 확인
        // choiced[0]을 시작으로 bfs (끝났을 때 cnt가 7이어야 모두 인접한 것임)

        boolean[][] visited = new boolean[5][5];

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(choiced[0] / 5, choiced[0] % 5));
        visited[choiced[0] / 5][choiced[0] % 5] = true;
        int cnt = 1;

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) {
                    continue;
                }

                if (visited[ny][nx]) continue;

                for (int j = 0; j < 7; j++) {
                    if (choiced[j] == ny * 5 + nx) {
                        que.add(new Node(ny, nx));
                        visited[ny][nx] = true;
                        cnt++;
                        break;
                    }
                }
            }
        }

        return cnt == 7;
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
