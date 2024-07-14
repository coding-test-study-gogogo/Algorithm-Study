// 2024-07-13
package week1;
import java.util.*;

public class 예산 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int left = 0, right = -1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();  
            right = Math.max(right, arr[i]);
        }
        
        int m = sc.nextInt(); 
        while (left <= right) {
            int mid = (left + right) / 2;
            long budget = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] > mid) budget += mid;
                else budget += arr[i];
            }
            if (budget <= m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
        sc.close();
    }
}

