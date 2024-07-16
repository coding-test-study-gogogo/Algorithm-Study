package week2.olsohee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 후보_추천하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] recommendations = new int[101];
        List<Node> list = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 이미 들어가 있는 학생인 경우
            if (recommendations[num] != 0) {
                recommendations[num]++;
                for (Node node : list) {
                    if (node.studentNum == num) {
                        node.recommendationCnt++;
                    }
                }
                continue;
            }

            // 한 명 빼고 넣기
            if (list.size() == n) {
                Collections.sort(list, new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if (o1.recommendationCnt == o2.recommendationCnt) {
                            return o1.inNum - o2.inNum;
                        }
                        return o1.recommendationCnt - o2.recommendationCnt;
                    }
                });
                Node removedNode = list.remove(0);
                recommendations[removedNode.studentNum] = 0;
                list.add(new Node(num, i, 1));
                recommendations[num] = 1;
            }
            // 바로 넣기
            else {
                list.add(new Node(num, i, 1));
                recommendations[num] = 1;
            }
        }

        // 오름차순으로 출력
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.studentNum - o2.studentNum;
            }
        });
        for (Node node : list) {
            System.out.print(node.studentNum+ " ");
        }
    }

    private static class Node {
        int studentNum;
        int inNum; // 사진틀에 들어간 순서
        int recommendationCnt;

        public Node(int studentNum, int inNum, int recommendationCnt) {
            this.studentNum = studentNum;
            this.inNum = inNum;
            this.recommendationCnt = recommendationCnt;
        }
    }
}
