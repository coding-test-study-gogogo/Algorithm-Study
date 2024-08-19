package week7.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 사다리_조작 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, h;
    static boolean[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        addLadder(0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void addLadder(int cnt) {
        if (finish()) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (cnt == 3) {
            return;
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n - 1; j++) {
                // 해당 위치에 사다리가 있으면 pass
                if (map[i][j]) continue;

                // 사다리가 좌우에 있으면 pass
                if (map[i][j - 1] || map[i][j + 1]) continue;

                map[i][j] = true;
                addLadder(cnt + 1);
                map[i][j] = false;
            }
        }
    }

    private static boolean finish() {
        for (int i = 1; i <= n; i++) {
            int dest = start(1, i);
            if (dest != i) {
                return false;
            }
        }

        return true;
    }
    private static int start(int y, int x) {

        if (y == h + 1) {
            return x;
        }

        // 오른쪽으로 이동
        if (map[y][x]) {
            return start(y + 1, x + 1);
        }
        // 왼쪽으로 이동
        else if (map[y][x - 1]) {
            return start(y + 1, x - 1);
        }
        // 사다리 X, 아래로 이동
        else {
            return start(y + 1, x);
        }
    }
}
