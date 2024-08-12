package week6.AlmondBreez3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자_끼워넣기 {
    public static int N;
    public static int[] arr;
    public static boolean[] visited;
    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;
    public static int[] oper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        oper = new int[4];
        visited = new boolean[4];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i]=Integer.parseInt(st.nextToken());
        }


        dfs(0,arr[0]);


        System.out.println(max);
        System.out.println(min);
    }
    public static void dfs(int depth, int sum) {
        if (depth == N-1) {
            min =Math.min(min,sum);
            max = Math.max(max,sum);
            return;
        }


        for (int j = 0; j <4;j++) {
            if (oper[j]>0) {
                if (oper[j]>0 && j==0) {
                    visited[j] = true;
                    oper[j]-=1;
                    dfs(depth+1,sum+arr[depth+1]);
                    oper[j] +=1;
                    visited[j] = false;
                } else if(oper[j]>0 && j==1) {
                    visited[j] = true;
                    oper[j]-=1;
                    dfs(depth+1,sum-arr[depth+1]);
                    oper[j] +=1;
                    visited[j] = false;
                }else if(oper[j]>0 && j==2) {
                    visited[j] = true;
                    oper[j]-=1;
                    dfs(depth+1,sum*arr[depth+1]);
                    oper[j] +=1;
                    visited[j] = false;
                }else if(oper[j]>0 && j==3) {
                    visited[j] = true;
                    oper[j]-=1;
                    dfs(depth+1,sum/arr[depth+1]);
                    oper[j] +=1;
                    visited[j] = false;
                }
            }
        }



    }
}
