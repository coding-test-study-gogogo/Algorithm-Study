package week8.AlmondBreez3;

import java.util.*;

class 롤케잉크 자르기{
    public int solution(int[] topping) {
        int N = topping.length;
        Set<Integer> a = new HashSet<>();
        Set<Integer> b = new HashSet<>();
        int[] leftUniqueCount = new int[N];
        int[] rightUniqueCount = new int[N];

        // 왼쪽 부분의 고유한 토핑 수 계산
        for (int i = 0; i < N; i++) {
            a.add(topping[i]);
            leftUniqueCount[i] = a.size();
        }

        // 오른쪽 부분의 고유한 토핑 수 계산
        for (int i = N - 1; i >= 0; i--) {
            b.add(topping[i]);
            rightUniqueCount[i] = b.size();
        }

        // 동일한 경우의 수를 계산
        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            if (leftUniqueCount[i] == rightUniqueCount[i + 1]) {
                result++;
            }
        }

        return result;
    }
}
