package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 로봇_청소기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 세로
    static int m; // 가로
    static int[][] map;
    static int y, x;
    static int dir;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // map의 값: -1=청소O, 0=청소X, 1=벽
        int cleanCount = 0;
        while (true) {
            // 현재 위치 청소하기
            if (map[y][x] == 0) {
                map[y][x] = -1;
                cleanCount++;
            }

            // 주변 4칸 확인
            boolean canGo = false;
            for (int i = 0; i < 4; i++) {
                dir--;
                if (dir == -1) dir = 3;

                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (map[ny][nx] == 0) { // 주변 4칸 중 청소되지 않은 칸으로 전진
                    canGo = true;
                    y = ny;
                    x = nx;
                    break;
                }
            }

            // 주변 4칸이 벽 or 청소되었으면 후진
            if (!canGo) {
                int backDir = (dir + 2) % 4;
                int ny = y + dy[backDir];
                int nx = x + dx[backDir];
                if (map[ny][nx] != 1) { // 벽이 아니면 후진 (방향은 바뀌지 않음)
                    y = ny;
                    x = nx;
                } else { // 후진 봇할 경우 끝내기
                    break;
                }
            }
        }

        System.out.println(cleanCount);
    }
}
