package week4.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 그롭_단어_체커 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().toCharArray();

            boolean[] visited = new boolean[26];
            char before = arr[0];
            visited[arr[0] - 97] = true;

            // 그룹단어인지 판단
            boolean canAnswer = true;
            for (int j = 1; j < arr.length; j++) {
                if (before == arr[j]) {
                    continue;
                }
                if (!visited[arr[j] - 97]) {
                    visited[arr[j] - 97] = true;
                    before = arr[j];
                } else {
                    canAnswer = false;
                    break;
                }
            }
            if (canAnswer) answer++;
        }

        System.out.println(answer);
    }
}
