package week12.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

// 다시 풀어야함. 긴걸 끌고 갈 능력이 안됨 아직
class 톱니바퀴 {
    public static int[][] arr;
    public static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[5][9];

        for (int i = 1; i <= 4;i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < 8;j++) {
                arr[i][j+1] = Integer.parseInt(str[j]);
            }
        }

        count = Integer.parseInt(br.readLine());
        int result = 0;
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            rotate(a,b);
        }

        if (arr[1][1]==0) {
            result += 0;
        } else {
            result += 1;
        }

        if (arr[2][1]==0) {
            result += 0;
        } else {
            result += 2;
        }

        if (arr[3][1]==0) {
            result += 0;
        } else {
            result += 4;
        }

        if (arr[4][1]==0) {
            result += 0;
        } else {
            result += 8;
        }

        System.out.println(result);
    }
    public static void rotate(int a, int b) {
        int[] rotateDirections = new int[5];
        rotateDirections[a] = b;

        // 왼쪽 톱니바퀴의 회전 방향 결정
        for (int i = a; i > 1; i--) {
            if (arr[i][7] != arr[i - 1][3]) {
                rotateDirections[i - 1] = -rotateDirections[i];
            } else {
                break;
            }
        }

        // 오른쪽 톱니바퀴의 회전 방향 결정
        for (int i = a; i < 4; i++) {
            if (arr[i][3] != arr[i + 1][7]) {
                rotateDirections[i + 1] = -rotateDirections[i];
            } else {
                break;
            }
        }
        // 모든 톱니바퀴 회전 수행
        for (int i = 1; i <= 4; i++) {
            if (rotateDirections[i] != 0) {
                rotateSingleGear(i, rotateDirections[i]);
            }
        }
    }
    public static void rotateSingleGear(int a, int b) {
        // 시계방향
        if (b==1) {
            int temp = arr[a][8];
            for (int j=8;j>=2;j--) {
                arr[a][j] = arr[a][j-1];
            }
            arr[a][1]=temp;
        } else {
            // 반시계방향
            int temp = arr[a][1];
            for (int j=1;j<8;j++) {
                arr[a][j] = arr[a][j+1];
            }
            arr[a][8]=temp;
        }
    }

}
