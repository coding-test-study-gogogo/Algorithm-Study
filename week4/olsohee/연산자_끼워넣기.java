package week4.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연산자_끼워넣기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[] nums;
    static int[] operators = new int[4];
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
            operators[i] = Integer.parseInt(st.nextToken());
        }

        getCombination(nums[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void getCombination(int result, int idx) {
        if (idx == nums.length) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                switch (i) {
                    case 0:
                        getCombination(result + nums[idx], idx + 1);
                        break;
                    case 1:
                        getCombination(result - nums[idx], idx + 1);
                        break;
                    case 2:
                        getCombination(result * nums[idx], idx + 1);
                        break;
                    case 3:
                        int newResult = 0;
                        if (result < 0) {
                            result *= -1;
                            newResult = -1 * (result / nums[idx]);
                        } else {
                            newResult = result / nums[idx];
                        }
                        getCombination(newResult, idx + 1);
                        break;
                }
                operators[i]++;
            }
        }
    }
}
