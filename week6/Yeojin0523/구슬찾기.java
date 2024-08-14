package week6;
import java.util.Scanner;
public class 구슬찾기 {

	public static int N;
	public static int M;
	public static int[][] array;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		array = new int[M][2];
		
		for(int i = 0; i < array.length; i++) {
			array[i][0] = sc.nextInt();
			array[i][1] = sc.nextInt();
		}
		
		sc.close();
	}
	
	
		

}
