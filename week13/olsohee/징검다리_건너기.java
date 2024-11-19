package week13.olsohee;

import java.util.*;

class 징검다리_건너기 {

    int[] stones;
    int k;

    public int solution(int[] stones, int k) {
        this.stones = stones;
        this.k = k;

        int min = 0;
        int max = Integer.MAX_VALUE;
        int answer = 0;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (canMove(mid)) {
                min = mid + 1; // 건널 수 있으면, 인원 더 늘리기
                answer = Math.max(answer, mid);
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    private boolean canMove(int peopleCnt) {
        int cnt = 0; // 연속으로 건너뛰는 돌 갯수
        for (int i = 0; i < stones.length; i++) {
            // 못 건너서 건너뛰는 경우
            if (stones[i] < peopleCnt) {
                cnt++;
                if (cnt >= k) return false;
            }
            // 건너는 경우
            else {
                cnt = 0;
            }
        }
        return true;
    }
}
