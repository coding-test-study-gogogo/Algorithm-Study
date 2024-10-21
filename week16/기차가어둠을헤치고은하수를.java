package week16;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 기차가어둠을헤치고은하수를 {
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        boolean[] visited = new boolean[1<<21];


        for (int i = 0; i < M; i++) {
            String[] tt = br.readLine().split(" ");
            int order = Integer.parseInt(tt[0]);
            int train = Integer.parseInt(tt[1]);

            if (order == 1) {
                int seat = Integer.parseInt(tt[2]);
                arr[train] = (arr[train] | (1<<seat));
            } else if (order == 2){
                int seat = Integer.parseInt(tt[2]);
                arr[train] = (arr[train] & ~(1<<seat));
            } else if (order == 3) {
                arr[train] = arr[train] << 1;
                arr[train] = (arr[train] &((1<<21)-1));
            } else if (order == 4){
                arr[train] = arr[train] >> 1;
                arr[train] = (arr[train] & ~1);
            }
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[arr[i]]) {
                ans++;
                visited[arr[i]] = true;
            }
        }
        System.out.println(ans);
    }
}
