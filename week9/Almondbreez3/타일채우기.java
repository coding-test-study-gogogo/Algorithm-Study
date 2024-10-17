package week9.Almondbreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 타일채우기 {
    public static int N;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());
        arr =new int[N+1];

        arr[0]=0;
        // 홀수이면 무조건 0이다
        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }

        arr[2] = 3;
        for (int i = 4; i <= N; i++) {
            // 지난 짝수 값 * 3 + 2개
            arr[i] = arr[i-2] * 3 + 2;

            // 4개씩 만들어지면 새로운 패턴이 추가로 만들어져서 고려해줘야 한다(1차 풀이때 놓침)
            for (int j = 4; j <= i;j+=2) {
                //  각 이전 상태에서 2가지 새로운 패턴이 생길 수 있습니다.
                arr[i] += arr[i-j] * 2;
            }
        }

        System.out.println(arr[N]);
    }
}