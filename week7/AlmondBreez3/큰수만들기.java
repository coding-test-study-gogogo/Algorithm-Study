package week7.AlmondBreez3;

import java.util.*;
class 큰수만들기{
    public int K;
    public int[] arr;
    public boolean[] visited;
    public List<Integer> list = new ArrayList<>();
    public List<String> resultArr = new ArrayList<>();
    public Stack<Integer> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    public String solution(String number, int k) {
        K = k;
        arr = number.chars().map(Character::getNumericValue)
                .toArray();
        for (int i = 0; i < number.length(); i++) {
            while (!stack.isEmpty() && k > 0 && stack.peek() < arr[i]) {
                stack.pop();
                k--;
            }
            stack.push(arr[i]); // Push the current element onto the stack
        }

        while(k > 0) {
            stack.pop();
            k--;
        }

        for (int a : stack) {
            sb.append(a);
        }
        String answer =sb.toString();
        return answer;
    }
}
