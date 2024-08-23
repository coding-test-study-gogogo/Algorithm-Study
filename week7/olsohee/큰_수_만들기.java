package week7.olsohee;

import java.util.*;

class 큰_수_만들기 {

    public String solution(String number, int k) {
        int n = number.length();
        int[] arr = Arrays.stream(number.split(""))
                .mapToInt(str -> Integer.parseInt(str))
                .toArray();

        String answer = "";
        int start = 0;

        while (answer.length() < n - k) {

            if (k == 0) {
                for (int i = start; i < n; i++) {
                    answer += arr[i];
                }
                break;
            }

            int max = arr[start];
            int maxIdx = start;
            for (int i = start; i <= start + k; i++) {
                if (max < arr[i]) {
                    max = arr[i];
                    maxIdx = i;
                    if (max == 9) break;
                }
            }
            answer += max;
            k -= maxIdx - start;
            start = maxIdx + 1;
        }
        return answer;
    }
}
