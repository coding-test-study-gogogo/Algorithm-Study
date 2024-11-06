package week18.olsohee;

import java.util.*;

class 혼자서_하는_틱택톡 {

    char[][] map;
    int xCnt;
    int oCnt;
    int xWinCnt;
    int oWinCnt;

    public int solution(String[] board) {
        map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            char[] arr = board[i].toCharArray();
            for (int j = 0; j < 3; j++) {
                map[i][j] = arr[j];
            }
        }

        // 1. 개수
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'X') xCnt++;
                if (map[i][j] == 'O') oCnt++;
            }
        }

        if (xCnt > oCnt || Math.abs(oCnt-xCnt) >= 2) return 0;

        // 2. 이기는 횟수
        // 가로/세로
        for (int i = 0; i < 3; i++) {
            func(i, true);
            func(i, false);
        }
        // 대각선
        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            if (map[0][0] == 'O') oWinCnt++;
            if (map[0][0] == 'X') xWinCnt++;
        }

        if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
            if (map[0][2] == 'O') oWinCnt++;
            if (map[0][2] == 'X') xWinCnt++;
        }

        if (oWinCnt >= 1 && xWinCnt >= 1) return 0;
        if (oWinCnt >= 1) {
            if (oCnt != xCnt + 1) return 0;
        }
        if (xWinCnt >= 1) {
            if (oCnt != xCnt) return 0;
        }

        return 1;
    }

    private void func(int idx, boolean isVertical) {
        if (isVertical) {
            char flag = map[0][idx];
            boolean isWin = true;
            for (int i = 1; i < 3; i++) {
                if (flag != map[i][idx]) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                if (flag == 'O') oWinCnt++;
                if (flag == 'X') xWinCnt++;
            }
        } else {
            char flag = map[idx][0];
            boolean isWin = true;
            for (int i = 1; i < 3; i++) {
                if (flag != map[idx][i]) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                if (flag == 'O') oWinCnt++;
                if (flag == 'X') xWinCnt++;
            }
        }
    }
}
