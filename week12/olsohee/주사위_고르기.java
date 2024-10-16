package week12.olsohee;

import java.util.*;

class 주사위_고르기 {

    int n;
    int[][] dice;
    List<int[]> combinations = new ArrayList<>();
    ArrayList<Integer> resultList = new ArrayList<>();

    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.n = dice.length;
        int[] answer = new int[n / 2];
        int maxWinCnt = 0;


        // 1. A가 가져갈 주사위 조합 만들기
        dfs(0, 0, new int[n / 2]);

        // 2. 각 조합별로
        for (int[] aDices : combinations) {

            int[] bDices = new int[n / 2];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                boolean isContain = false;
                for (int j = 0; j < aDices.length; j++) {
                    if (aDices[j] == i) {
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    bDices[idx++] = i;
                }
            }

            // 2-1. A 결과, B 결과 생성
            List<Integer> aResult = getResult(aDices);
            List<Integer> bResult = getResult(bDices);

            // 2-2. A 결과, B 결과 비교 (이분탐색)
            int winCnt = 0; // 해당 주사위 조합일 때, A가 이기는 횟수
            Collections.sort(bResult);

            for (int result : aResult) {
                // A 점수(result)로 이기는 B 점수의 인덱스 찾기(이분탐색)
                int start = 0;
                int end = bResult.size() - 1;
                while (start <= end) {
                    int mid = (start + end) / 2;
                    if (bResult.get(mid) >= result) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                }

                winCnt += start;
            }


            // 2-3. A가 이길 확률 계산, 더 높을 경우 answer 갱신
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                answer = aDices;
            }
        }

        return Arrays.stream(answer)
                .map((i) -> i + 1)
                .toArray(); // 주사위 번호 +1
    }

    private List<Integer> getResult(int[] dices) {
        resultList = new ArrayList<>();
        throwDice(0, dices, 0);
        return (ArrayList<Integer>)resultList.clone();
    }

    private void throwDice(int idx, int[] dices, int sum) {
        if (idx == dices.length) {
            resultList.add(sum);
            return;
        }
        int[] now = dice[dices[idx]];
        for (int i = 0; i < 6; i++) {
            throwDice(idx + 1, dices, sum + now[i]);
        }
    }

    private void dfs(int depth, int startIdx, int[] dices) {
        if (depth == n / 2) {
            combinations.add(dices.clone());
            return;
        }

        for (int i = startIdx; i < n; i++) {
            dices[depth] = i;
            dfs(depth + 1, i + 1, dices);
        }
    }
}
