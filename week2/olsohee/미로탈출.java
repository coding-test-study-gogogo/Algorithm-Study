package week2.olsohee;

import java.util.*;

class 미로탈출 {

    static int r, c;
    static char[][] map;
    static boolean[][] visited;
    static int startY, startX;
    static int leverY, leverX;
    static int endY, endX;
    static int answer;

    public int solution(String[] maps) {

        r = maps.length;
        c = maps[0].length();
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            char[] arr = maps[i].toCharArray();
            for (int j = 0; j < arr.length; j++) {
                map[i][j] = arr[j];
                if (map[i][j] == 'S') {
                    startY = i;
                    startX = j;
                }
                if (map[i][j] == 'L') {
                    leverY = i;
                    leverX = j;
                }
                if (map[i][j] == 'E') {
                    endY = i;
                    endX = j;
                }
            }
        }

        // 출발지점 -> 레버 (이 과정에서 출구 지나갈 수 있음)
        int result = bfs(startY, startX, leverY, leverX);
        if (result == -1) {
            return -1;
        }
        answer += result;

        // 레버 -> 출구
        result = bfs(leverY, leverX, endY, endX);
        if (result == -1) {
            return -1;
        }
        answer += result;

        // 최소 시간 반환(불가하면 -1)
        return answer;
    }

    public int bfs(int startY, int startX, int destY, int destX) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<Node> que = new LinkedList<>();
        visited = new boolean[r][c];

        que.add(new Node(startY, startX, 0));
        visited[startY][startX] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.y == destY && now.x == destX) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= r || nx < 0 || nx >= c) {
                    continue;
                }

                if (visited[ny][nx] || map[ny][nx] == 'X') {
                    continue;
                }

                // 이동
                que.add(new Node(ny, nx, now.cnt + 1));
                visited[ny][nx] = true;
            }
        }
        return -1;
    }

    private class Node {

        int y, x;
        int cnt; // 현재 노드까지 오는데 걸린 시간

        public Node (int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
