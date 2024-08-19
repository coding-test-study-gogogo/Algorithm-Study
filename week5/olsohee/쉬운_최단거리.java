package week5.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 쉬운_최단거리 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static int[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int startY, startX;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startY = i;
                    startX = j;
                }
            }
        }

        bfs(startY, startX);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    if (map[i][j] == 0) sb.append(0 + " ");
                    else sb.append(-1 + " ");
                } else {
                    sb.append(visited[i][j] - 1 + " ");
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static void bfs(int y, int x) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        visited[y][x] = 1;

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (visited[ny][nx] != 0 || map[ny][nx] == 0) continue;
                que.add(new Node(ny, nx));
                visited[ny][nx] = visited[now.y][now.x] + 1;
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
