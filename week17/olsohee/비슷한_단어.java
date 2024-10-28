package week17.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 비슷한_단어 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int answer = 0;
        int n = Integer.parseInt(br.readLine());

        int[] target = new int[27]; // A~Z 26개
        for (char c : br.readLine().toCharArray()) {
            target[c - 65]++;
        }

        for (int i = 0; i < n - 1; i++) {
            int[] word = new int[27]; // A~Z 26개
            for (char c : br.readLine().toCharArray()) {
                word[c - 65]++;
            }

            int onlyTargetCnt = 0;
            int onlyWordCnt = 0;
            for (int j = 0; j < 27; j++) {
                if (target[j] >= word[j]) {
                    onlyTargetCnt += target[j] - word[j];
                } else {
                    onlyWordCnt += word[j] - target[j];
                }
            }
            if (onlyTargetCnt == 0 && onlyWordCnt == 0) answer++;
            if (onlyTargetCnt == 1 && onlyWordCnt == 0) answer++;
            if (onlyTargetCnt == 0 && onlyWordCnt == 1) answer++;
            if (onlyTargetCnt == 1 && onlyWordCnt == 1) answer++;
        }
        System.out.println(answer);
    }
}
