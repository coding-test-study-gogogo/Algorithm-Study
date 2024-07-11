package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 빙산 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {

            int cnt = getIslandCnt();
            if (cnt == 0) {
                answer = 0;
                break;
            }
            else if (cnt > 1) {
                break;
            }
            else if (cnt == 1) {
                melt();
                answer++;
            }
        }

        // 두 덩이 이상으로 분리되는 최초의 시간을 구하기
        System.out.println(answer);
    }

    private static int getIslandCnt() {
        int cnt = 0;
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void bfs(int startY, int startX) {
        Queue<Node> que = new LinkedList<>();
        visited[startY][startX] = true;
        que.add(new Node(startY, startX));

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;

                if (ny < 0 || ny >= n || nx < 0 || nx >= m || visited[ny][nx]) {
                    continue;
                }

                if (map[ny][nx] > 0) {
                    que.add(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
    }

    private static void melt() {
        int[][] newMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                int seaCnt = check(i, j);
                newMap[i][j] = (map[i][j] - seaCnt < 0) ? 0 : map[i][j] - seaCnt;
            }
        }

        map = newMap;
    }

    private static int check(int y, int x) {
        int seaCnt = 0;

        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                continue;
            }

            if (map[ny][nx] == 0) {
                seaCnt++;
            }
        }

        return seaCnt;
    }

    private static class Node {

        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
