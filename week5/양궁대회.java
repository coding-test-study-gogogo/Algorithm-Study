package week5;

class 양궁대회 {
    public int N;  // Ryan이 쏠 수 있는 총 화살 수
    public int[] result;  // 최적의 점수 배분 결과 배열
    public int[] infos;  // Apeach의 점수 배분 배열
    public int maxDiff;  // Ryan이 달성할 수 있는 최대 점수 차이

    public int[] solution(int n, int[] info) {
        N = n;
        infos = info;
        result = new int[11];
        maxDiff = 0;

        dfs(0, 0, new int[11]);

        // 유효한 결과가 없으면 [-1] 반환
        if (maxDiff == 0) {
            return new int[]{-1};
        }
        return result;
    }

    public void dfs(int depth, int arrowsUsed, int[] tempR) {
        // 모든 화살을 사용한 경우
        if (arrowsUsed == N) {
            int ryan = calculate(tempR, true);
            int apeach = calculate(tempR, false);
            int diff = ryan - apeach;

            if (diff > maxDiff) {
                maxDiff = diff;
                result = tempR.clone();
            } else if (diff == maxDiff) {
                // 낮은 점수를 더 많이 얻는 결과를 선택
                for (int i = 10; i >= 0; i--) {
                    if (tempR[i] > result[i]) {
                        result = tempR.clone();
                        break;
                    } else if (tempR[i] < result[i]) {
                        break;
                    }
                }
            }
            return;
        }

        // 모든 점수를 고려했을 경우, 남은 화살을 마지막 슬롯에 배분
        if (depth == 10) {
            tempR[depth] = N - arrowsUsed;
            dfs(11, N, tempR);
            tempR[depth] = 0;  // 백트래킹
            return;
        }

        // 경우 1: 이 점수에서 Ryan이 이기는 경우
        if (arrowsUsed + infos[depth] + 1 <= N) {
            tempR[depth] = infos[depth] + 1;
            dfs(depth + 1, arrowsUsed + tempR[depth], tempR);
            tempR[depth] = 0;  // 백트래킹
        }

        // 경우 2: 이 점수에서 Ryan이 지는 경우
        dfs(depth + 1, arrowsUsed, tempR);
    }

    public int calculate(int[] arr, boolean isRyan) {
        int score = 0;
        for (int i = 0; i < 11; i++) {
            if (arr[i] == 0 && infos[i] == 0) continue;
            if (isRyan && arr[i] > infos[i]) {
                score += (10 - i);
            } else if (!isRyan && infos[i] >= arr[i]) {
                score += (10 - i);
            }
        }
        return score;
    }
}
