package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 진우의_민트초코우유 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m; // 초기 체력
    static int h; // 우유 마시고 증가하는 체력
    static int[][] map;
    static Node home;
    static List<Node> milkList = new ArrayList<>();
    static int answer = 0; // 집으로 돌아올 떄까지 마실 수 있는 우유 최대 개수
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    home = new Node(i, j);
                }
                if (map[i][j] == 2) {
                    milkList.add(new Node(i, j));
                }
            }
        }

        // 집에서 각 우유로 이동 시작
        visited = new boolean[n][n];
        for (Node milk : milkList) {
            int distance = Math.abs(home.y - milk.y) + Math.abs(home.x - milk.x);
            if (distance <= m) {
                dfs(milk.y, milk.x, m - distance, 0);
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int remain, int cnt) {
        // 현재 우유 위치에 와서, 체력 증가 + 우유 갯수 증가
        remain += h;
        cnt++;
        visited[y][x] = true;

        // 현재 위치에서 집으로 돌아가기(답 반영)
        int distance = Math.abs(home.y - y) + Math.abs(home.x - x);
        if (distance <= remain) {
            answer = Math.max(answer, cnt);
        }

        // 현재 위치에서 계속 이동하기
        for (Node milk : milkList) {
            if (visited[milk.y][milk.x]) {
                continue;
            }
            distance = Math.abs(y - milk.y) + Math.abs(x - milk.x);
            if (distance <= remain) {
                dfs(milk.y, milk.x, remain - distance, cnt);
            }
        }

        visited[y][x] = false;
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
