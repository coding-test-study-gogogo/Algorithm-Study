package week3.AlmondBreez3;

import java.util.*;
class 롤케이크_자르기 {
    HashSet<Integer> set = new HashSet<>();
    public int[] top;
    public int[] arr;
    public int[] arr2;
    public int solution(int[] topping) {
        top = topping;
        int N = topping.length;
        arr = new int[N];
        arr2 = new int[N];


        for (int i = 0; i < N; i++) {
            set.add(topping[i]);
            arr[i] = set.size();
        }
        set.clear();
        for (int i = N-1; i>=0; i--) {
            set.add(topping[i]);
            arr2[i] = set.size();
        }

        int res = 0;
        for (int i = 0; i < N-1; i++) {
            if (arr[i] == arr2[i+1]) {
                res++;
            }
        }

        int answer = res;
        return answer;
    }
}
