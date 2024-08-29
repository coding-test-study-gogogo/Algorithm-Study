package week8;
import java.util.Scanner;
public class 알고스팟 {

	public static int N;
	public static int M;
	public static int[][] array;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		array = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				array[i][j] = sc.nextInt();
			}
		} // 입력받고
		
		gogo(array[0][0]);
		
		
		
		sc.close();
	}
	public static void gogo(int[][] start) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				start[i][j] + dx;
			}
		}
	}

}
