package week9.Almondbreez3;

import java.util.*;
import java.io.*;

class 달팽이는올라가고싶다 {
    public static int A, B, V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        // Calculate the number of days
        int days = (V - B) / (A - B);
        if ((V - B) % (A - B) != 0) {
            days++;
        }

        System.out.println(days);
    }
}
