package week8.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가르침 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k;
    static List<String> words = new ArrayList<>();
    static int wordCnt = 0;
    static boolean[] visited = new boolean[26]; // 0(a) ~ 25(z)
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            words.add(br.readLine());
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }

        if (k == 26) {
            System.out.println(n);
            return;
        }

        visited['a' - 97] = true;
        visited['n' - 97] = true;
        visited['t' - 97] = true;
        visited['i' - 97] = true;
        visited['c' - 97] = true;
        wordCnt = 5;

        // 알파벳 조합
        dfs(97);
        System.out.println(answer);
    }

    private static void dfs(int start) {
        if (wordCnt == k) {
            getResult();
            return;
        }

        for (int i = start; i <= 122; i++) {
            if (!visited[i - 97]) {
                visited[i - 97] = true;
                wordCnt++;
                dfs(i + 1);
                visited[i - 97] = false;
                wordCnt--;
            }
        }
    }

    private static void getResult() {
        int cnt = 0;

        for (String word : words) {
            boolean flag = true;
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (!visited[arr[i] - 97]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cnt++;
            }
        }

        answer = Math.max(answer, cnt);
    }
}
