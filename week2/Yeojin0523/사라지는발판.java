package week2;

class Result{
	private boolean isWin;
	private int moveCnt;
	
	public Result(boolean isWin, int moveCnt) {
		this.isWin = isWin;
		this.moveCnt = moveCnt;
	}
	
	public int getMoveCnt() {
		return moveCnt;
	}
	public boolean getIsWin() {
		return isWin;
	}
}

public class 사라지는발판 {
	
	private static final int BOARD_SIZE = 4;
	
	private static int boardX;

	private static int boardY;
	
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String args[]) {
		int[][] board = new int[5][];
		int[] aloc = new int[2];
		int[] bloc = new int[2];
		System.out.println(solution(board, aloc, bloc));	
	}
	
	public static int solution(int[][] board, int[]aloc, int[] bloc) {
		boardX = board.length;
		boardY = board[0].length;
		
		return move(board, aloc[0], aloc[1], bloc[0], bloc[1]).getMoveCnt();
	}
	
	private static Result move(int[][] board, int ax, int ay, int bx, int by){
		
		if(isEnded(board, ax, ay)) {
			return new Result(false, 0);
		}
		if(ax == bx && ay == by) {
			return new Result(true, 1);
		}
		
		boolean win = false; // 승리하는 경우가 하나라도 있다면, 
		int maxValue = - 1; // 갈 수 있는 경로 중 승리하는 경우가 존재하지 않으면 최대한 오래 버텨야해
		int minValue = Integer.MAX_VALUE; // 갈 수 있는 경로 중 승리하는 경우가 있다면, 최대한 빨리 끝내야 해
		board[ax][ay] = 0; // 현재 위치의 발판 = 0으로 만들고, 나는 이동해야 해
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			int nx = ax + dx[i];
			int ny = ay + dy[i]; // 이동
			
			if(!inRange(nx, ny) || board[nx][ny] == 0) {
				continue; // 범위 밖이면 계속 돌고
			}
			
			Result nextResult = move(board, bx, by, nx, ny);
			
			if(nextResult.getIsWin()) {
				maxValue = Math.max(maxValue, nextResult.getMoveCnt());
			}else {
				win = true; // 내가 이김
				minValue = Math.min(minValue, nextResult.getMoveCnt());
			}
		}
		board[ax][ay] = 1; // 다시 1로 만들고
		return new Result(win, (win ? minValue : maxValue) + 1);
	}
	private static boolean isEnded(int[][] board, int x, int y) {
		for(int i = 0; i < BOARD_SIZE; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(inRange(nx, ny) && board[nx][ny] == 1)
				return false;
		}
		return true;
	}
	private static boolean inRange(int x, int y) {
		return 0 <= x && x < boardX && 0 <= y && y < boardY;
	}
}
