package week3.olsohee;

import java.util.*;

class 롤케이크_자르기 {
    public int solution(int[] topping) {
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int[] arr1 = new int[10001];
        int[] arr2 = new int[10001];

        for (int n : topping) {
            que1.add(n);
            set1.add(n);
            arr1[n]++;
        }

        // 메인 로직 시작
        int answer = 0;
        int i = 0;
        while (!que1.isEmpty()) {

            int num = que1.poll();
            arr1[num]--;
            if (arr1[num] == 0) {
                set1.remove(num);
            }

            que2.add(num);
            arr2[num]++;
            if (arr2[num] == 1) {
                set2.add(num);
            }

            if (set1.size() == set2.size()) {
                answer++;
            }
            i++;
        }

        return answer;
    }
}
