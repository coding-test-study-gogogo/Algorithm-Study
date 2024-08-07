import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        arr = new int[6];
        int maxX = 0;
        int maxY = 0;
        int maxXIdx = -1;
        int maxYIdx = -1;



        for (int i=0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            arr[i] = dist;


            if (pos == 1 || pos ==2) {
                if (dist > maxX) {
                    maxX = Math.max(dist,maxX);
                    maxXIdx = i;
                }

            } else if (pos == 3 || pos ==4) {
                if (dist >maxY) {
                    maxY = Math.max(dist,maxY);
                    maxYIdx = i;
                }

            }



        }

        int minSize = arr[(maxXIdx+3)%6] * arr[(maxYIdx+3)%6];

        int result = (maxX * maxY) - minSize;
        System.out.println(result * N);
    }
}