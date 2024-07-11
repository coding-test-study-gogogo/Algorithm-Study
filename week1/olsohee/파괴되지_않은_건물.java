package week1.olsohee;

class 파괴되지_않은_건물 {

    int n, m;
    int[][] skillMap;
    int[][] sumMap;
    int[][] board;

    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;
        this.board = board;
        skillMap = new int[n][m];
        sumMap = new int[n][m];

        // 1. 스킬 반영(배열의 끝 점들에만 반영)
        for (int[] s : skill) {
            // 공격
            if (s[0] == 1) {
                attack(s[1], s[2], s[3], s[4], s[5]);
            }

            // 방어
            if (s[0] == 2) {
                defend(s[1], s[2], s[3], s[4], s[5]);
            }
        }

        // 2. 스킬들 누적합 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    sumMap[i][j] = skillMap[i][j];
                } else if (i == 0) {
                    sumMap[i][j] = sumMap[i][j - 1] + skillMap[i][j];
                } else if (j == 0) {
                    sumMap[i][j] = sumMap[i - 1][j] + skillMap[i][j];
                } else {
                    sumMap[i][j] = sumMap[i - 1][j] + sumMap[i][j - 1] - sumMap[i - 1][j - 1] + skillMap[i][j];
                }

            }
        }

        // 3. 누적합을 게임 맵에 반영 후 답 구하기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += sumMap[i][j];
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void attack(int r1, int c1, int r2, int c2, int degree) {
        skillMap[r1][c1] -= degree;
        if (r2 + 1 < n) {
            skillMap[r2 + 1][c1] += degree;
        }
        if (c2 + 1 < m) {
            skillMap[r1][c2 + 1] += degree;
        }
        if (r2 + 1 < n && c2 + 1 < m) {
            skillMap[r2 + 1][c2 + 1] -= degree;
        }
    }

    private void defend(int r1, int c1, int r2, int c2, int degree) {
        skillMap[r1][c1] += degree;
        if (r2 + 1 < n) {
            skillMap[r2 + 1][c1] -= degree;
        }
        if (c2 + 1 < m) {
            skillMap[r1][c2 + 1] -= degree;
        }
        if (r2 + 1 < n && c2 + 1 < m) {
            skillMap[r2 + 1][c2 + 1] += degree;
        }
    }
}
