package week6.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Puyo_Puyo {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] visited;
    static int answer = 0;
    static boolean flag = false;
    static char[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (true) {
            flag = false;
            visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j, map[i][j]);
                    }
                }
            }

            if (!flag) {
                break;
            }

            answer++;
            fall();
        }

        // 몇연쇄인지 출력 (하나도 터지지 않으면 0 출력)
        System.out.println(answer);
    }

    private static void bfs(int y, int x, char color) {

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        visited[y][x] = true;
        List<Node> list = new ArrayList<>();
        list.add(new Node(y, x));

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;

                if (ny < 0 || ny >= 12 || nx < 0 || nx >= 6) continue;
                if (visited[ny][nx] || map[ny][nx] != color) continue;

                que.add(new Node(ny, nx));
                visited[ny][nx] = true;
                list.add(new Node(ny, nx));
            }
        }

        if (list.size() >= 4) {
            flag = true;
            for (Node node : list) {
                map[node.y][node.x] = '.';
            }
        }
    }

    private static void fall() {
        for (int i = 0; i < 6; i++) {
            Queue<Character> que = new LinkedList<>();
            for (int j = 11; j >= 0; j--) {
                if (map[j][i] != '.') {
                    que.add(map[j][i]);
                }
            }

            for (int j = 11; j >= 0; j--) {
                if (!que.isEmpty()) {
                    map[j][i] = que.poll();
                } else {
                    map[j][i] = '.';
                }
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
