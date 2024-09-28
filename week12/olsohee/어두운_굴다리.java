package week12.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 어두운_굴다리 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Integer> lightList = new ArrayList<>();
    static int n;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            lightList.add(Integer.parseInt(st.nextToken()));
        }

        // 이분탐색 (가로등 높이 찾기)
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = (start + end) / 2; // 가로등 높이

            if (isPossible(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }

    private static boolean isPossible(int mid) {
        int previous = 0;
        for (Integer idx : lightList) {
            if (idx - mid <= previous) {
                previous = idx + mid;
            } else {
                return false;
            }
        }
        if (previous < n) return false;
        return true;
    }
}
