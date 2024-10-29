package week17.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스티커_붙이기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 세로
    static int m; // 가로
    static int k; // 스티커 개수
    static int[][] notebook;
    static int answer;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        notebook = new int[n][m];
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[y][x];
            for (int i = 0; i < y; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < x; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            putOnNotebook(sticker);
        }
        System.out.println(answer);
    }

    private static void putOnNotebook(int[][] sticker) {
        int turnCnt = 0;
        while (turnCnt < 4) {
            // 붙이기 시도
            int y = sticker.length;
            int x = sticker[0].length;
            if (isSuccess(sticker, y, x)) {
                break;
            }

            // 못붙였으면 회전
            turnCnt++;
            int[][] turnSticker = new int[x][y];
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    turnSticker[j][y - 1 - i] = sticker[i][j];
                }
            }
            sticker = turnSticker;
        }
    }

    private static boolean isSuccess(int[][] sticker, int y, int x) {
        if (y > n || x > m) return false;
        for (int i = 0; i <= n - y; i++) {
            for (int j = 0; j <= m - x; j++) {
                // (i, j)를 시작점으로 붙이기
                boolean canPut = true;

                outer: for (int a = 0; a < y; a++) {
                    for (int b = 0; b < x; b++) {
                        if (sticker[a][b] == 1) { // 스티커 자리가 노트북에 비워져있는지 확인(스티커가 1, 노트북은 0)
                            if (notebook[a + i][b + j] != 0) {
                                canPut = false;
                                break outer;
                            }
                        }
                    }
                }

                // 붙이기
                if (canPut) {
                    for (int a = 0; a < y; a++) {
                        for (int b = 0; b < x; b++) {
                            if (sticker[a][b] == 1) {
                                notebook[a + i][b + j] = -1;
                                answer++;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
