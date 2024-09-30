package week12.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인구_이동 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int l, r;
    static int[][] map;
    static int[][] visited;
    static boolean flag; // 인구이동 유무
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            flag = false;
            int num = 1;

            // 국가들 구분하기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        dfs(i, j, num);
                        num++;
                    }
                }
            }

            if (!flag) {
                break;
            }

            answer++;

            // 인구이동
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] != 0) {
                        bfs(i, j, visited[i][j]);
                    }
                }
            }
        }

        System.out.println(answer);
    }


    private static void bfs(int y, int x, int num) {

        List<Node> list = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        list.add(new Node(y, x));
        visited[y][x] = 0; // 방문처리(visited = 0)
        int cnt = 1;
        int sum = map[y][x];

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

                if (visited[ny][nx] == num) {
                    visited[ny][nx] = 0;
                    que.add(new Node(ny, nx));
                    list.add(new Node(ny, nx));
                    cnt++;
                    sum += map[ny][nx];
                }
            }
        }

        // 해당 구역 인구이동하기
        int value = sum / cnt;
        for (Node node : list) {
            map[node.y][node.x] = value;
        }
    }

    private static void dfs(int y, int x, int num) {
        visited[y][x] = num;

        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
            if (visited[ny][nx] != 0) continue;

            int diff = Math.abs(map[y][x] - map[ny][nx]);
            if (diff >= l && diff <= r) {
                visited[ny][nx] = num;
                flag = true;
                dfs(ny, nx, num);
            }
        }
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
