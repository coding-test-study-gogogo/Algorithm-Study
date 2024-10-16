package week13.olsohee;

import java.util.*;

class 귤_고르기 {
    public int solution(int k, int[] tangerine) {
        // Map에 넣기
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : tangerine) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        // 사이즈별 객체로 만들기
        List<Size> list = new ArrayList<>();
        for (int size : map.keySet()) {
            list.add(new Size(size, map.get(size)));
        }

        // 객체 정렬
        Collections.sort(list, (o1, o2) -> o2.cnt - o1.cnt);

        // 갯수가 많은 것 우선으로 고르기 (순차 탐색)
        int answer = 0; // 종류 수
        for (int i = 0; i < list.size(); i++) {
            if (k <= 0) break;
            k -= list.get(i).cnt;
            answer++;
        }

        return answer;
    }

    private class Size {

        int size;
        int cnt;

        public Size(int size, int cnt) {
            this.size = size;
            this.cnt = cnt;
        }
    }
}
