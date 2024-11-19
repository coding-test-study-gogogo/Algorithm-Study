package week8.AlmondBreez3;

import java.io.*;
import java.util.Stack;

public class 괄호의값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();


        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int temp = 1;

        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);

            if (cur == '(') {
                stack.push(cur);
                temp *= 2; // '('는 2로 곱해줌
            } else if (cur == '[') {
                stack.push(cur);
                temp *= 3; // '['는 3으로 곱해줌
            } else if (cur == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0; // 유효하지 않은 경우
                    break;
                }
                if (line.charAt(i - 1) == '(') {
                    answer += temp;
                }
                stack.pop(); // '(' 제거
                temp /= 2; // 2로 나누어줌
            } else if (cur == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0; // 유효하지 않은 경우
                    break;
                }
                if (line.charAt(i - 1) == '[') {
                    answer += temp;
                }
                stack.pop(); // '[' 제거
                temp /= 3; // 3으로 나누어줌
            } else {
                answer = 0; // 유효하지 않은 문자가 포함된 경우
                break;
            }
        }

        if (!stack.isEmpty()) {
            answer = 0; // 모든 괄호가 짝이 맞지 않는 경우
        }

        System.out.println(answer);
    }
}
