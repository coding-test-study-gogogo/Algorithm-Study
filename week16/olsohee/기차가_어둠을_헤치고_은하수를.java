package week16.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 기차가_어둠을_헤치고_은하수를 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] trains = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int trainNum = Integer.parseInt(st.nextToken());
            int idx = 0;
            switch (command) {
                case 1:
                    idx = Integer.parseInt(st.nextToken());
                    trains[trainNum] |= (1 << idx - 1);
                    break;
                case 2:
                    idx = Integer.parseInt(st.nextToken());
                    trains[trainNum] &= ~(1 << idx - 1);
                    break;
                case 3:
                    trains[trainNum] = (trains[trainNum] & ~(1 << 19)) << 1;
                    break;
                case 4:
                    trains[trainNum] = trains[trainNum] >> 1;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(trains[i]);
        }

        System.out.println(set.size());
    }
}
