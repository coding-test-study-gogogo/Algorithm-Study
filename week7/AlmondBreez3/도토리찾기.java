package week7.AlmondBreez3;

import java.util.*;
import java.io.*;

class 도토리찾기 {
    public static int totalBox, K, D;
    public static int[][] rules;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        totalBox = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        rules = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }

        // 이진 탐색을 통해 D번째 도토리가 들어 있는 마지막 상자 번호를 찾습니다.
        System.out.println(findBoxWithDthAcorn());
    }

    private static int findBoxWithDthAcorn() {
        int left = 1;
        int right = totalBox;

        while (left <= right) {
            int mid = (left + right) / 2;
            long acorns = countAcorns(mid);

            if (acorns >= D) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left; // 마지막으로 찾은 위치가 D번째 도토리가 들어 있는 상자입니다.
    }

    private static long countAcorns(int box) {
        long count = 0;

        for (int[] rule : rules) {
            int start = rule[0];
            int end = rule[1];
            int step = rule[2];

            if (start > box) continue;

            int effectiveEnd = Math.min(end, box);
            if (effectiveEnd >= start) {
                count += (effectiveEnd - start) / step + 1;
            }
        }

        return count;
    }
}
