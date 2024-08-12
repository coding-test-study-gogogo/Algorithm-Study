package week5.olsohee;

import java.util.*;

class 메뉴_리뉴얼 {

    Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {

        // orders의 각 주문들 알파벳 순 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // course 개수에 맞게 조합 만들고, 해당 조합이 몇 개인지 계산
        List<String> list = new ArrayList<>();

        for (int cnt : course) {
            System.out.println();
            System.out.println("cnt = " + cnt);
            for (String order : orders) {
                combination("", order, cnt);
            }

            // 각 코스의 메뉴 개수가 cnt이면서, value가 최대 값인 거 찾기
            int maxCnt = 0;
            List<String> menuCombiList = new ArrayList<>();
            for (String key : map.keySet()) {
                if (key.length() == cnt && map.get(key) >= 2) {
                    if (maxCnt < map.get(key)) {
                        maxCnt = map.get(key);
                        menuCombiList.clear();
                        menuCombiList.add(key);
                    } else if (maxCnt == map.get(key)) {
                        menuCombiList.add(key);
                    }
                }
            }

            for (String menuCombi : menuCombiList) {
                System.out.println(menuCombi);
                list.add(menuCombi);
            }
        }

        Collections.sort(list);

        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private void combination(String result, String orders, int cnt) {
        if (cnt == 0) {
            map.put(result, map.getOrDefault(result, 0) + 1);
            return;
        }


        for (int i = 0; i < orders.length(); i++) {
            combination(result + orders.charAt(i), orders.substring(i + 1), cnt - 1);
        }
    }
}
