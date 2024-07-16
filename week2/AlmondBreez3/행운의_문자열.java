package week2.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 행운의_문자열{
    public static char[] arr;
    public static int[] alpha = new int[26]; // 알파벳 수에 맞게 크기 조정
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine().toCharArray();

        // 알파벳 빈도수 계산
        for (char c : arr) {
            alpha[c - 'a']++;
        }

        dfs(0, new StringBuilder());
        System.out.println(count);
    }

    public static void dfs(int idx, StringBuilder curStr) {
        if (curStr.length() == arr.length) { // 완성된 문자열의 길이가 입력 문자열의 길이와 같으면 카운트
            count++;
            return;
        }

        for (int i = 0; i < 26; i++) { // 0부터 25까지 반복
            if (alpha[i] == 0) continue;
            char currentChar = (char) (i + 'a');
            if (curStr.length() == 0 || curStr.charAt(curStr.length() - 1) != currentChar) {
                alpha[i]--;
                curStr.append(currentChar);
                dfs(idx + 1, curStr);
                curStr.deleteCharAt(curStr.length() - 1);
                alpha[i]++;
            }
        }
    }
}
