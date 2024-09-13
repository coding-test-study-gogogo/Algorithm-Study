package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 알고스팟 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 가로
        int n = Integer.parseInt(st.nextToken()); // 세로

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        int[][] cost = new int[n][m]; // cost[i][j]: i, j 위치까지 벽을 부스는 최소 개수
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[0][0] = 0;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0));
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        int answer = Integer.MAX_VALUE;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.y == n - 1 && now.x == m - 1) {
                answer = Math.min(answer, cost[n - 1][m - 1]);
            }

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= m ) continue;

                // 길인 경우
                if (map[ny][nx] == 0) {
                    if(cost[now.y][now.x] < cost[ny][nx]) {
                        cost[ny][nx] = cost[now.y][now.x];
                        que.add(new Node(ny, nx));
                    }
                }
                // 벽인 경우
                else {
                    if(cost[now.y][now.x] + 1 < cost[ny][nx]) {
                        cost[ny][nx] = cost[now.y][now.x] + 1;
                        que.add(new Node(ny, nx));
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
