package week8.AlmondBreez3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 사탕게임 {
    public static int N;
    public static char[][] arr;
    public static int max = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        // 각 사탕에 대해 상하좌우 교환 시도
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 오른쪽과 교환
                if (j + 1 < N) {
                    swap(i, j, i, j + 1);
                    checkMax();
                    swap(i, j, i, j + 1); // 다시 원상복구
                }

                // 아래쪽과 교환
                if (i + 1 < N) {
                    swap(i, j, i + 1, j);
                    checkMax();
                    swap(i, j, i + 1, j); // 다시 원상복구
                }
            }
        }

        System.out.println(max);
    }

    // 사탕을 교환하는 함수
    public static void swap(int x1, int y1, int x2, int y2) {
        char temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }

    // 현재 배열에서 가장 긴 연속된 사탕의 길이를 확인하는 함수
    public static void checkMax() {
        // 가로 체크
        for (int i = 0; i < N; i++) {
            int count = 1;
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == arr[i][j - 1]) {
                    count++;
                } else {
                    count = 1;
                }
                max = Math.max(max, count);
            }
        }

        String a = "15:00";
        a.replace(":","");
        a.replace()
        // 세로 체크
        for (int j = 0; j < N; j++) {
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (arr[i][j] == arr[i - 1][j]) {
                    count++;
                } else {
                    count = 1;
                }
                max = Math.max(max, count);
            }
        }
    }
}

