package week5.olsohee;

import java.util.*;

class 양궁대회 {
    int n;
    int[] info;
    List<int[]> list = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;

        // 1. n개의 화살을 0 ~ 10점으로 나누는 조합 만들기
        dfs(0, new int[11], n);

        // 2. 조합별로, 어피치-라이언 점수 계산해서 diff 만들기
        int maxDiff = 0;
        int[] answerArr = new int[11];
        for(int i = 0; i < list.size(); i++) {
            int[] arr = list.get(i);

            int diff = getScoreDiff(arr);
            if(maxDiff == diff) {
                // 점수차가 같은 경우가 여러개 있으면, 더 낮은 점수를 많이 맞춘 경우를 답으로
                for(int j = 10; j >= 0; j--) {
                    if(arr[j] == answerArr[j]) {
                        continue;
                    }
                    if(arr[j] > answerArr[j]) {
                        answerArr = arr;
                        break;
                    } else {
                        break;
                    }
                }
            } else if(maxDiff < diff) {
                maxDiff = diff;
                answerArr = arr;
            }
        }

        // 3. diff가 가장 큰 조합이 답
        // 4. diff가 0이거나 음수이면 -1 반환
        if(maxDiff <= 0) {
            return new int[] {-1};
        } else {
            return answerArr;
        }
    }

    private int getScoreDiff(int[] arr) {
        int lionScore = 0;
        int apeachScore = 0;

        for(int i = 0; i <= 10; i++) {
            if(arr[i] == 0 && info[i] == 0) {
                continue;
            }
            if(arr[i] > info[i]) {
                lionScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }

        return lionScore - apeachScore;
    }

    // index 0부터 10까지 탐색하며, 하나씩 값 채우기
    // index가 11이 되었을 때, 총 합이 n이면 리스트에 담고 끝내기
    private void dfs(int index, int[] arr, int remain) {
        if(index == 11) {
            if(remain == 0) {
                list.add(arr);
            }
            return;
        }

        // cnt 인덱스에 숫자 몇을 넣을지
        for(int i = 0; i <= remain; i++) {
            int[] cloneArr = arr.clone();
            cloneArr[index] = i;
            dfs(index + 1, cloneArr, remain - i);
            if(cloneArr[index] > info[index]) {
                break;
            }
        }
    }
}
