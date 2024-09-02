package week8.olsohee;

import java.util.*;

class 롤케이크_자르기 {
    public int solution(int[] topping) {

        int answer = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : topping) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }

        for (int num : topping) {
            map2.put(num, map2.get(num) - 1);
            if (map2.get(num) == 0) {
                map2.remove((Object) num);
            }
            map1.put(num, map1.getOrDefault(num, 0) + 1);

            if (map1.size() == map2.size()) {
                answer++;
            }
        }

        return answer;
    }
}
