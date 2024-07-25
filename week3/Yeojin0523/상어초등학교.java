// 미완성. 내일 다시..
package week3;

import java.io.BufferedReader;
import java.util.*;

public class 상어초등학교 {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] arr;
	static int[] numOfStu;
	static int[] numOfLikes;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));'
		StringTokenizer st;
		
		int N = stoi(br.readLine());
		arr = new int[N][N];
		numOfStu = new int[N * N];
		numOfLikes = new int[4];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N * N; i++) {
			numOfStu[i] = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 4; j++) {
				numOfLikes[j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
	static void solve(int row, int cols, int N) {
		int[] count = new int[4];
		int maxCount = 0;
		
		for(int i = 0 ; i < N * N; i++) {
			for(int j = 0; j < 4; j++) {
				int newRow = row + dx[j];
				int newCols = cols + dy[j];
				
				for(int k = 0; k < 4; k++) {
					if(arr[newRow][newCols] == numOfLikes[k])
						count[j]++;
				}
				Math.max(maxCount, count[j]);
			}
		}
	}
}
