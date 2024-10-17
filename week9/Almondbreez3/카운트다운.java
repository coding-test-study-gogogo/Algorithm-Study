package week9.Almondbreez3;

class 카운트다운{
    int[][] dp;
    int INF = 100001;

    public int[] solution(int target) {
        dp = new int[target + 1][2]; // 0: 다트 수 1: 싱글/불 횟수
        for(int i=1;i<=target;i++)
            dp[i][0] = INF;

        return throwDart(target);
    }

    int[] throwDart(int n) {
        if(dp[n][0] == INF) {
            if(n >= 50) { // 불 맞추기
                int[] temp = throwDart(n - 50);
                if((temp[0] + 1 < dp[n][0]) || (temp[0] + 1 == dp[n][0] && temp[1] + 1 > dp[n][1])) {
                    dp[n][0] = 1 + temp[0];
                    dp[n][1] = 1 + temp[1];
                }
            }

            int start = n >= 20 ? 20 : n;
            for(int i=start;i>=1;i--) {
                for(int j=1;j<=3;j++) { // 싱글, 더블, 트리플
                    if(n >= i * j) {
                        int[] temp = throwDart(n - i * j);
                        int cnt = j == 1 ? 1 : 0; // 싱글일 경우 카운트하기
                        if((temp[0] + 1 < dp[n][0]) || (temp[0] + 1 == dp[n][0] && temp[1] + cnt > dp[n][1])) {
                            dp[n][0] = 1 + temp[0];
                            dp[n][1] = cnt + temp[1];
                        }
                    }
                }
            }
        }

        return dp[n];
    }
}
