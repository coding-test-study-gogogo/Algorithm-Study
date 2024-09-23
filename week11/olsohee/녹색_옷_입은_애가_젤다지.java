package week11.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 녹색_옷_입은_애가_젤다지 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        int round = 0;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            round++;

            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] cost = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(cost[i], Integer.MAX_VALUE);
            }

            cost[0][0] = map[0][0];
            Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
            que.add(new Node(0, 0, cost[0][0]));
            while (!que.isEmpty()) {
                Node now = que.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = now.y + dy[i];
                    int nx = now.x + dx[i];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if (cost[ny][nx] > now.cost + map[ny][nx]) {
                        cost[ny][nx] = now.cost + map[ny][nx];
                        que.add(new Node(ny, nx, cost[ny][nx]));
                    }
                }
            }

            System.out.println("Problem " + round + ": " + cost[n - 1][n - 1]);
        }
    }

    private static class Node {
        int y, x, cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
}
