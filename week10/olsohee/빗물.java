package week10.olsohee;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.*;

public class 빗물 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] height = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int[] left = new int[w];
        for (int i = 0; i < w; i++) {
            max = Math.max(max, height[i]);
            left[i] = max;
        }

        max = 0;
        int[] right = new int[w];
        for (int i = w - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            right[i] = max;
        }

        // 고이는 빗물 계산
        int answer = 0;
        for (int i = 0; i < w; i++) {
            answer += Math.min(left[i], right[i]) - height[i];
        }

        System.out.println(answer);
    }
}
