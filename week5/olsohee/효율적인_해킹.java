package week5.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 효율적인_해킹 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static int[] cntArr;
    static int n, m;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        cntArr = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            list.get(num1).add(num2);
        }

        // dfs
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            dfs(i);
        }

        int max = 0;
        for (int i : cntArr) {
            max = Math.max(max, i);
        }

        for (int i = 1; i <= n; i++) {
            if (max == cntArr[i]) {
                System.out.print(i + " ");
            }
        }
    }

    private static void dfs(int start) {
        visited[start] = true;
        for (int num : list.get(start)) {
            if (!visited[num]) {
                cntArr[num]++;
                dfs(num);
            }
        }
    }
}
