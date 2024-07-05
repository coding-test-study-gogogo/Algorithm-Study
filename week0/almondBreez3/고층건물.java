import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int N;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++ ) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int i = 0; i < N;i++) {
            result = Math.max(result, getInclination(i));
        }

        System.out.println(result);
    }

    public static int getInclination(int num) {
        //빌딩 개수
        int count = 0;
        //이전 기울기
        double temp = 0;

        //왼쪽 기울기 - 감소해야한다
        for (int i = num-1; i>=0;i--){
            double slope = (double) (arr[num]-arr[i]) / (num-i);

            if (i == num -1 || slope < temp) {
                count++;
                temp = slope;
            }
        }

        //오른쪽 기울기 - 증가해야한다
        for (int i = num +1 ; i < N ; i++) {
            double slope = (double) (arr[num]-arr[i]) / (num-i);

            if (i == num +1 || slope > temp) {
                count++;
                temp = slope;
            }
        }

        return count;

    }
}