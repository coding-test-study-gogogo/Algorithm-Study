// 왜안돼? 7-28
package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		int left = 0;
		int right = 0;
		
		for(int height : arr) {
			if(height > right)
				right = height;
		}
		
		int count = 0;
		
		while(left < right) {
			int mid = (left + right) / 2;
			long total = 0;
		
			for (int i = 0; i < N; i++) {
                if (arr[i] > mid) {
                    total += (long)(arr[i] - mid);
                }
            }
			
			if(total < M) {
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		System.out.println(left - 1);
	}
}
