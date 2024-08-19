package week6;
import java.util.Scanner;
public class PuyoPuyo {

	public static String[][] puyo;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static int count = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 12; j++) {
				puyo[i][j] = sc.next();
			}
		} // 입력받는다. 
	
		for(int i = 11; i >= 0; i--) {
			for(int j = 0; j < 6; j++) {
				if(checkIfSame = 4)
					break;
			}
		}
		
		sc.close();
	}
	public static int checkIfSame(String puyo, int i, int j) {
		if(puyo.equals(puyo + dx[i])) {
			count++;
			checkIfSame(puyo + dx[i], i + 1, j); // 오른쪽으로 가기
		}
		if(puyo) {
			// 위로 가기
		}
		
			return count;
	}
	

}
