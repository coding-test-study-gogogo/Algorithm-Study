package week13.olsohee;

class 파괴되지_않은_건물 {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length; // 세로
        int m = board[0].length; // 가로

        // 1. 공격을 누적합 준비 배열에 반영
        int[][] pSum = new int[n][m];
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int y1 = skill[i][1];
            int x1 = skill[i][2];
            int y2 = skill[i][3];
            int x2 = skill[i][4];
            int degree = skill[i][5];

            // 공격
            if (type == 1) {
                pSum[y1][x1] -= degree;
                if (x2 + 1 < m) {
                    pSum[y1][x2 + 1] += degree;
                }
                if (y2 + 1 < n) {
                    pSum[y2 + 1][x1] += degree;
                }
                if (y2 + 1 < n && x2 + 1 < m) {
                    pSum[y2 + 1][x2 + 1] -= degree;
                }
            }

            // 회복
            if (type == 2) {
                pSum[y1][x1] += degree;
                if (x2 + 1 < m) {
                    pSum[y1][x2 + 1] -= degree;
                }
                if (y2 + 1 < n) {
                    pSum[y2 + 1][x1] -= degree;
                }
                if (y2 + 1 < n && x2 + 1 < m) {
                    pSum[y2 + 1][x2 + 1] += degree;
                }
            }
        }

        // 2. 누적합 준비 배열을 누적합하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    pSum[i][j] += pSum[i][j - 1];
                } else if (j == 0) {
                    pSum[i][j] += pSum[i - 1][j];
                } else {
                    pSum[i][j] = pSum[i - 1][j] + pSum[i][j - 1] - pSum[i - 1][j - 1] + pSum[i][j];
                }
            }
        }

        // 3. 누적합 결과를 원본 배열에 반영, 파괴되지 않은 건물 개수 세기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += pSum[i][j];
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}
