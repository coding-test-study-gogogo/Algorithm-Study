package week13.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 촌수계산 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static boolean[][] map;
    static boolean[] visited;
    static int start, end;
    static int answer;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        map = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[n1][n2] = map[n2][n1] = true;
        }

        dfs(start, 0);

        System.out.println((answer == 0) ? -1 : answer);
    }

    private static void dfs(int now, int depth) {
        if (now == end) {
            answer = depth;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (map[now][i] && !visited[i]) {
                visited[i] = true;
                dfs(i, depth + 1);
                visited[i] = false;
            }
        }
    }
}
