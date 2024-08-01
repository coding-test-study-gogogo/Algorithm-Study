package week4.olsohee;

import java.util.*;

class 카펫 {
    public int[] solution(int brown, int yellow) {

        int[] answer = new int[2];

        for (int i = 1; i <= yellow; i++) {
            if (yellow % i != 0) {
                continue;
            }
            int j = yellow / i; // i: 세로, j: 가로

            if (i * 2 + j * 2 + 4 == brown) {
                answer[0] = j + 2;
                answer[1] = i + 2;
                break;
            }
        }

        return answer;
    }
}
