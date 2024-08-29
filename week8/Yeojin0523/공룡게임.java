package week8;
import java.util.*;
public class 공룡게임 {

	static int[] map;
	static boolean tree = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		map = new int[N];
		
		map[0] = 0;
		
		for(int i = 1; i < N; i++) {
			
		}
		
		
		
		sc.close();
	}
}
/*
1번 인덱스는 0
나머지에는 0, 1, 2가 가능한거임
근데, 2는 적어도 한 번 나와야 해
근데 2가 연속으로 2번 나오거나, 세 칸 연속 차있으면 안돼
map의 경우의 수 return
map의 길이는 1~1000
경우의 수 / 1,000,000,007로 나눈 나머지 출력
N + 1에 도착하면 깰 수 있다.

경우 따지기. 
음.. N + 1 = 0이잖아

N + 1에 착지해야 하니까

n, n-1에 1, 2 / 2,1 가능하고
0, 0
1, 0 / 0, 1
2, 0 / 0, 2

그리고 n번 반복

뒤에서부터 온다고 생각하면, 

*/