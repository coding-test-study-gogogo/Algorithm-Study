package week18.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 오리 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        char[] input = br.readLine().toCharArray();
        boolean[] visited = new boolean[input.length];
        char[] word = {'q', 'u', 'a', 'c', 'k'};
        int answer = 0;

        for (int i = 0; i < input.length; i++) {
            if (visited[i]) continue;
            int wordIdx = 0;
            List<Integer> list = new ArrayList<>();

            for (int j = i; j < input.length; j++) {
                if (wordIdx == 5) {
                    wordIdx = 0;
                }
                if (visited[j]) {
                    continue;
                }
                if (input[j] == word[wordIdx]) {
                    list.add(j);
                    visited[j] = true;
                    wordIdx++;
                }
            }

            // quack을 만들다가 중간에 끝난 경우
            if (list.size() % 5 != 0) {
                System.out.println(-1);
                return;
            }

            answer++;
        }

        for (boolean isVisited : visited) {
            if (!isVisited) {
                answer = -1;
            }
        }

        System.out.println(answer);
    }
}
