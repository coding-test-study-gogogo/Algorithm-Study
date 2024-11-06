package week18.olsohee;

import java.util.*;

class 괄호_변환 {
    public String solution(String p) {
        if (isCollectWord(p)) return p;
        return func(p);
    }

    private String func(String str) {
        if (str.equals("")) return str;

        // u와 v로 분리하기
        int idx = divide(str);
        String u = str.substring(0, idx + 1);
        String v = str.substring(idx + 1);

        if (isCollectWord(u)) {
            return u + func(v);
        } else {
            v = func(v);
            String result = "(" + v + ")";
            for (char c : u.substring(1, u.length() - 1).toCharArray()) {
                result += (c == '(') ? ')' : '(';
            }
            return result;
        }
    }

    private boolean isCollectWord(String str) {
        Stack<Character> st = new Stack<>();
        for (char c : str.toCharArray()) {
            if (st.isEmpty()) {
                st.push(c);
                continue;
            }
            // 여는 괄호인 경우
            if (c == '(') {
                st.push(c);
            }
            // 닫는 괄호인 경우
            else {
                if (st.peek() == '(') {
                    st.pop();
                } else {
                    st.push(c);
                }
            }
        }

        return st.size() == 0;
    }

    private int divide(String str) {
        int openCnt = 0;
        int closedCnt = 0;
        int idx = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') openCnt++;
            if (str.charAt(i) == ')') closedCnt++;
            if (openCnt == closedCnt) {
                idx = i;
                break;
            }
        }
        return idx;
    }
}
