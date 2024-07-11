import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int[][] diff = new int[N][M];

        for (int i = 0; i < skills.length; i++) {
            int type = skills[i][0];
            int r1 = skills[i][1];
            int c1 = skills[i][2];
            int r2 = skills[i][3];
            int c2 = skills[i][4];
            int degree = skills[i][5];
            if (type == 1) {
                degree *= -1;
            }

            diff[r1][c1] += degree;
            if (c2+1<M)  diff[r1][c2+1] -= degree;
            if (r2+1<N) diff[r2+1][c1] -= degree;
            if (r2+1 < N && c2+1 <M) diff[r2+1][c2+1] += degree;
        }

        //가로 더하기
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <M;j++) {
                diff[i][j] += diff[i][j-1];
            }
        }

        //세로 더하기
        for (int i = 0; i < M; i++) {
            for (int j = 1; j <N;j++) {
                diff[j][i] +=diff[j-1][i];
            }
        }

        int count = 0;

        for (int i =0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += diff[i][j];
                if (board[i][j] >0) {
                    count++;
                }
            }
        }

        return count;
    }
}