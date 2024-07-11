import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int N,M;
    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr=new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j =1; j <= N; j++) {
                arr[i][j] = arr[i][j-1] + arr[i-1][j] - arr[i-1][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            int result = arr[endX][endY] - arr[startX-1][endY] - arr[endX][startY-1] + arr[startX-1][startY-1];
            System.out.println(result);
        }



    }
}