package week6.olsohee;

import java.util.*;

class 단속카메라 {
    public int solution(int[][] routes) {

        Arrays.sort(routes, ((o1, o2) -> {
            return o1[1] - o2[1];
        }));

        int answer = 1;
        int lastLocation = routes[0][1];
        for (int i = 1; i < routes.length; i++) {
            if (lastLocation < routes[i][0]) {
                answer++;
                lastLocation = routes[i][1];
            }
        }
        // 최소 몇 대의 카메라 설치?
        return answer;
    }
}
