package week10.almondBreez3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//x + y = k - z 라면, 가장 큰 k값을 찾아야 하므로, minus를 구할 때 N-1부터 시작하게 된다.
//List에 x+y의 값들을 넣어준다. 이제 2개의 값을 뺀 값이 List에 담겨있다면, 그 수는 정답가능성
public class 세수의합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)arr[i] = sc.nextInt();
        List<Integer> sum = new ArrayList<>();
        for (int i = 0 ; i < N ; i++){
            for (int j = 0 ; j < N; j++){
                sum.add(arr[i] + arr[j]);
            }
        }
        Arrays.sort(arr);
        Collections.sort(sum);

        for (int i = N-1; i>=0; i--){
            for (int j = N-1; j>=0; j--){
                int minus = arr[i] - arr[j];

                if (Collections.binarySearch(sum,minus)>=0){
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }
}
