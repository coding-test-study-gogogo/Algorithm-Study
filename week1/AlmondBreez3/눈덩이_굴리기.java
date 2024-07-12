import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int N,M;
    public static int[] arr;
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        // 시작 크기 1
        // 시작 위치 0
        // 가장 큰 눈사람을 만들고 싶은 수수
        // 눈덩이를 굴리는 두가지 방법
        // 1. 현재 위치 i, i+1
        // 2. i+2
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        visited = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1,M,0);
        bw.write(String.valueOf(max));
        bw.flush();
    }
    public static void dfs(int sum, int depth,int idx) {
        if (depth == 0 || idx == N) {
            max = Math.max(max, sum);
            return;
        }
        if (idx +1 <= N) {
            dfs(sum+arr[idx+1],depth-1,idx+1);
        }
        if (idx + 2 <= N) {
            dfs(sum/2+arr[idx+2],depth-1,idx+2);
        }
    }
}