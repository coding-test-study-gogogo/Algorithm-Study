package week12.AlmondBreez3;

import java.util.*;
import java.io.*;

class 로봇청소기 {
    public static int[][] arr; // 맵 배열
    public static int count; // 청소한 칸의 수
    public static int N, M, a, b, dir;
    public static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서 순서
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()); // 초기 세로 위치
        b = Integer.parseInt(st.nextToken()); // 초기 가로 위치
        dir = Integer.parseInt(st.nextToken()); // 초기 방향

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(a, b, dir); // DFS 탐색 시작
        System.out.println(count); // 청소한 칸의 수 출력
    }

    public static void dfs(int x, int y, int d) {
        // 현재 위치 청소
        if (arr[x][y] == 0) {
            count++;
            arr[x][y] = 2; // 청소 완료 표시
        }

        // 네 방향 탐색
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4; // 왼쪽으로 회전
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] == 0) {
                dfs(nx, ny, d); // 청소하지 않은 곳으로 이동
                return; // 이동 후 돌아가지 않음
            }
        }

        // 네 방향 모두 청소할 곳이 없는 경우 후진
        int backDirection = (d + 2) % 4; // 반대 방향
        int backX = x + dx[backDirection];
        int backY = y + dy[backDirection];

        // 후진 가능하면 후진
        if (backX >= 0 && backY >= 0 && backX < N && backY < M && arr[backX][backY] != 1) {
            dfs(backX, backY, d); // 후진할 때 방향 유지
        }
    }
}
