package week2.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 후보_추천하기 {
    public static int N,M;
    public static List<Integer> lis = new ArrayList<>();
    public static int[] arr;
    public static HashMap<Integer,Integer> result = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // HashSet이용, list이용, Array이용

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[M];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = -1;
        for (int i =0; i< M;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            h = Math.max(arr[i],h);
        }

        for (int i = 1; i<= h; i++) {
            result.put(i,0);
        }

        for (int i =0; i < M; i++) {
            int temp = arr[i];

            if (lis.size() < N) {
                if (lis.contains(temp)) {
                    int count = result.get(temp);
                    count++;
                    result.put(temp,count);

                } else {
                    result.put(temp,1);
                    lis.add(temp);
                }
            } else {
                if (lis.contains(temp)) {
                    int count = result.get(temp);
                    count++;
                    result.put(temp,count);

                } else {
                    int min = getMinVal(lis);
                    lis.remove(Integer.valueOf(min));
                    result.put(min,0);
                    result.put(temp,1);
                    lis.add(temp);
                }
            }

        }

        Collections.sort(lis);
        StringBuilder sb = new StringBuilder();
        for (int a : lis) {
            sb.append(a+" ");
        }
        System.out.println(sb);

    }
    public static int getMinVal(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int minVal = Integer.MAX_VALUE;

        for (int a : list) {
            int temp = result.get(a);
            if (temp < minVal) {
                minVal = temp;
                min = a;
            }
        }
        return min;
    }
}