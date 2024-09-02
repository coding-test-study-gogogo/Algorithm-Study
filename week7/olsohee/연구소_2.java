package week7.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] map;
    static List<Node> virusList = new ArrayList<>(); // 바이러스를 놓을 수 있는 위치 리스트
    static boolean[] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new Node(i, j));
                }
            }
        }

        visited = new boolean[virusList.size()];
        getCombination(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 1. virusList 중 m개 조합 찾기
    private static void getCombination(int cnt, int start) {

        if (cnt == m) {
            // 2. 각 조합마다 바이러스 퍼트리는데 걸리는 시간 계산
            getResult();
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            getCombination(cnt + 1, i);
            visited[i] = false;
        }
    }

    private static void getResult() {
        // visited한 곳은 바이러스가 있음(1)
        // 0, 2는 빈칸(0)
        // 1은 벽(-1)

        int[][] newMap = new int[n][n];
        Queue<Node> que = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                newMap[virusList.get(i).y][virusList.get(i).x] = 1;
                que.add(new Node(virusList.get(i).y, virusList.get(i).x));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) newMap[i][j] = -1;
            }
        }

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;

                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

                if (newMap[ny][nx] == 0) {
                    newMap[ny][nx] = newMap[now.y][now.x] + 1;
                    que.add(new Node(ny, nx));
                }
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (newMap[i][j] == 0) return;
                max = Math.max(max, newMap[i][j]);
            }
        }

        answer = Math.min(answer, max - 1);
    }

    private static class Node {

        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
