package week4.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 바이러스 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] map;
    static boolean[] visited;
    static int n;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];
        int cnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[n1][n2] = true;
            map[n2][n1] = true;
        }

        dfs(1);

        System.out.println(--answer);
    }

    private static void dfs(int start) {
        visited[start] = true;
        answer++;

        for (int i = 1; i <= n; i++) {
            if (map[start][i] && !visited[i]) {
                dfs(i);
            }
        }
    }
}
