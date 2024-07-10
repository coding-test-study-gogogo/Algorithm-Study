package week1.olsohee;

import java.util.*;

class 표현_가능한_이진트리 {
    public int[] solution(long[] numbers) {

        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String str = Long.toBinaryString(numbers[i]);
            str = validateLen(str);
            char mid = str.charAt(str.length() / 2);
            int result = dfs(str) ? 1 : 0;
            answer[i] = result;
        }
        return answer;
    }

    private boolean dfs(String str) {
        if (str.length() == 1) {
            return true;
        }

        int mid = str.length() / 2;
        String left = str.substring(0, mid);
        String right = str.substring(mid + 1, str.length());

        // 가운데 부모 노드가 0이면, 그 아래도 다 0이어야 함
        if (str.charAt(mid) == '0') {
            if (left.contains("1") || right.contains("1")) {
                return false;
            } else {
                return true;
            }
        }
        // 가운데 부모 노드가 1이면, 계속 탐색
        else {
            return dfs(left) && dfs(right);
        }
    }

    // 길이가 2^n - 1가 될 때까지 앞에 0을 붙이기
    private String validateLen(String str) {

        while (!validateBinary(str.length() + 1)) {
            str = "0" + str;
        }

        return str;
    }

    // num이 2^n의 값인지?
    private boolean validateBinary(int num) {
        int result = 1;
        while (result < num) {
            result *= 2;
        }

        return result == num;
    }
}
