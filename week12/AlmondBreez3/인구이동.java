package week12.AlmondBreez3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인구이동 {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    public static int result;
    public static int N, L, R;
    public static int[][] arr;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) {
                            moved = true;
                        }
                    }
                }
            }

            if (!moved) {
                break;
            }
            result++;
        }

        System.out.println(result);
    }

    public static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> united = new ArrayList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        united.add(new int[]{x, y});

        int sum = arr[x][y];
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = tempX + dx[i];
                int ny = tempY + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(arr[tempX][tempY] - arr[nx][ny]);
                    if (diff >= L && diff <= R) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        united.add(new int[]{nx, ny});
                        sum += arr[nx][ny];
                    }
                }
            }
        }

        if (united.size() > 1) {
            int newPopulation = sum / united.size();
            for (int[] cell : united) {
                arr[cell[0]][cell[1]] = newPopulation;
            }
            return true;  // Indicate that a move has been made
        }

        return false; // No move was made
    }
}
