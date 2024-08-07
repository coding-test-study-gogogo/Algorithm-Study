package week4.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 토마토 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, h;
    static int[][][] map;
    static boolean[][][] visited;
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dx = {0, 0, 1, -1,0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static int answer;
    static Queue<Node> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][n][m];
        visited = new boolean[h][n][m];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // bfs
        boolean flag = true;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[i][j][k] == 1) {
                        que.add(new Node(i, j, k));
                        visited[i][j][k] = true;
                    } else {
                        flag = false;
                    }
                }
            }
        }

        // 이미 다 익어있는 상태이면 0
        if (flag) {
            System.out.println(0);
            return;
        }

        // bfs
        bfs();

        // 토마토가 모두 익지 못하면 -1
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[i][j][k] == -1) continue;

                    if (map[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        // 토마토가 모두 익는데 걸린 일수
        if (answer == 0) {
            System.out.println(0);
        } else {
            System.out.println(answer - 1);
        }
    }

    private static void bfs() {

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 6; i++) {
                int nh = dh[i] + now.h;
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;

                if (nh < 0 || nh >= h || ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (visited[nh][ny][nx]) continue;

                if (map[nh][ny][nx] == 0) {
                    que.add(new Node(nh, ny, nx));
                    visited[nh][ny][nx] = true;
                    map[nh][ny][nx] = map[now.h][now.y][now.x] + 1;
                    answer = Math.max(answer, map[nh][ny][nx]);
                }
            }
        }
    }

    private static class Node {
        int h, y, x;

        public Node(int h, int y, int x) {
            this.h = h;
            this.y = y;
            this.x = x;
        }
    }
}
