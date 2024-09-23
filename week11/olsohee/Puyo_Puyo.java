package week11.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Puyo_Puyo {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] map = new char[12][6];
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (true) {
            visited = new boolean[12][6];

            // 터트리기
            boolean isPop = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        if (pop(i, j, map[i][j])) {
                            isPop = true;
                        }
                    }
                }
            }

            // 아래로 내리기
            if (isPop) {
                answer++;
                down();
            } else {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void down() {
        for (int i = 0; i < 6; i++) {
            Queue<Character> que = new LinkedList<>();
            for (int j = 11; j >= 0; j--) {
                if (map[j][i] != '.') que.add(map[j][i]);
            }

            for (int j = 11; j >= 0; j--) {
                if (que.isEmpty()) {
                    map[j][i] = '.';
                } else {
                    map[j][i] = que.poll();
                }
            }
        }
    }

    private static boolean pop(int y, int x, char value) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        visited[y][x] = true;
        int cnt = 1;
        List<Node> record = new ArrayList<>();
        record.add(new Node(y, x));

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= 12 || nx < 0 || nx >= 6) continue;
                if (visited[ny][nx] || map[ny][nx] != value) continue;

                cnt++;
                visited[ny][nx] = true;
                que.add(new Node(ny, nx));
                record.add(new Node(ny, nx));
            }
        }

        if (cnt >= 4) {
            for (Node node : record) {
                map[node.y][node.x] = '.';
            }

            return true;
        }

        return false;
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
