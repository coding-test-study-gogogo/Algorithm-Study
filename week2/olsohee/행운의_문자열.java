package week2.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 행운의_문자열 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[] arr;
    static int[] alphabet = new int[27];
    static int answer;

    public static void main(String[] args) throws IOException {

        arr = br.readLine().toCharArray();
        for (char c : arr) {
            alphabet[c - 'a']++;
        }

        dfs(0, ' ');
        System.out.println(answer);
    }

    private static void dfs(int len, char pre) {

        if (len == arr.length) {
            answer++;
            return;
        }

        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] > 0 && (char)(i + 'a') != pre) {
                alphabet[i]--;
                dfs(len + 1, (char)(i + 'a'));
                alphabet[i]++;
            }
        }
    }
}
