package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 퇴사2 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine().trim());
	
		int[] T = new int[N + 1];
		int[] P = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		int max = profit(N, T, P);
		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
	}
	public static int profit(int N, int[] T, int[] P) {
		int dp[] = new int[N + 2];
		
		for(int i = 1; i <= N; i++) {
			dp[i] = Math.max(dp[i], dp[i - 1]);
			
			int next = i + T[i];
			
			if(next <= N + 1) {
				dp[next] = Math.max(dp[next], dp[i] + P[i]);
			}
		}
		int max = 0;
		for(int i = 1; i <= N + 1; i++) {
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}
