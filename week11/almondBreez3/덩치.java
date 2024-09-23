package week11.almondBreez3;
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 덩치 {
    public static int N;
    public static int[][] arr;
    public static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i =0; i < N; i++) {
            int tempX = arr[i][0];
            int tempY = arr[i][1];
            for (int j = 0; j < N; j++) {
                if (i!=j) {
                    if (tempX<arr[j][0] && tempY<arr[j][1]) {
                        map.put(i,map.getOrDefault(i,0)+1);
                    }
                }
            }
        }

        check();
    }
    public static void check() {

        for (int i =0; i < N; i++) {
            System.out.print(map.getOrDefault(i,0)+1+" ");
        }
    }
}