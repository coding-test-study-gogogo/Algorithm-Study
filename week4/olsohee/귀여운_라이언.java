package week4.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 귀여운_라이언 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> lionList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) lionList.add(i);
        }

        if (lionList.size() < k) {
            System.out.println(-1);
            return;
        }

        int start = 0;
        int end = k - 1;
        int answer = Integer.MAX_VALUE;
        while (end < lionList.size()) {
            answer = Math.min(answer, lionList.get(end) - lionList.get(start) + 1);
            start++;
            end++;
        }

        System.out.println(answer);
    }
}
