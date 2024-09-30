package week12.olsohee;

import java.util.*;

class 시소짝꿍 {
    public long solution(int[] weights) {

        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>();
        long answer = 0;

        for (int i = 0; i < weights.length; i++) {
            double w = weights[i];
            double n1 = w * 1.0;
            double n2 = w * (2.0 / 3.0);
            double n3 = w * (1.0 / 2.0);
            double n4 = w * (3.0 / 4.0);

            if (map.containsKey(n1)) answer += map.get(n1);
            if (map.containsKey(n2)) answer += map.get(n2);
            if (map.containsKey(n3)) answer += map.get(n3);
            if (map.containsKey(n4)) answer += map.get(n4);

            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        return answer;
    }
}
