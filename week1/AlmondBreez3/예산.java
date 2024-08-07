import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
        }
        Arrays.sort(arr);
        long total = Integer.parseInt(br.readLine());

        long max = arr[N-1];
        max++;
        long min = 0;
        long mid = 0;

        while (min < max) {
            mid = (max + min) / 2;


            long count = 0;


            for (long a : arr) {
                if (mid < a) {
                    count += mid;
                } else {
                    count += a;
                }
            }

            if(count > total) {
                max = mid;
            }
            else {
                min = mid + 1;
            }
        }


        System.out.println(min-1);
    }
}