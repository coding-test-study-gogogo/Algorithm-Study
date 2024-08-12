package week5.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 토마토 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Point> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new int[n][m];

        boolean isAllZero = false;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    isAllZero = false;
                }
                if (map[i][j] == 1) {
                    que.add(new Point(i, j));
                    visited[i][j] = 1;
                }
            }
        }

        if (isAllZero) {
            System.out.println(0);
            return;
        }

        bfs();

        int maxDay = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 익지 않은 토마토(0)가 있으면 -1 출력
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                maxDay = Math.max(maxDay, visited[i][j]);
            }
        }
        System.out.println(maxDay - 1);
    }

    private static void bfs() {

        while (!que.isEmpty()) {
            Point tomato = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = tomato.y + dy[i];
                int nx = tomato.x + dx[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                if (visited[ny][nx] != 0) {
                    continue;
                }

                if (map[ny][nx] == 0) {
                    visited[ny][nx] = visited[tomato.y][tomato.x] + 1;
                    que.add(new Point(ny, nx));
                    map[ny][nx] = 1;
                }
            }
        }
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
