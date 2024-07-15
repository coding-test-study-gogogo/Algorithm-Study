package week2.AlmondBreez3;

import java.util.*;
class 가장큰수 {
    public String solution(int[] numbers) {

        // int를 String으로 저장
        ArrayList<String> lis = new ArrayList<>();
        for (int i = 0; i < numbers.length;i++) {
            lis.add(String.valueOf(numbers[i]));
        }

        // 조합해서 비교해서 정렬
        lis.sort((o1,o2)->{
            int a = Integer.parseInt(o1+o2);
            int b = Integer.parseInt(o2+o1);
            return Integer.compare(b,a);
        });

        // 정렬된 수를 나열해 문자열로 만듬
        StringBuilder sb = new StringBuilder();

        for (String s:lis) {
            sb.append(s);
        }

        // 문자열을 반환 맨 앞에 0이 있는 경우는 0만 반환하도록 예외 처리합니다. 예를 들어 000와 같은 문자열은 0으로 반환해야 한다.
        return sb.charAt(0) == '0' ? "0" :sb.toString();

    }
}