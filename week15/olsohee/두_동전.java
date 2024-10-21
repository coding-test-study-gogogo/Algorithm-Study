package week15.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 두_동전 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        int[][] twoCoins = new int[2][2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = arr[j];
                if (arr[j] == 'o') {
                    twoCoins[idx][0] = i;
                    twoCoins[idx][1] = j;
                    idx++;
                }
            }
        }

        // bfs
        Queue<Node> que = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];
        que.add(new Node(twoCoins[0][0], twoCoins[0][1], twoCoins[1][0], twoCoins[1][1], 0));
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        int answer = -1;

        outer:
        while (!que.isEmpty()) {
            Node now = que.poll();

            // 10번 이상 움직였는데 아직 답이 안나온 경우 끝내기
            if (now.cnt >= 10) break;

            for (int i = 0; i < 4; i++) {
                int ny1 = now.y1 + dy[i];
                int nx1 = now.x1 + dx[i];
                int ny2 = now.y2 + dy[i];
                int nx2 = now.x2 + dx[i];

                // 둘 중 하나만 밖으로 떨어질 경우 답, 둘 다 밖으로 떨어질 경우 pass
                int outCnt = 0;
                if (ny1 < 0 || ny1 >= n || nx1 < 0 || nx1 >= m) outCnt++;
                if (ny2 < 0 || ny2 >= n || nx2 < 0 || nx2 >= m) outCnt++;
                if (outCnt == 2) continue;
                if (outCnt == 1) {
                    answer = now.cnt + 1;
                    break outer;
                }

                if (visited[ny1][nx1][ny2][nx2]) continue;

                // 벽이면 이동 X
                if (map[ny1][nx1] == '#') {
                    ny1 = now.y1;
                    nx1 = now.x1;
                }
                if (map[ny2][nx2] == '#') {
                    ny2 = now.y2;
                    nx2 = now.x2;
                }

                que.add(new Node(ny1, nx1, ny2, nx2, now.cnt + 1));
                visited[ny1][nx1][ny2][nx2] = true;
            }
        }

        System.out.println(answer);
    }

    private static class Node {
        int y1, x1;
        int y2, x2;
        int cnt; // 버튼을 누른 횟수

        public Node(int y1, int x1, int y2, int x2, int cnt) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.cnt = cnt;
        }
    }
}
