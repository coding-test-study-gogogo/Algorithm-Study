package week7.AlmondBreez3;

import java.io.*;
import java.util.*;

public class A_B {
    public static long N, M;
    public static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        dfs(1, N);
        System.out.println(min == Long.MAX_VALUE ? -1 : min);
    }

    public static void dfs(int depth, long num) {
        if (num == M) {
            min = Math.min(depth, min);
            return;
        }
        if (num > M) {
            return;
        }

        // Perform operations
        long numDoubled = num * 2;
        long numAppended = Long.parseLong(num + "1");

        dfs(depth + 1, numDoubled);
        dfs(depth + 1, numAppended);
    }
}

