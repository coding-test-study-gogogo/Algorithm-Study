package week5.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 보물섬 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int yLen, xLen;
    static char[][] map;
    static int[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        // 최단 거리로 이동, 재방문 X
        st = new StringTokenizer(br.readLine());
        yLen = Integer.parseInt(st.nextToken());
        xLen = Integer.parseInt(st.nextToken());

        map = new char[yLen][xLen];

        for (int i = 0; i < yLen; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                if (map[i][j] == 'L') {
                    visited = new int[yLen][xLen];
                    bfs(i, j);
                }
            }
        }

        System.out.println(--max);
    }

    private static void bfs(int y, int x) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        visited[y][x] = 1;
        max = Math.max(max, 1);

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;

                if (ny < 0 || ny >= yLen || nx < 0 || nx >= xLen) continue;
                if (visited[ny][nx] != 0 || map[ny][nx] != 'L') continue;

                visited[ny][nx] = visited[now.y][now.x] + 1;
                max = Math.max(max, visited[ny][nx]);
                que.add(new Node(ny, nx));
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
