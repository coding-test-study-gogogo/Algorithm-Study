package week9.Almondbreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 알람시계 {
    public static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int hour = 0;
        int min = 0;
        int fortyFive = 45 * 60;
        if (N!=0) {
            N = N * 60 * 60;
            M = M * 60;
            int temp = N+M;
            hour = ((temp) -fortyFive) / 3600;
            min =  (((temp) -fortyFive) / 60 ) % 60;
        } else {
            N = 24 * 60 * 60;
            M = M * 60;
            int temp = N+M;
            hour = ((temp) -fortyFive) / 3600;
            min =  (((temp) -fortyFive) / 60 ) % 60;
        }
        if (hour ==24) {
            hour = 0;
        }
        System.out.println(hour + " "+ min);
    }
}
