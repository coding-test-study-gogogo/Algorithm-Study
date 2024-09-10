package week10.almondBreez3;

import java.util.*;
import java.io.*;

class 빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 세로 H, 가로 W
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        // 블록 높이 정보
        int[] height = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        // 고인 물의 양 계산
        int result = 0;

        // 각 열의 왼쪽 최대 높이 저장
        int[] leftMax = new int[W];
        leftMax[0] = height[0];
        for (int i = 1; i < W; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 각 열의 오른쪽 최대 높이 저장
        int[] rightMax = new int[W];
        rightMax[W - 1] = height[W - 1];
        for (int i = W - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 각 칸에서 고일 수 있는 물 계산
        for (int i = 0; i < W; i++) {
            // 현재 칸에서 물이 고일 수 있는 높이
            int waterHeight = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (waterHeight > 0) {
                result += waterHeight;
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}
