package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 톱니바퀴 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] gears = new int[4][8];
    static int[] turn = new int[4]; // 1: 오른쪽 회전, -1: 왼쪽 회전

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = arr[j] - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            turn = new int[4]; // 1: 오른쪽 회전, -1: 왼쪽 회전
            turn[gearNum] = dir;

            // 왼쪽
            for (int j = gearNum - 1; j >= 0; j--) {
                if (gears[j + 1][6] == gears[j][2]) break;
                turn[j] = turn[j + 1] * -1;
            }

            // 오른쪽
            for (int j = gearNum + 1; j < 4; j++) {
                if (gears[j - 1][2] == gears[j][6]) break;
                turn[j] = turn[j - 1] * -1;
            }

            turn();
        }

        int answer = 0;
        answer += (gears[0][0] == 0) ? 0 : 1;
        answer += (gears[1][0] == 0) ? 0 : 2;
        answer += (gears[2][0] == 0) ? 0 : 4;
        answer += (gears[3][0] == 0) ? 0 : 8;
        System.out.println(answer);


    }

    private static void turn() {
        for (int i = 0; i < 4; i++) {
            if (turn[i] == 1) {
                turnRight(i);
            } else if (turn[i] == -1) {
                turnLeft(i);
            }
        }
    }

    private static void turnRight(int idx) {
        int temp = gears[idx][7];
        for (int i = 6; i >= 0; i--) {
            gears[idx][i + 1] = gears[idx][i];
        }
        gears[idx][0] = temp;
    }

    private static void turnLeft(int idx) {
        int temp = gears[idx][0];
        for (int i = 1; i <= 7; i++) {
            gears[idx][i - 1] = gears[idx][i];
        }
        gears[idx][7] = temp;
    }
}
