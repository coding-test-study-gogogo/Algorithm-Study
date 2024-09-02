package week8.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 알고스팟 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int m; // 가로
    static int n; // 세로
    static int[][] map;
    static boolean[][][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[10000][n][m];
        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = arr[j] - '0';
            }
        }

        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.breakCnt - o2.breakCnt);
        que.add(new Node(0, 0, 0));
        visited[0][0][0] = true;
        int answer = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.y == n - 1 && now.x == m - 1) {
                answer = now.breakCnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;

                // 길인 경우
                if (map[ny][nx] == 0) {
                    if (!visited[now.breakCnt][ny][nx]) {
                        visited[now.breakCnt][ny][nx] = true;
                        que.add(new Node(ny, nx, now.breakCnt));
                    }
                }

                // 벽인 경우
                if (map[ny][nx] == 1) {
                    if (!visited[now.breakCnt + 1][ny][nx]) {
                        visited[now.breakCnt + 1][ny][nx] = true;
                        que.add(new Node(ny, nx, now.breakCnt + 1));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static class Node {
        int y, x;
        int breakCnt;

        public Node(int y, int x, int breakCnt) {
            this.y = y;
            this.x = x;
            this.breakCnt = breakCnt;
        }
    }
}
