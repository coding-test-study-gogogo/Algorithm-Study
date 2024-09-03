package week9.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 파이프_옮기기_1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(1, 1, 1, 2, 1));
        int answer = 0;

        if (map[n][n] == 1) {
            System.out.println(0);
            return;
        }

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.y2 == n && now.x2 == n) {
                answer++;
            }

            // 가로로 놓인 경우
            if (now.flag == 1) {
                // 오른쪽 이동
                if (now.x2 + 1 <= n) {
                    int movedX = now.x2 + 1;
                    if (map[now.y2][movedX] == 0) {
                        que.add(new Node(now.y1, movedX - 1, now.y2, movedX, 1));
                    }
                }

                // 대각선 이동
                if (now.y2 + 1 <= n && now.x2 + 1 <= n) {
                    int movedY = now.y2 + 1;
                    int movedX = now.x2 + 1;
                    if (map[movedY][movedX] == 0 && map[movedY - 1][movedX] == 0 && map[movedY][movedX - 1] == 0) {
                        que.add(new Node(movedY - 1, movedX - 1, movedY, movedX, 3));
                    }
                }
            }

            // 세로로 놓인 경우
            if (now.flag == 2) {
                // 아래 이동
                if (now.y2 + 1 <= n) {
                    int movedY = now.y2 + 1;
                    if (map[movedY][now.x2] == 0) {
                        que.add(new Node(movedY - 1, now.x1, movedY, now.x2, 2));
                    }
                }

                // 대각선 이동
                if (now.y2 + 1 <= n && now.x2 + 1 <= n) {
                    int movedY = now.y2 + 1;
                    int movedX = now.x2 + 1;
                    if (map[movedY][movedX] == 0 && map[movedY - 1][movedX] == 0 && map[movedY][movedX - 1] == 0) {
                        que.add(new Node(movedY - 1, movedX - 1, movedY, movedX, 3));
                    }
                }
            }

            // 대각선으로 놓인 경우
            if (now.flag == 3) {
                // 오른쪽 이동
                if (now.x2 + 1 <= n) {
                    int movedX = now.x2 + 1;
                    if (map[now.y2][movedX] == 0) {
                        que.add(new Node(now.y1, movedX - 1, now.y2, movedX, 1));
                    }
                }

                // 아래 이동
                if (now.y2 + 1 <= n) {
                    int movedY = now.y2 + 1;
                    if (map[movedY][now.x2] == 0) {
                        que.add(new Node(movedY - 1, now.x1, movedY, now.x2, 2));
                    }
                }

                // 대각선 이동
                if (now.y2 + 1 <= n && now.x2 + 1 <= n) {
                    int movedY = now.y2 + 1;
                    int movedX = now.x2 + 1;
                    if (map[movedY][movedX] == 0 && map[movedY - 1][movedX] == 0 && map[movedY][movedX - 1] == 0) {
                        que.add(new Node(movedY - 1, movedX - 1, movedY, movedX, 3));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static class Node {
        int y1, x1;
        int y2, x2;
        int flag;

        public Node(int y1, int x1, int y2, int x2, int flag) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.flag = flag;
        }
    }
}
