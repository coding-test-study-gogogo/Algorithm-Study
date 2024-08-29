package week8;

import java.util.Scanner;
// 모르겠다..... -> 이해 못함
public class 주식가격 {

	public static int top;
	public static int[] stack;
	public static int size;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] prices = new int[n];

		for (int i = 0; i < n; i++) {
			prices[i] = sc.nextInt();
		}

		// 스택 초기화
		주식가격.stack = new int[n];
		주식가격.top = -1;

		// 솔루션 실행
		int[] result = solution(prices);

		// 결과 출력
		for (int res : result) {
			System.out.print(res + " ");
		}

		sc.close();
	}

	public static int[] solution(int[] prices) {
		int n = prices.length;
		int[] answer = new int[n];
		stack = new int[n];
		top = -1;

		for (int i = 0; i < n; i++) {
			// 스택에 남아있는 값들 중, 현재 가격보다 높은 값들을 처리
			while (top != -1 && prices[stack[top]] > prices[i]) {
				int idx = pop();
				answer[idx] = i - idx;
			}
			push(i);
		}

		// 스택에 남아있는 값들 처리 (끝까지 가격이 떨어지지 않은 경우)
		while (top != -1) {
			int idx = pop();
			answer[idx] = n - idx - 1;
		}

		return answer;
	}

	// Push 메서드
	public static void push(int item) {
		if (top < size - 1) {
			stack[++top] = item;
		}
	}

	// Pop 메서드
	public static int pop() {
		if (top == -1) {
			return -1;
		}
		return stack[top--];
	}
}

