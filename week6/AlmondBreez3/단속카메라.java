package week6.AlmondBreez3;

import java.util.*;

class 단속카메라 {
    public int solution(int[][] routes) {
        // 진출 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (a1, a2) -> a1[1] - a2[1]);

        int count = 1;  // 첫 번째 카메라
        int cameraPosition = routes[0][1];  // 첫 번째 카메라를 첫 번째 차량의 진출 지점에 설치

        for (int i = 1; i < routes.length; i++) {
            int temStart = routes[i][0];
            int temEnd = routes[i][1];

            // 현재 카메라 위치가 다음 차량의 구간에 포함되지 않으면 새로운 카메라 설치
            if (cameraPosition < temStart) {
                cameraPosition = temEnd;  // 새로운 카메라는 현재 차량의 진출 지점에 설치
                count++;
            }
        }

        return count;
    }
}
