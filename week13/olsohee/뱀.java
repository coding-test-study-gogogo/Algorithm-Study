package week13.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Node> snake = new LinkedList<>();
    static Map<Integer, String> timeMap = new HashMap<>();
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int direction = 0; // 0:오, 1:아래, 2:왼, 3:위
    static int gameTime = 0;
    static int headY, headX;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = 1; // 사과가 있는 위치 = 1
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            timeMap.put(time, direction);
        }

        // 뱀 이동 시작
        snake.add(new Node(1, 1));
        headY = 1;
        headX = 1;

        while (true) {

            int ny = headY + dy[direction];
            int nx = headX + dx[direction];

            // 벽에 닿거나, 자기 자신에 닿으면 -> 끝내기
            if (ny <= 0 || nx <= 0 || ny > n || nx > n || isTouchSnake(ny, nx)) {
                gameTime++;
                break;
            }

            // 사괴이면 머리 넣기
            if (map[ny][nx] == 1) {
                snake.add(new Node(ny, nx));
                map[ny][nx] = 0;
            }

            // 빈 공간이면 머리 넣기 + 꼬리 빼기
            else {
                snake.add(new Node(ny, nx));
                snake.remove(snake.poll());
            }

            headY = ny;
            headX = nx;

            gameTime++;

            // 현재 gameTime초가 지난 상태, 방향 바꿔야 하면 바꾸기
            if (timeMap.containsKey(gameTime)) {
                turn(timeMap.get(gameTime));
            }
        }

        System.out.println(gameTime);
    }

    private static boolean isTouchSnake(int ny, int nx) {

        for (Node snakeLocation : snake) {
            if (snakeLocation.y == ny && snakeLocation.x == nx) {
                return true;
            }
        }
        return false;
    }

    private static void turn(String dir) {
        // 왼쪽으로 회전
        if (dir.equals("L")) {
            direction--;
            if (direction == -1) direction = 3;
        }

        // 오른쪽으로 회전
        if (dir.equals("D")) {
            direction++;
            if (direction == 4) direction = 0;
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
