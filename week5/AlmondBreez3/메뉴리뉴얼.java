package week5.AlmondBreez3;

import java.util.*;
class 메뉴리뉴얼 {
    public String[] order;
    public boolean[] alphaVisited;
    public boolean[] alpha = new boolean[26];
    public int max = Integer.MIN_VALUE;
    public List<String> result = new ArrayList<>();

    public String[] solution(String[] orders, int[] course) {
        // 탐색 문자열 갯수 작은 것 부터 탐색하기
        order = orders;
        alphaVisited = new boolean[27];

        // 초기화
        for (String s : orders) {
            for (char c : s.toCharArray()) {
                alpha[c - 'A'] = true;
            }
        }

        for (int c : course) {
            max = 0;
            char[] newArr = new char[c];
            dfs(c, 0, 0, newArr);
        }

        String[] answer = result.toArray(new String[0]);
        Arrays.sort(answer);
        return answer;
    }

    public void dfs(int cur, int depth, int start, char[] newArr) {
        if (depth == cur) {
            calculate(newArr);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!alphaVisited[i] && alpha[i]) {
                alphaVisited[i] = true;
                newArr[depth] = (char) ('A' + i);
                dfs(cur, depth + 1, i + 1, newArr);
                alphaVisited[i] = false;
            }
        }

    }

    private void calculate(char[] arrToCal) {
        List<String> tempArr = new ArrayList<>();
        int count = 0;
        for (String s : order) {
            boolean flag = true;
            for (char c : arrToCal) {
                if (!s.contains(String.valueOf(c))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }

        if (count >= 2) {
            String temp = new String(arrToCal);
            if (count > max) {
                max = count;
                result.removeIf(s->s.length()==arrToCal.length);
                result.add(temp);
            } else if (count==max) {
                result.add(temp);
            }

        }
    }
}
