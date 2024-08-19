package week6.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연산자_끼워넣기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] nums;
    static int[] operations = new int[4];
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, nums[0], operations);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int idx, int result, int[] operations) {
        if (idx == n) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                operations[i]--;
                if (i == 0) {
                    dfs(idx + 1, result + nums[idx], operations);
                }
                if (i == 1) {
                    dfs(idx + 1, result - nums[idx], operations);
                }
                if (i == 2) {
                    dfs(idx + 1, result * nums[idx], operations);
                }
                if (i == 3) {
                    dfs(idx + 1, result / nums[idx], operations);
                }
                operations[i]++;
            }
        }

    }
}
