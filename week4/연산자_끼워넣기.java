package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자_끼워넣기 {
    public static int N;
    public static int a,b;
    public static int[] arr;
    public static int[] bArr;
    public static boolean[] visited;
    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }
        arr = new int[4];
        visited = new boolean[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        dfs(0,0);

    }
    public static void dfs(int n,int sum) {
        if (n == 3) {
            min = Math.min(sum,min);
            max = Math.max(sum,max);
            return;
        }

        for (int i = 0; i < 4 ;i++) {
            if (!visited[i]) {
                if (i == 0) {
                    sum += arr[n];
                    visited[i] = true;
                    dfs(n+1,sum);
                    visited[i] = false;
                    sum -= arr[n];
                } else if (i == 1) {
                    sum -= arr[n];
                    visited[i] = true;
                    dfs(n+1,sum);
                    visited[i] = false;
                    sum += arr[n];
                } else if (i == 2) {
                    sum = sum * arr[n];
                    visited[i] = true;
                    dfs(n+1,sum);
                    visited[i] = false;
                    sum = sum / arr[n];
                } else if ( i ==3) {
                    sum = sum / arr[n];
                    visited[i] = true;
                    dfs(n+1,sum);
                    visited[i] = false;
                    sum = sum * arr[n];
                }
            }
        }

    }
}
