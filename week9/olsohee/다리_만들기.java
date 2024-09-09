package week9.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 다리_만들기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static Set<Node> edgeNodeSet = new HashSet<>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 각 섬 구분 짓기, 각 섬의 가장자리를 set에 담기
        int islandNum = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    bfs1(i, j, ++islandNum); // 섬 번호: 2, 3, 4, ...
                }
            }
        }

        // 2. 각 가장자리에서 bfs 시작
        for (Node edgeNode : edgeNodeSet) {
            bfs2(edgeNode.y, edgeNode.x, map[edgeNode.y][edgeNode.x]);
        }

        System.out.println(answer);
    }

    private static void bfs1(int y, int x, int islandNum) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        map[y][x] = islandNum;

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                // 같은 섬인 경우
                if (map[ny][nx] == 1) {
                    map[ny][nx] = islandNum;
                    que.add(new Node(ny, nx));
                }
                // 바다인 경우 (= now가 섬의 가장자리)
                if (map[ny][nx] == 0) {
                    edgeNodeSet.add(new Node(now.y, now.x));
                }
            }
        }
    }

    private static void bfs2(int y, int x, int startIslandNum) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        int[][] visited = new int[n][n];
        visited[y][x] = 1;

        while (!que.isEmpty()) {
            Node now = que.poll();

            // 다른 섬인 경우 (도착!)
            if (map[now.y][now.x] != 0 && map[now.y][now.x] != startIslandNum) {
                answer = Math.min(answer, visited[now.y][now.x] - 2);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

                // 같은 섬인 경우
                if (map[ny][nx] == startIslandNum) continue;

                // 이미 방문한 경우
                if (visited[ny][nx] != 0) continue;

                // 바다이거나 다른 섬이면 이동
                visited[ny][nx] = visited[now.y][now.x] + 1;
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
