package week12.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 구간자르기 {
    public static void main(String args[]) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int[] count = new int[1000001];	//각 지점 선분 개수 저장 배열
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int max = -1, min = 1000001;	//선분 존재 최소, 최대값
        //선분에 대한 정보 저장
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," " );
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            count[left]++;	//선분 시작 : 선분 개수 증가!
            count[right]--;	//선분 끝 : 선분 개수 감소!
            //최대, 최소값 구하기
            min = Math.min(min, left);
            max = Math.max(max, right);
        }
        //누적합 구하기!
        for(int i=min+1;i<=max;i++)
            count[i] += count[i-1];

        //투 포인터 탐색
        int s_id = min, e_id = min;	//s_id : start, e_id : end
        int A = 0, B = 0;
        int s = 0;	//현재 값
        while(e_id <= max){
            if(s < K) {			//S + E < K
                s += count[e_id++];		//end 증가!
            }else if(s == K){	//S + E == K, 조건 달성!, 탐색 종료!
                A = s_id;
                B = e_id;
                break;
            }else		//S + E > K
                s -= count[s_id++];		//start 증가
        }
        if(A == min)	//A가 최소값일 때 0으로 변경!
            A = 0;
        System.out.println(A+" "+B);
    }
}