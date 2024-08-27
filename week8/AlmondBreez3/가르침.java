package week8.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 가르침 {
    public static int N,M;
    public static List<char[]> arr = new ArrayList<>();
    public static List<Character> lis = new ArrayList<>();
    public static int[] alpha = new int[27];
    public static boolean[] visited;
    public static int min = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[M];

        alpha[0] = 1;
        alpha['n'-'a'] = 1;
        alpha['t'-'a'] = 1;
        alpha['i'-'a'] = 1;
        alpha['c'-'a'] = 1;
        M = M - 5;

        if (M < 0) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N;i++) {
            char[] str = br.readLine().toCharArray();
            arr.add(str);
            for (int j = 4; j <= str.length - 5; j++) {
                if (alpha[str[j]-'a'] != 1 && !lis.contains(str[j])) {
                    lis.add(str[j]);
                }
            }
        }


        dfs(0,0);
        System.out.println(min);
    }
    public static void dfs(int idx,int depth) {
        if (depth == M) {
            min = Math.max(min, calculate());
            return;
        }
        if (idx == lis.size()) {
            min = Math.max(min, calculate());
            return;
        }

        if (alpha[lis.get(idx)-'a'] != 1) {
            alpha[lis.get(idx)-'a'] = 1;
            dfs(idx+1,depth+1);
            alpha[lis.get(idx)-'a'] = 0;
        }
        dfs(idx+1,depth);

    }
    public static int calculate() {
        int result = 0;
        int temp = 0;
        for (char[] a : arr) {
            boolean flag = true;
            for (char t: a) {
                if (alpha[t-'a'] != 1) {
                    flag = false;
                }
            }
            if (flag) {
                result++;
            }
        }
        return result;
    }
}