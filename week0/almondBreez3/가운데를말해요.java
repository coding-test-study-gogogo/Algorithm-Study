import java.util.*;
import java.lang.*;
import java.io.*;

class 가운데를_말해요 {
    public static int N;
    public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (maxHeap.size() == minHeap.size()) {

                maxHeap.offer(arr[i]);
            } else {
                minHeap.offer(arr[i]);
            }

            if (!maxHeap.isEmpty() && !minHeap.isEmpty())
                if (maxHeap.peek() > minHeap.peek()) {
                    int num = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(num);
                }

            sb.append(maxHeap.peek()+ "\n");

        }
        System.out.println(sb.toString());
    }
}