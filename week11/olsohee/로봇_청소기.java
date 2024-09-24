package week11.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 로봇_청소기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static int dir;
    static int nowY, nowX;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        nowY = Integer.parseInt(st.nextToken());
        nowX = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // map[i][j] = -1(청소O), 0(청소X), 1(벽)
        while (true) {

            // 1. 현재 칸 청소
            if (map[nowY][nowX] == 0) {
                map[nowY][nowX] = -1;
            }

            // 2. 주변 네 칸 청소유무 확인
            boolean allClean = true;
            for (int i = 0; i < 4; i++) {
                int ny = nowY + dy[i];
                int nx = nowX + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                // 주변 네 칸 중 하나라도 청소되어 있지 않으면 체크하고 break
                if (map[ny][nx] == 0) {
                    allClean = false;
                    break;
                }
            }

            // 3. 후진 OR 주변 네 칸 중 청소되지 않은 칸으로 이동
            if (allClean) {
                if (!back()) {
                    break;
                }
            } else {
                while (true) {
                    dir--;
                    if (dir == -1) dir = 3;

                    if (go()) {
                        break;
                    }
                }
            }
        }

        // 청소된 칸 계산
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == -1) answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean go() {
        // 앞쪽이 청소되지 않은 빈 칸(0)이면 전진 후 return true
        // 앞쪽이 벽이거나 청소된 칸이면 return false
        switch (dir) {
            case 0: // 위로
                if (nowY - 1 >= 0 && map[nowY - 1][nowX] == 0) {
                    nowY--;
                    return true;
                }
                break;
            case 1: // 오른쪽으로
                if (nowX + 1 < m && map[nowY][nowX + 1] == 0) {
                    nowX++;
                    return true;
                }
                break;
            case 2: // 아래로
                if (nowY + 1 < n && map[nowY + 1][nowX] == 0) {
                    nowY++;
                    return true;
                }
                break;
            case 3: // 왼쪽으로
                if (nowX - 1 >= 0 && map[nowY][nowX - 1] == 0) {
                    nowX--;
                    return true;
                }
        }
        return false;
    }

    private static boolean back() {
        // 후진할 수 있으면 후진 후 return true
        // 벽(1)이면 return false
        switch (dir) {
            case 0: // 아래로
                if (nowY + 1 >= n || map[nowY + 1][nowX] == 1) return false;
                nowY++;
                break;
            case 1: // 왼쪽으로
                if (nowX - 1 < 0 || map[nowY][nowX - 1] == 1) return false;
                nowX--;
                break;
            case 2: // 위로
                if (nowY - 1 < 0 || map[nowY - 1][nowX] == 1) return false;
                nowY--;
                break;
            case 3: // 오른쪽으로
                if (nowX + 1 >= m || map[nowY][nowX + 1] == 1) return false;
                nowX++;
        }
        return true;
    }
}
