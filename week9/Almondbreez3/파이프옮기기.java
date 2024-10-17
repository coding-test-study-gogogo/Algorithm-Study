package week9.Almondbreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

class 파이프옮기기 {
    public static int N;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    visited[i][j] = true;
                }
            }
        }

        if (visited[N][N]) {
            System.out.println(0);
            return;
        }

        bfs(N);
        System.out.println(count);
    }

    public static void bfs(int N) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 2, 1));

        while (!q.isEmpty()) {
            Node temp = q.poll();

            // 목표지점에 도달했을 때
            if (temp.lastX == N && temp.lastY == N) {
                count++;
                continue;
            }

            // 가로 방향
            if (temp.type == 1) {
                // 가로로 이동
                if (temp.lastY + 1 <= N && !visited[temp.lastX][temp.lastY + 1]) {
                    q.offer(new Node(temp.lastX, temp.lastY + 1, 1));
                }
                // 대각선으로 이동
                if (crossAble(temp.lastX, temp.lastY)) {
                    q.offer(new Node(temp.lastX + 1, temp.lastY + 1, 3));
                }

                // 세로 방향
            } else if (temp.type == 2) {
                // 세로로 이동
                if (temp.lastX + 1 <= N && !visited[temp.lastX + 1][temp.lastY]) {
                    q.offer(new Node(temp.lastX + 1, temp.lastY, 2));
                }
                // 대각선으로 이동
                if (crossAble(temp.lastX, temp.lastY)) {
                    q.offer(new Node(temp.lastX + 1, temp.lastY + 1, 3));
                }

                // 대각선 방향
            } else if (temp.type == 3) {
                // 세로로 이동
                if (temp.lastX + 1 <= N && !visited[temp.lastX + 1][temp.lastY]) {
                    q.offer(new Node(temp.lastX + 1, temp.lastY, 2));
                }
                // 가로로 이동
                if (temp.lastY + 1 <= N && !visited[temp.lastX][temp.lastY + 1]) {
                    q.offer(new Node(temp.lastX, temp.lastY + 1, 1));
                }
                // 대각선으로 이동
                if (crossAble(temp.lastX, temp.lastY)) {
                    q.offer(new Node(temp.lastX + 1, temp.lastY + 1, 3));
                }
            }
        }
    }

    // 대각선으로 이동할 수 있는지 확인하는 메소드
    public static boolean crossAble(int x, int y) {
        // 경계 체크를 먼저 수행합니다.
        if (x + 1 > N || y + 1 > N) return false;

        // 3칸 모두 비어 있어야 대각선 이동 가능
        return !visited[x][y + 1] && !visited[x + 1][y] && !visited[x + 1][y + 1];
    }

    static class Node {
        int lastX;
        int lastY;
        int type;

        public Node(int lastX, int lastY, int type) {
            this.lastX = lastX;
            this.lastY = lastY;
            this.type = type;
        }
    }
}
