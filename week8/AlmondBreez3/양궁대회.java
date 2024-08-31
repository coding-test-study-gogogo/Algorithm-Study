package week8.AlmondBreez3;

import java.util.*;
class 양궁대회 {
    public int N;
    public int curDiff = 0;
    public int[] infos;
    public int[] current;
    public int[] result;

    public int[] solution(int n, int[] info) {
        N = n;
        infos = info;
        current = new int[11];
        result = new int[11];

        dfs(0, n);

        if (curDiff == 0) {
            return new int[]{-1};
        }

        return result;
    }

    public void dfs(int index, int remainingArrows) {
        if (index == 11 || remainingArrows == 0) {
            if (remainingArrows > 0) {
                current[10] += remainingArrows;  // 남은 화살을 가장 낮은 점수에 할당
            }
            int diff = calculate();
            if (diff > curDiff || (diff == curDiff && isLowerScoreBetter())) {
                curDiff = diff;
                System.arraycopy(current, 0, result, 0, 11);
            }
            if (remainingArrows > 0) {
                current[10] -= remainingArrows;  // 상태 복원
            }
            return;
        }

        // 점수를 얻는 경우
        if (infos[index] < remainingArrows) {
            current[index] = infos[index] + 1;
            dfs(index + 1, remainingArrows - current[index]);
            current[index] = 0;
        }

        // 점수를 포기하는 경우
        dfs(index + 1, remainingArrows);
    }

    public int calculate() {
        int ryan = 0;
        int apeach = 0;
        for (int i = 0; i <= 10; i++) {
            if (current[i] > infos[i]) {
                ryan += 10 - i;
            } else if (infos[i] > 0) {
                apeach += 10 - i;
            }
        }
        return ryan - apeach;
    }

    private boolean isLowerScoreBetter() {
        for (int i = 10; i >= 0; i--) {
            if (current[i] > result[i]) return true;
            if (current[i] < result[i]) return false;
        }
        return false;
    }
}

//import java.util.*;
//
//class Solution {
//    public int N;
//    public int curMax = Integer.MIN_VALUE;
//    public int curDiff = Integer.MIN_VALUE;
//    public int[] infos;
//    public int[] result;
//
//    public int[] solution(int n, int[] info) {
//        N = n;
//        infos = info;
//        result = new int[info.length];
//
//        dfs(0, n, new int[11]);
//
//        if (curDiff == Integer.MIN_VALUE) {
//            return new int[]{-1};
//        }
//
//        return result;
//    }
//
//    public void dfs(int depth, int remainingArrows, int[] currentResult) {
//        if (depth == 11 || remainingArrows == 0) {
//            currentResult[10] += remainingArrows; // 남은 화살은 0점에 할당
//            calculate(currentResult);
//            currentResult[10] -= remainingArrows; // 백트래킹을 위해 원래 상태로 복구
//            return;
//        }
//
//        if (remainingArrows > infos[depth]) { // 라이언이 이 점수에서 이기는 경우
//            currentResult[depth] = infos[depth] + 1;
//            dfs(depth + 1, remainingArrows - (infos[depth] + 1), currentResult);
//            currentResult[depth] = 0; // 백트래킹
//        }
//
//        // 이 점수를 포기하는 경우
//        dfs(depth + 1, remainingArrows, currentResult);
//    }
//
//    public void calculate(int[] currentResult) {
//        int apeach = 0;
//        int ryan = 0;
//
//        for (int i = 0; i <= 10; i++) {
//            if (infos[i] == 0 && currentResult[i] == 0) {
//                continue;
//            }
//            if (infos[i] >= currentResult[i]) {
//                apeach += 10 - i;
//            } else {
//                ryan += 10 - i;
//            }
//        }
//
//        if (ryan > apeach) { // 라이언이 이기는 경우에만 갱신
//            int diff = ryan - apeach;
//            if (diff > curDiff || (diff == curDiff && compareResults(currentResult, result))) {
//                curDiff = diff;
//                System.arraycopy(currentResult, 0, result, 0, 11);
//            }
//        }
//    }
//
//    private boolean compareResults(int[] currentResult, int[] bestResult) {
//        for (int i = 10; i >= 0; i--) {
//            if (currentResult[i] > bestResult[i]) {
//                return true;
//            } else if (currentResult[i] < bestResult[i]) {
//                return false;
//            }
//        }
//        return false;
//    }
//}


