package week1.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 눈덩이_굴리기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] arr;
    static int maxSize = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dfs(2가지 방법 다 해보기)
        dfs(0, 0, 1);

        System.out.println(maxSize);
    }

    private static void dfs(int idx, int time, int size) {
        // 시간 끝났거나 더 갈 곳 없으면 종료
        if (time == m || idx == n) {
            maxSize = Math.max(maxSize, size);
            return;
        }

        // +1칸
        if (idx + 1 <= n) {
            dfs(idx + 1, time + 1, size + arr[idx + 1]);
        }

        // +2칸
        if (idx + 2 <= n) {
            dfs(idx + 2, time + 1, size / 2 + arr[idx + 2]);
        }
    }
}
