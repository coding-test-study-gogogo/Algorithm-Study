package week10.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 세_수의_합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] nums;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        // a = c - b인 경우를 찾기
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                list.add(nums[i] + nums[j]);
            }
        }
        Collections.sort(list);

        // 이분탐색
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // nums[j] - nums[i]가 list에 있는지 확인
                if (binarySearch(nums[j] - nums[i])) {
                    answer = Math.max(answer, nums[j]);
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean binarySearch(int searchNum) {
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) == searchNum) return true;
            if (list.get(mid) > searchNum) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;
    }
}
