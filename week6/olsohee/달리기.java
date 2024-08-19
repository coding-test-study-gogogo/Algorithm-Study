package week6.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 달리기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k;
    static char[][] map;
    static int[][] visited;
    static int startY, startX, destY, destX;
    static int answer = -1;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n + 1][m + 1];
        visited = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                map[i][j] = arr[j - 1];
            }
        }
        for (int[] v : visited) {
            Arrays.fill(v, Integer.MAX_VALUE);
        }

        st = new StringTokenizer(br.readLine());
        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        destY = Integer.parseInt(st.nextToken());
        destX = Integer.parseInt(st.nextToken());

        bfs(startY, startX);
        System.out.println(answer);
    }

    private static void bfs(int y, int x) {
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x, 0));
        visited[y][x] = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.y == destY && now.x == destX) {
                answer = visited[now.y][now.x];
                return;
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= k; j++) {
                    int ny = now.y + dy[i] * j;
                    int nx = now.x + dx[i] * j;

                    if (nx <= 0 || nx > m || ny <= 0 || ny > n) break;
                    if (map[ny][nx] == '#') break;

                    if (visited[ny][nx] == Integer.MAX_VALUE) {
                        visited[ny][nx] = now.time + 1;
                        que.add(new Node(ny, nx, now.time + 1));
                    } else if (visited[ny][nx] == now.time + 1) {
                        continue;
                    } else break;
                }
            }
        }
    }

    private static class Node {

        int y, x;
        int time;

        public Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }
}
