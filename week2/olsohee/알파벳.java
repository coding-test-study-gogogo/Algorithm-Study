package week2.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 알파벳 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = 0;
    static char[][] map;
    static int r, c;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[] visited = new boolean[26]; // 알파벳 26개

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 1; j <= c; j++) {
                map[i][j] = arr[j - 1];
            }
        }

        // 1, 1부터 출발
        dfs(1, 1, 1);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int cnt) {
        visited[(int) map[y][x] - 65] = true;

        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny <= 0 || ny > r || nx <= 0 || nx > c) {
                continue;
            }
            if (visited[(int) map[ny][nx] - 65]) {
                continue;
            }

            visited[(int) map[ny][nx] - 65] = true;
            dfs(ny, nx, cnt + 1);
            visited[(int) map[ny][nx] - 65] = false;
            flag = true;
        }

        if (!flag) { // 더이상 갈 수 없으면(4방향 다 이미 간 알파벳)
            answer = Math.max(answer, cnt);
        }
    }
}
