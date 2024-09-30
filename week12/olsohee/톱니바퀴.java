package week12.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 톱니바퀴 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] gears;

    public static void main(String[] args) throws IOException {

        gears = new int[4][8];
        for (int i = 0; i < 4; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = input[j] - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {

            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            int[] turnDir = getTurnGear(gearNum - 1, dir);
            turn(turnDir);
        }

        // 최종 점수 출력
        int answer = 0;
        answer += gears[0][0] == 0 ? 0 : 1;
        answer += gears[1][0] == 0 ? 0 : 2;
        answer += gears[2][0] == 0 ? 0 : 4;
        answer += gears[3][0] == 0 ? 0 : 8;
        System.out.println(answer);
    }

    private static void turn(int[] turnDir) {
        for (int i = 0; i < 4; i++) {
            if (turnDir[i] == 0) continue;

            if (turnDir[i] == 1) {
                int temp = gears[i][7];
                for (int j = 7; j >= 1; j--) {
                    gears[i][j] = gears[i][j - 1];
                }
                gears[i][0] = temp;
            }

            if (turnDir[i] == -1) {
                int temp = gears[i][0];
                for (int j = 0; j <= 6; j++) {
                    gears[i][j] = gears[i][j + 1];
                }
                gears[i][7] = temp;
            }
        }
    }

    private static int[] getTurnGear(int gearIdx, int dir) {
        int[] turnDir = new int[4];
        turnDir[gearIdx] = dir;

        // 왼쪽 톱니바퀴
        for (int i = gearIdx - 1; i >= 0; i--) {
            if (gears[i + 1][6] == gears[i][2]) {
                break;
            } else {
                turnDir[i] = turnDir[i + 1] * -1;
            }
        }

        // 오른쪽 톱니바퀴
        for (int i = gearIdx + 1; i <= 3; i++) {
            if (gears[i - 1][2] == gears[i][6]) {
                break;
            } else {
                turnDir[i] = turnDir[i - 1] * -1;
            }
        }

        return turnDir;
    }
}
