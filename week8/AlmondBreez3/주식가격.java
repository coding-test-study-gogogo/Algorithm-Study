package week8.AlmondBreez3;

import java.util.Stack;

class 주식가격 {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // 가격 인덱스를 스택에 저장하고, 가격이 떨어지기까지의 시간을 계산합니다.
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        // 스택에 남아있는 인덱스는 마지막까지 가격이 떨어지지 않았으므로, 마지막 인덱스까지의 차이를 계산합니다.
        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = n - 1 - index;
        }

        return result;
    }
}
