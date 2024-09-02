package week8.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 괄호의_값 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        char[] arr = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int temp = 1;
        int answer = 0;
        boolean flag = true;

        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty() || arr[i] == '(' || arr[i] == '[') {
                stack.add(arr[i]);
                temp *= (arr[i] == '(') ? 2 : 3;
                continue;
            }

            // 닫는 괄호인 경우
            if (arr[i] == ')') {
                if (stack.peek() != '(') {
                    flag = false;
                    break;
                }

                if (arr[i - 1] == '(') {
                    answer += temp;
                }
                stack.pop();
                temp /= 2;
            }
            if (arr[i] == ']') {
                if (stack.peek() != '[') {
                    flag = false;
                    break;
                }

                if (arr[i - 1] == '[') {
                    answer += temp;
                }
                stack.pop();
                temp /= 3;
            }
        }
        if (!flag || !stack.isEmpty()) {
            System.out.println(0);
            return;
        }

        System.out.println(answer);
    }
}
