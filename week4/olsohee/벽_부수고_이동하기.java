package week4.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 벽_부수고_이동하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = arr[j] - '0';
            }
        }

        // bfs
        int answer = -1;
        boolean[][][] visited = new boolean[2][n][m];
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.y == n - 1 && now.x == m - 1) {
                answer = now.cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;

                // 벽인 경우, 부술 수 있으면 부수고 이동
                if (map[ny][nx] == 1) {
                    if (now.breakCnt >= 1) {
                        continue;
                    }
                    if (!visited[now.breakCnt + 1][ny][nx]) {
                        que.add(new Node(ny, nx, now.cnt + 1, now.breakCnt + 1));
                        visited[now.breakCnt + 1][ny][nx] = true;
                    }
                }

                // 길인 경우
                else {
                    if (!visited[now.breakCnt][ny][nx]) {
                        que.add(new Node(ny, nx, now.cnt + 1, now.breakCnt));
                        visited[now.breakCnt][ny][nx] = true;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static class Node {
        int y, x;
        int cnt; // y, x까지 오는데 걸린 경로 수
        int breakCnt;
        public Node(int y, int x, int cnt, int breakCnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.breakCnt = breakCnt;
        }
    }
}
