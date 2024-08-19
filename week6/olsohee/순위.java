package week6.olsohee;

class 순위 {
    public int solution(int n, int[][] results) {

        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int[] result : results) {
            map[result[0]][result[1]] = -1;
            map[result[1]][result[0]] = 1;
        }

        // 플로이드
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) continue;
                    if (map[i][k] != map[k][j]) continue;
                    map[i][j] = map[i][k];
                }
            }
        }

        // 순위 계산
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == Integer.MAX_VALUE) {
                    flag = false;
                    break;
                }
            }
            if (flag) answer++;
        }

        return answer;
    }
}
