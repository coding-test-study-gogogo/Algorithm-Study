package week6.AlmondBreez3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PuyoPuyo {
    public static char[][] arr;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public static boolean[][] visited;
    public static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[12][6];
        visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            String st = br.readLine();
            for (int j = 0; j < 6; j++) {
                arr[i][j] = st.charAt(j);
            }
        }

        int count = 0;
        while (true) {
            boolean flag = false;
            visited =new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (arr[i][j] != '.' && !visited[i][j]) {
                        if (bfs(i,j)){
                            flag = true;
                        }
                    }
                }
            }

            if (!flag) break;

            calculate();
            cnt++;
        }



        System.out.println(cnt);
    }

    public static boolean bfs(int x, int y) {
        Queue<int[]> lastq = new LinkedList<>();
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        lastq.offer(new int[]{x,y});
        q.offer(new int[]{x,y});
        int count = 1;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            for (int j = 0; j < 4;j++) {
                int newx = tempX + dx[j];
                int newy = tempY + dy[j];
                if (newx >= 12 || newy >= 6 || newx <0 || newy <0) {
                    continue;
                }
                if (!visited[newx][newy]) {
                    if (arr[newx][newy]==arr[x][y]) {
                        visited[newx][newy] = true;
                        count += 1;
                        q.offer(new int[]{newx,newy});
                        lastq.offer(new int[]{newx,newy});
                    }
                }
            }
        }
        if (count >= 4) {
            for (int[] tem : lastq) {
                int temx = tem[0];
                int temy = tem[1];
                arr[temx][temy] = '.';
            }
            return true;
        }
        return false;
    }
    public static void calculate() {
        // 전체 12 x 6 판에 대해 계산하기
        // 각 열에 대하여
        for (int j = 0; j < 6; j++) {
            int row = 11;

            for (int i = 11; i >=0; i--) {
                if (arr[i][j] != '.') {
                    arr[row][j] = arr[i][j]; // 현재 위치로 블록 이동
                    if (row != i) arr[i][j] = '.'; // 이동 후 원래 위치는 빈 칸으로 설정
                    row--;
                }
            }

        }
    }
}
