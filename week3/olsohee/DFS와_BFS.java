package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS와_BFS {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static boolean[][] map;
    static boolean[] visited;
    static List<Integer> dfsRoute = new ArrayList<>();
    static List<Integer> bfsRoute = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        map = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            map[node1][node2] = map[node2][node1] = true;
        }

        dfs(v);
        visited = new boolean[n + 1];
        bfs(v);

        for (Integer integer : dfsRoute) {
            System.out.print(integer + " ");
        }
        System.out.println();
        for (Integer integer : bfsRoute) {
            System.out.print(integer + " ");
        }
    }

    private static void dfs(int start) {

        visited[start] = true;
        dfsRoute.add(start);

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            if (map[start][i]) {
                dfs(i);
            }
        }
    }

    private static void bfs(int start) {

        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;
        bfsRoute.add(start);

        while (!que.isEmpty()) {
            Integer now = que.poll();
            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                if (map[now][i]) {
                    que.add(i);
                    visited[i] = true;
                    bfsRoute.add(i);
                }
            }
        }
    }
}
