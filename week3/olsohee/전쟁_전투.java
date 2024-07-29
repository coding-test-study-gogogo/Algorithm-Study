package week3.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 전쟁_전투 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int myCnt = 0;
    static int yourCnt = 0;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = arr[j];
            }
        }

        // bfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(myCnt + " " + yourCnt);
    }

    private static void bfs(int y, int x) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        visited[y][x] = true;

        int cnt = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int ny = dy[i]+ now.y;
                int nx = dx[i]+ now.x;
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[ny][nx]) continue;
                if (map[now.y][now.x] == map[ny][nx]) {
                    que.add(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }

        if (map[y][x] == 'W') {
            myCnt += cnt * cnt;
        } else {
            yourCnt += cnt * cnt;
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
