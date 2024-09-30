package week12.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 탑 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] lengthArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            lengthArr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[n + 1];
        Stack<Integer> stack = new Stack<>();

        for (int i = n; i >= 1; i--) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && lengthArr[stack.peek()] < lengthArr[i]) {
                answer[stack.pop()] = i;
            }
            stack.push(i);
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
