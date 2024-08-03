package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 바이러스 {
    public static int N, M;
    public static List<List<Integer>> arr = new ArrayList<>();
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visited=new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        int res = bfs(1);
        if (res==0) {
            System.out.println(0);
            return;
        }
        System.out.println(res-1);
    }
    public static int bfs(int num) {
        Queue<Integer> a = new LinkedList<>();
        List<Integer> temp = arr.get(num);

        for (int c : temp) {
            a.add(c);
        }
        int count = 0;

        while (!a.isEmpty()) {
            int tem = a.poll();
            if (!visited[tem]) {
                visited[tem] = true;
                List<Integer> t = arr.get(tem);
                for (int c : t) {
                    a.add(c);
                }
                count++;
            }
        }

        return count;
    }
}
