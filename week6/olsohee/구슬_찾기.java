package week6.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 구슬_찾기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] visited;
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            map[num1][num2] = -1;
            map[num2][num1] = 1;
        }

        int answer = 0;

        // i보다 큰 구슬 찾기
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            dfs(i, 1);
            if (isAnswer()) answer++;
        }

        // i보다 작은 구슬 찾기
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            dfs(i, -1);
            if (isAnswer()) answer++;
        }

        System.out.println(answer);
    }

    private static void dfs(int start, int flag) {
        for (int i = 1; i <= n; i++) {
            if (map[start][i] == flag && !visited[i]) {
                visited[i] = true;
                dfs(i, flag);
            }
        }
    }

    private static boolean isAnswer() {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[i]) cnt++;
        }

        return cnt >= (n + 1) / 2;
    }
}
