package week7;
import java.util.Arrays;
import java.util.Scanner;
public class k번째수 {

	public static int N;
	public static int K;
	public static int[][]A;
	public static int[]B;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		A = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				A[i][j] = i * j;
			}
		} // 배열 초기화
		B = new int[N * N + 1];
		int k = 1;
		
		for (int i = 1; i <= N; i++) {  // Notice the change here from <N to <=N
            for (int j = 1; j <= N; j++) {
                B[k++] = A[i][j];
            }
        }
		
		Arrays.sort(B);
		System.out.println(B[K]);
		
		sc.close();
	}

}
/*
1 2 3
2 4 6 -> 1 2 3 2 4 6 3 6 9 -> sort
3 6 9
*/