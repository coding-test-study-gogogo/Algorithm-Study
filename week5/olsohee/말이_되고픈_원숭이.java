package week5.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 말이_되고픈_원숭이 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer;
    static int[] dx1 = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy1 = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] dx2 = {1, -1, 0, 0};
    static int[] dy2 = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][] map = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] visited = new int[k + 1][h][w];

        // bfs
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.x == w - 1 && now.y == h - 1) {
                answer = visited[now.kCnt][now.y][now.x];
                break;
            }

            // 말 이동 방식
            for (int i = 0; i < 8; i++) {
                if (now.kCnt + 1 > k) break;

                int nx = now.x + dx1[i];
                int ny = now.y + dy1[i];

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;

                if (map[ny][nx] == 0 && visited[now.kCnt + 1][ny][nx] == 0) {
                    que.add(new Node(ny, nx, now.kCnt + 1));
                    visited[now.kCnt + 1][ny][nx] = visited[now.kCnt][now.y][now.x] + 1;
                }
            }

            // 기본 이동 방식
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx2[i];
                int ny = now.y + dy2[i];

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;

                if (map[ny][nx] == 0 && visited[now.kCnt][ny][nx] == 0) {
                    que.add(new Node(ny, nx, now.kCnt));
                    visited[now.kCnt][ny][nx] = visited[now.kCnt][now.y][now.x] + 1;
                }
            }
        }
        answer = (answer == 0) ? -1 : --answer;
        System.out.println(answer);
    }

    public static class Node {
        int y, x;
        int kCnt;

        public Node(int y, int x, int kCnt) {
            this.y = y;
            this.x = x;
            this.kCnt = kCnt;
        }
    }
}
